function initData(){
	$.ajaxSettings.async = false;
	// 获取project下拉列表框
	$.post("productType/getSelect.json",function(result){
        $("#typeSelectSub").append("<option value='0'>无父级类型</option>");
		for (var int = 0; int < result.length; int++) {
			$("#typeSelectSub").append("<option value='"+result[int].id+"'>"+result[int].nameCn+"</option>");
		}
	});
	$.ajaxSettings.async = true;
}

$(function(){
	
	initData();
	
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });
    
    $('#uploadfile').fileinput({
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
        	$("#photo").val(data.response.desc);
		}
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
        if (null != data.photo) {
        	$("#picUrlsDiv").append('<a class="attachment" href="'+data.photo+'" target="_blank"><img src="'+data.photo+'" width="200px" style="max-width: 100%;max-height: 100%;"></a>');
		}
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
                if (res == 200) {
                    $('#formModal').modal("hide");
                    alertModal("保存成功！", "提示", function(){
                        table.ajax.reload();
                    });
                }
            }, function(resp) {
                l.stop();
                // console.log(resp);
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            });
        }
        // console.log(inputForm.data('formValidation').isValid());
    });
});