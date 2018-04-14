$(function(){
    $(".form-control").focus(function() {
        $(this).attr("type", "password");
    })

    var passwordForm = $("#passwordForm");
    passwordForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            oldPassword: {
                validators: {
                    callback: {
                        enabled: action == 'modify',
                        message: "旧密码不能为空",
                        callback: function (value, validator, $field) {
                            if (value && value.length > 0)  return true;
                            return false;
                        }
                    }

                }
            },
            newPassword: {
                validators: {
                    notEmpty: {
                        message: "新密码不能为空"
                    }
                }
            },
            cnPassword: {
                validators: {
                    identical: {
                        enabled: action == 'modify',
                        field: 'newPassword',
                        message: '密码和确认密码不相同'
                    }
                }
            }
        }
    }).on('success.form.fv', function(e) {
        e.preventDefault();
    });

    if (action == "update") {
        $('#formModal').modal({
            backdrop: "static",
            show: true
        });
        passwordForm.attr("action", "user/updatePassword");
    } else {
//        $(".col-lg-2").attr("class", "col-lg-3");
        $(".col-lg-7").attr("class", "col-lg-3");
        passwordForm.attr("action", "user/modifyPassword");
        $("#modifyPasswordSubmit").on("click", function(e) {
            e.preventDefault();
            passwordForm.data("formValidation").validate();

            if (passwordForm.data("formValidation").isValid()) {
                var l = Ladda.create(this);
                l.start();
                var newPassword = $("#new_password").val();
                var oldPassword = $("#old_password").val();
                ap.ajaxPost(passwordForm.attr("action"), {newPassword: newPassword, oldPassword: oldPassword},function(resp) {
                    l.stop();
                    var code = resp.code;
                    console.log(code);
                    if (code == 1002) {
                        alertModal("旧密码错误!", "提示");
                    } else {
                        alertModal("密码修改成功, 请重新登陆！", "提示", function(){
                            ap.ajaxPost("logout", null, function(resp) {
                                toLoginPage();
                            });
                        });
                    }
                }, function(resp) {
                   l.stop();
                   alertModal("系统繁忙，请稍后在访问！", "错误提示");
                });
            }
        });
    }

    $("#formSubmit").on("click", function(e) {
        e.preventDefault();
        passwordForm.data("formValidation").validate();

        if (passwordForm.data("formValidation").isValid()) {
            var l = Ladda.create(this);
            l.start();
            var data = table.rows({ selected: true }).data()[0];
            var newPassword = $("#new_password").val();
            var oldPassword = $("#old_password").val();
            ap.ajaxPost(passwordForm.attr("action"), {newPassword: newPassword, oldPassword: oldPassword, userId: data["id"]},function(resp) {
                l.stop();
                var code = resp.code;
                console.log(code);
                if (code == 200) {
                    $('#formModal').modal('hide');
                    alertModal("密码修改成功！", "提示", function(){
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