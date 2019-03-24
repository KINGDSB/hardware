$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });

    var data = table.rows({ selected: true }).data()[0];

    var inputForm = $("#inputForm");
    if (action == 'edit' && data) {
        $('<input/>').attr({
            type: 'hidden',
            id: 'u_id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "productSeries/edit");
        inputForm.find(".form-control").each(function(e) {
            if(this.name == 'createdDate'){
                var createdDate = data[this.name] ? new Date(data[this.name]).Format("yyyy-MM-dd") : '';
                $(this).val(createdDate);
            } else {
                $(this).val(data[this.name]);
            }
        });
    } else if (action == 'create'){
        inputForm.attr("action", "productSeries/create");
    }

    inputForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
            }
        }
    }).on('success.form.fv', function(e) {
        e.preventDefault();
    });

    $("#formSubmit").on('click', function(e) {
        e.preventDefault();
        inputForm.data('formValidation').validate();
        if (inputForm.data('formValidation').isValid()) {
            var l = Ladda.create(this);
            l.start();
            ap.ajaxSubmit(inputForm, function(resp) {
                l.stop();
                console.log(resp);
                var res = resp.code;
                console.log(res);
                if (res == 200) {
                    $('#formModal').modal("hide");
                    alertModal("保存成功！", "提示", function(){
                        table.ajax.reload();
                    });
                }
            }, function(resp) {
                l.stop();
                console.log(resp);
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
        console.log(inputForm.data('formValidation').isValid());
    });
});