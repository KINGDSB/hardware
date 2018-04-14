/*
* Created by Administrator on 2016-04-01.
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
            id: 'qrtz_id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "qrtz/edit");
        inputForm.find(".form-control").each(function(e) {
            if (this.name == "isConcurrent" || this.name == "isPaused") {
                $(this).val(data[this.name] ? "true" : "false");
            } else {
                $(this).val(data[this.name]);
            }
        });
    } else if (action == 'create'){
        inputForm.attr("action", "qrtz/create");
    }

    if ($("#q_jobType").val() != 1) {
        $("#q_jobData_div").hide();
    }

    $("#q_jobType").change(function(){
        if ($("#q_jobType").val() == 1) {
            $("#q_jobData_div").show();
        } else {
            $("#q_jobData_div").hide();
        }
    });

    inputForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            jobName: {
                validators: {
                    notEmpty: {
                        message: '任务名称不能为空'
                    }
//                    ,
//                    remote: {
//                        message: '任务名称已经存在',
//                        url: contextPath + '/qrtz/validName',
//                        type: 'post'
//                    }
                }
            },
            cronExpression: {
                validators: {
                    notEmpty: {
                        message: '表达式不能为空'
                    },
                    remote: {
                        message: '表达式格式不正确',
                        url: contextPath + '/qrtz/validCronExpression',
                        type: 'POST'
                    }
                }
            },
            classBean: {
                validators: {
                    notEmpty: {
                        message: 'bean名称不能为空'
                    }
                }
            },
            methodName: {
                validators: {
                    notEmpty: {
                        message: '任务方法名称不能为空'
                    }
                }
            },
            jobData: {
                validators: {
                    callback: {
                        message: 'SQL不能为空',
                        callback: function(value, validator, $field) {
                            var jobType = $("#q_jobType").val();

                            if (jobType == 1) {
                                return value.length > 0;
                            } else {
                                return true;
                            }
                        }
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
                        table.ajax.reload();
                    });
                } else {
                    alertModal(resp.desc);
                }
            }, function(resp) {
                l.stop();
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
    });
});