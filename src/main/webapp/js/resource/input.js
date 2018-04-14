/*
* Created by ryan on 2016-03-17.
*/
$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });

    var data = table.rows({ selected: true }).data()[0];

    ap.ajaxGet("resource/forCreate.json", null, function(resp) {
        var nodes = resp.nodes;
        $.each(nodes, function(idx, node) {
            $("#r_parentId").append('<option value="' + node.id + '">---' + node.text + '</option>');

            if (node.nodes) {
                $.each(node.nodes, function(idx, subNode) {
                    $("#r_parentId").append('<option value="' + subNode.id + '">------' + subNode.text + '</option>');
                });
            }
        });
        $("#r_parentId").val(data["parentId"]);
    });

    $("#r_level").change(function() {
        var level = $(this).val();
        if (level == 2) {
            $("#btnStyle").show();
        } else {
            $("#btnStyle").hide();
            $("#r_icon").val("");
        }
    });

    $("#btnStyle").find("button").on("click", function(e) {
        e.preventDefault();

        var className = $(this).attr("class");
        $("#r_icon").val(className.substring(className.indexOf(" ") + 1));
        $(".fa-check").remove("i");
        $(this).append('<i class="fa fa-check"></i>');
    });

    var inputForm = $("#inputForm");
    if (action == 'edit' && data) {
        $('<input/>').attr({
            type: 'hidden',
            id: 'res_id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "resource/edit");
        inputForm.find(".form-control").each(function(e) {
            $(this).val(data[this.name] + "");
        });
//        $("#r_isHidden").val(false + "");

//        $("#r_resKey").attr("disabled", true);
//        $("#r_resUrl").attr("disabled", true);
        initIconField();
    } else if (action == 'create'){
        inputForm.attr("action", "resource/create");
        initIconField();
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
//            parentId: {
//                validators: {
//                    notEmpty: {
//                        message: '不能为空'
//                    }
//                }
//            },
            resKey: {
                validators: {
                    notEmpty: {
                        message: 'key不能为空'
                    }
                }
            },
            resUrl: {
                validators: {
                    notEmpty: {
                        message: 'url不能为空'
                    }
                }
            },
//            level: {
//                validators: {
//                    notEmpty: {
//                                message: '0-组，1菜单，2按钮不能为空'
//                    }
//                }
//            },
            icon: {
                validators: {
                    callback: {
                        message: '不能为空',
                        callback: function (value, validator, $field) {
                            var resourceType = $("#r_level").val();
                            if (resourceType == 2) {
                                var icon = $("#r_icon").val();
                                return icon && icon.length > 0;
                            } else {
                                return true;
                            }
                            var items = $('#captchaOperation').html().split(' '),
                                sum   = parseInt(items[0]) + parseInt(items[2]);
                            return value == sum;
                        }
                    }
                }
            },
            seq: {
                validators: {
                    notEmpty: {
                        message: '显示顺序不能为空'
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
                if (res == 1010){
                    alertModal("菜单URL已经存在！", "提示");
                } else if (res == 1011) {
                    alertModal("菜单类型与上级菜单不匹配！", "提示");
                } else if (res == 1012) {
                    alertModal("菜单key已经存在！", "提示");
                } else {
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

function initIconField() {
    if (action == 'create') {
        $("#btnStyle").hide();
        return;
    }

    var data = table.rows({ selected: true }).data()[0];
    var icon = data["icon"];
    $("#r_icon").val(icon);

    var level = data["level"];
    if (level == 2) {
        $("#btnStyle").show();
        console.log($("#r_icon").val());
        $("#btnStyle").find("." + icon).each(function() {
            if ($(this).attr("class") == "btn " + icon) {
                $(this).append('<i class="fa fa-check"></i>');
            }
        });
    } else {
        $("#btnStyle").hide();
        $(".fa-check").remove("i");
        $("#r_icon").val("");
    }
}