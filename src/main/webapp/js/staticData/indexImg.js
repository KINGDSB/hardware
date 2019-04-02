/*
* Created by ryan on 2016-03-17.
*/

function initData(){
	$.ajaxSettings.async = false;
	// 获取project下拉列表框
	$.post("staticData/getIndeximg.json",function(result){
        // console.log(result)
		var list = result.result;
		for (var i = 0; i < list.length; i++) {
	        $("#picUrlsDiv"+(i+1)).append('<a class="attachment" href="'+list[i].dataValueEn+'" target="_blank"><img src="'+list[i].dataValueEn+'" width="200px" style="max-width: 100%;max-height: 100%;"></a>');
	        $("#indexInfoImg"+(i+1)).val(list[i].dataValueEn);
		}
	});
	$.ajaxSettings.async = true;
}

$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });
    
    //
    initData();

    $('#uploadfile1').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg1").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile2').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg2").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile3').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg3").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile4').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg4").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile5').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg5").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile6').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg6").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile7').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg7").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile8').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg8").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    $('#uploadfile9').fileinput({
    	language: 'zh',
        uploadUrl: 'uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        maxFileCount:1, //表示允许同时上传的最大文件个数
        enctype:'multipart/form-data',
        validateInitialCount:true
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function(event, data, previewId, index) {
        if (200 == data.response.code) {
        	$("#indexInfoImg9").val('http://47.254.38.242:8082/files/hardware'+data.response.desc);
		}
    });

    var inputForm = $("#inputForm");
    if (action == 'edit') {
        
        inputForm.attr("action", "staticData/saveIndeximg");
        
    }

    inputForm.formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
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
                if (res == 200){
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
