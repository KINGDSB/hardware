$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });

    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        pickerPosition: "bottom-right"
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
        $("#u_username").attr("disabled", true);
        inputForm.attr("action", "user/edit");
        inputForm.find(".form-control").each(function(e) {
            if (this.name == 'isLocked') {
                $(this).val(data[this.name] ? "true" : "false");
            } else if(this.name == 'birthday'){
                var birthday = data[this.name] ? new Date(data[this.name]).Format("yyyy-MM-dd") : '';
                $(this).val(birthday);
            } else {
                $(this).val(data[this.name]);
            }
        });
    } else if (action == 'create'){
        inputForm.attr("action", "user/create");
        $("#u_password").on("click", function() {
            $(this).val('');
        })
    }

//    console.log(action);
//    table.rows({ selected: true }).data().each(function(d) {
//        console.log(d);
//    });

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
                        message: '姓名不能为空'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 30,
                        message: '用户名必须大于6个字符，小于30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由字母，数字和下划线组成'
                    },
                    remote: {
                        message: '用户名已经存在',
                        url: contextPath + '/user/username/check',
                        type: 'POST'
                    }
                }
            },
            password: {
                enabled: action == 'create',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与用户名相同'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱格式不正确'
                    }
                }
            },
            mobile: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    phone: {
                        country: 'CN',
                        message: '手机号码格式不正确！'
                    }
                }
            },
//            captcha: {
//                validators: {
//                    callback: {
//                        message: 'Wrong answer',
//                        callback: function(value, validator, $field) {
//                            var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
//                            return value == sum;
//                        }
//                    }
//                }
//            }
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
                } else if (res = 1001) {
                    alertModal("用户名已经存在！", "提示", function() {
                        $("#u_username").focus();
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