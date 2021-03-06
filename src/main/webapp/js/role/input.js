/*
* Created by ryan on 2016-03-17.
*/
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
            id: 'ro_id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "role/edit");
        inputForm.find(".form-control").each(function(e) {
            $(this).val(data[this.name]);
        });
    } else if (action == 'create'){
        inputForm.attr("action", "role/create");
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
//                                message: '不能为空'
                            }
                        }
                    },
                    status: {
                        validators: {
                            notEmpty: {
//                                message: '不能为空'
                            }
                        }
                    },
                    roleKey: {
                        validators: {
                            notEmpty: {
//                                message: '不能为空'
                            }
                        }
                    },
                    description: {
                        validators: {
                            notEmpty: {
//                                message: '不能为空'
                            }
                        }
                    },
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
                var res = resp.code;
                if (res == 200) {
                    $('#formModal').modal("hide");
                    alertModal("保存成功！", null, function(){
                        table.ajax.reload();
                    });
                }
            }, function(resp) {
                l.stop();
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
    });
});