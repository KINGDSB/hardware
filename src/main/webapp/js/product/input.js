/**
 * Created by Administrator on 2017/4/6.
 */

// 初始化数据方法
function initData(){
	$.ajaxSettings.async = false;
	// 获取project下拉列表框
	$.post("productType/getSelect.json",function(result){
        $("#typeSelectSub").append("<option value=''>请选择</option>");
		for (var int = 0; int < result.length; int++) {
			$("#typeSelectSub").append("<option value='"+result[int].id+"'>"+result[int].nameCn+"</option>");
		}
	});
	$.ajaxSettings.async = true;
}

$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });

    $('#uploadfile').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
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
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        //异步上传返回结果处理
        console.log("fileuploaded");
        console.log(data);
        if (200 == data.response.code) {
        	if ($("#picUrls").val() != null && $("#picUrls").val() != '') {
	    		$("#picUrls").val($("#picUrls").val() + "|" + data.response.desc);
			} else {
	    		$("#picUrls").val(data.response.desc);
			}
		}
    });

    initData()
    
    var data = table.rows({ selected: true }).data()[0];

    var inputForm = $("#inputForm");
    if (action == 'edit' && data) {
        $('<input/>').attr({
            type: 'hidden',
            id: 'id',
            name: 'id',
            value: data.id
        }).appendTo(inputForm);
        inputForm.attr("action", "product/edit");
        inputForm.find(".form-control1").each(function(e) {
            $(this).val(data[this.name]);
        });

//        $("#typeSelectSub").val(data.type);
        
        $("#picUrlsDiv").empty();
        //img 
        if (null != data.picUrls) {
        	console.log(data.picUrls)
            var imgs = data.picUrls.split("\|");
        	console.log(imgs)
            if (imgs.length > 0) {
            	for (var i = 0; i < imgs.length; i++) {
                	$("#picUrlsDiv").append('<a class="attachment" href="'+imgs[i]+'" target="_blank"><img src="'+imgs[i]+'" style="max-width: 100%;max-height: 100%;"></a>');
				}
            }
		}
        // 鼠标悬浮大图
        $(".attachment").hover(function(){
            $("#hoverImg").append("<p id='pic'><img src='"+this.href+"'></p>");
            $(".attachment").mousemove(function(e){
                $("#pic").css({
                    "top":(e.pageY - 390)+"px",
                    "left":(e.pageX - 420)+"px"
                }).fadeIn("fast");
            });
        },function(){
            $("#pic").remove();
        });
        
    } else if (action == 'create'){
        inputForm.attr("action", "product/create");
    }

    inputForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	nameCn: {
                validators: {
                    notEmpty: {
                        message: '中文名不能为空'
                    }
                }
            },
            nameEn: {
                validators: {
                    notEmpty: {
                        message: '英文名不能为空'
                    }
                }
            },
            type: {
                validators: {
                    notEmpty: {
                        message: '类型不能为空'
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
