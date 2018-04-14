/**
 * Created by Administrator on 2017/4/6.
 */

// 初始化数据方法
function initData(){
	// 获取project下拉列表框
	$.post("project/getSelect.json",function(result){
        $("#projectSelectSub").append("<option value=''>请选择</option>");
		for (var int = 0; int < result.length; int++) {
			$("#projectSelectSub").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
		}
	});
	// 获取问题类型下拉列表框
	$.post("matter/getMatterTypeSelect.json",function(result){
        $("#typeSub").append("<option value=''>请选择</option>");
		for (var int = 0; int < result.length; int++) {
			$("#typeSub").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
		}
	});
//	// 获取反馈人下拉列表框
//	$.post("user/getSelect.json",function(result){
//        $("#feedbackUid").append("<option value=''>请选择</option>");
//		for (var int = 0; int < result.length; int++) {
//			$("#feedbackUid").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
//		}
//	});
}

function checkUser(){
	// 获取反馈人下拉列表框
    var feedbackUname = $("#feedbackUname").val();
    if (feedbackUname == null || feedbackUname == '') {
        alertModal("请输入正确的反馈人姓名", "错误提示");
        $("#feedbackUname").val("");
        $("#feedbackUname").focus();
	} else {
	    $.ajaxSettings.async = false;
		$.post("user/checkUserIsExist.json", {name: feedbackUname}, function(result){
			if (result != null) {
				$("#feedbackUid").val(result.id)
			} else {
		        alertModal("请输入正确的反馈人姓名", "错误提示");
		        $("#feedbackUname").val("");
		        $("#feedbackUname").focus();
			}
		});
	    $.ajaxSettings.async = true;
	}
}

// 获取项目信息
function getProjectInfo(){
	var pid = $("#projectSelectSub").val();
	if (pid != null && pid != '') {
		$.post("project/getProjectInfo.json",{projectId: pid},function(result){
			console.log(result)
			$("#version").val(result.version);
			var membersHtml = "";
			if (result.members.length == 0) {
				membersHtml += "<span>产品经理: ---</span><br/>";
				membersHtml += "<span>普通成员: ---</span><br/>";
			} else {
				for (var int = 0; int < result.members.length; int++) {
					if (result.members[int].type == 1) {
						membersHtml += "<span>产品经理: " + result.members[int].member.name + "</span><br/>";
						$("#productUid").val(result.members[int].member.id);
					} else {
						membersHtml += "<span>普通成员: " + result.members[int].member.name + "</span><br/>";
					}
				}
			}
			$('#pmembers').html(membersHtml);
		});
	}
}

$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });

    $('#uploadfile').fileinput({
    	language: 'zh',
        uploadUrl: 'common/uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
//        uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
//        maxFileCount:10, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    });
    //异步上传返回结果处理
    $("#uploadfile").on("fileuploaded", function(event, data, previewId, index) {
        console.log("fileuploaded");
        console.log(data);
        if (200 == data.response.code) {
        	if ($("#attachment").val() != null && $("#attachment").val() != '') {
	    		$("#attachment").val($("#attachment").val() + "," + data.response.desc);
			} else {
	    		$("#attachment").val(data.response.desc);
			}
		}
    });

    initData()
    
    var data = table.rows({ selected: true }).data()[0];

    var inputForm = $("#inputForm");
    if (action == 'edit' && data) {
        $('<input/>').attr({
            type: 'hidden',
            id: 'category_id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "matter/edit");
        inputForm.find(".form-control").each(function(e) {
            $(this).val(data[this.name]);
        });
    } else if (action == 'create'){
        inputForm.attr("action", "matter/create");
    }

    inputForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	projectId: {
                validators: {
                    notEmpty: {
                        message: '项目不能为空'
                    }
                }
            },
            type: {
                validators: {
                    notEmpty: {
                        message: '问题类型不能为空'
                    }
                }
            },
            grade: {
                validators: {
                    notEmpty: {
                        message: '处理等级不能为空'
                    }
                }
            },
            feedbackUname: {
                validators: {
                    notEmpty: {
                        message: '问题反馈人不能为空'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: '附件不能为空'
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
                }
            }, function(resp) {
                l.stop();
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
    });
});
