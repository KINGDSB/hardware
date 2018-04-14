/**
 * Created by Administrator on 2017/4/6.
 */

// 初始化数据方法
function initData(){
}

$(function(){

    initData()
    
    var inputForm = $("#inputForm");
    if (action == 'edit' && data) {
//        $('<input/>').attr({
//            type: 'hidden',
//            id: 'u_id',
//            name: 'id',
//            value: data.id
//        }).appendTo(inputForm);
//        $("#u_username").attr("disabled", true);
//        inputForm.attr("action", "user/edit");
//        inputForm.find(".form-control").each(function(e) {
//            if (this.name == 'isLocked') {
//                $(this).val(data[this.name] ? "true" : "false");
//            } else if(this.name == 'birthday'){
//                var birthday = data[this.name] ? new Date(data[this.name]).Format("yyyy-MM-dd") : '';
//                $(this).val(birthday);
//            } else {
//                $(this).val(data[this.name]);
//            }
//        });
    } else if (action == 'create'){
        inputForm.attr("action", "project/create");
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
                        message: '项目名称不能为空'
                    }
                }
            },
            version: {
                validators: {
                    notEmpty: {
                        message: '版本不能为空'
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
                var res = resp.code;
                if (res == 200) {
                    $('#formModal').modal("hide");
                        alertModal("保存成功！", null, function(){
                        getProjectList();
                    });
                }
            }, function(resp) {
                l.stop();
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
    });
});
