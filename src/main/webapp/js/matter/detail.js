/**
 * Created by Administrator on 2017/4/6.
 */

function initData(){
    $('#uploadfile').fileinput({
    	language: 'zh',
        uploadUrl: 'common/uploadFiles', //上传的地址
        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:true, //是否显示上传按钮
        showRemove :true, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:false,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        enctype:'multipart/form-data',
        validateInitialCount:true
    });
    //异步上传返回结果处理
    $("#uploadfile").on("fileuploaded", function(event, data, previewId, index) {
//        console.log(data);
        if (200 == data.response.code) {
        	if ($("#attachment").val() != null && $("#attachment").val() != '') {
	    		$("#attachment").val($("#attachment").val() + "," + data.response.desc);
			} else {
	    		$("#attachment").val(data.response.desc);
			}
		}
    });
    
	// 初始化日期控件
	$('.form_datetime').datetimepicker({
	    minView: "month", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,
	    autoclose: 1,
	});
	
	$('#formModal').on('hide.bs.modal', function () {
		$("#buttons").show();
	    $("#matterButtons").hide();
	})
}

function submitFrom(url, data){
	$.post(url, data, function(resp){
        var res = resp.code;
        if (res == 200) {
    	    alertModal("操作成功！", null, function(){
    	        table.ajax.reload();
    	    });
		} else {
			alertModal("系统繁忙，请稍后在访问！", "错误提示");
		}
	});		        
}
// 申请延期
function applyDelay(){
	submitFrom("matter/applyDelay.json", {
		id: $("#matterId").val()
	});
}
// 申请延期
function assignMatter(){
	
	var replyUids = "";
	$.each($('input:checkbox:checked'), function(){
		replyUids += $(this).val()+",";
    });
	
	var data = {
        id: $("#matterId").val(),
        replyUids: replyUids.substr(0, replyUids.length - 1),
        solution: $("#solution").val(),
        attachment: $("#attachment").val()
	};
	submitFrom("matter/assignMatter.json", data);
}
function replySubmit(){
	submitFrom("matter/replySubmit.json", {
		id: $("#replyId").val(),
		solution: $("#solution2").val(),
		attachment: $("#attachment").val()
	});
}
function auditMatter(type){

	submitFrom("matter/auditMatter.json", {
		id: $("#matterId").val(),
		processStatus: type
	});
}
function timestamp2DateStr(timestamp){
    var date = new Date(timestamp);
    y = date.getFullYear();
    m = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
    d = date.getDate() < 10 ? '0'+date.getDate() : date.getDate();
    h = date.getHours() < 10 ? '0'+date.getHours() : date.getHours();
    minute = date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes();
    second = date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds();
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}

$(function(){
    $('#formModal').modal({
        backdrop: "static",
        show: true
    });
    initData();

    var data = table.rows({ selected: true }).data()[0];//    console.log(data);
    var inputForm = $("#inputForm");
    // 载入基本数据
    $('#projectName').text(data.project.name);
    $('#version').text(data.project.version);
    $('#matterTypeName').text(data.matterType.name);
    var grade = data.grade;
    if (grade == 1) {
        $('#grade').text('低级');
    } else if (grade == 2) {
        $('#grade').text('中级');
    } else if (grade == 3) {
        $('#grade').text('紧急');
    } else if (grade == 4) {
        $('#grade').text('致命');
    } else {
        $('#grade').text('未知');
    }
    $('#feedbackUname').text(data.feedbackUser.name);
    $('#createUserName').text(data.createdUser.name);
    $('#description').text(data.description);
    if (null != data.attachment && '' != data.attachment) {
        var imgs = data.attachment.split(",");
        for (var int = 0; int < imgs.length; int++) {
//    			$('#imgDiv').append('<img class="lazy" height="150px" width="200px" src="'+imgs[int]+'" onclick="showimage(\''+imgs[int]+'\')" >');
        	$('#imgDiv').append('<a class="attachment" href="'+imgs[int]+'"><img src="'+imgs[int]+'" width="150" height="100"></a>');
		}
	}
    $('#createTime').text(timestamp2DateStr(data.createdDate).substr(0, 16));
    $('#expectTime').text("　"+timestamp2DateStr(data.expectTime).substr(0, 10));

    // 鼠标悬浮大图
    $(".attachment").hover(function(){
        $(this).append("<p id='pic'><img src='"+this.href+"' id='pic1'></p>");
        $(".attachment").mousemove(function(e){
          $("#pic").css({
	          "top":(e.pageY-220)+"px",
	          "left":(e.pageX-400)+"px"
	      }).fadeIn("fast");
        });
    },function(){
        $("#pic").remove();
    });
    
    // 添加摸态框操作按钮
    var btn = '<br /><button id="formModalClose" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>';
    
	if (action == 'edit') {
	    if (data.processStatus == 1 && userid == data.productUser.id) { // 待处理进来
	        $('<input/>').attr({type: 'hidden', id: 'matterId', name: 'id', value: data.id}).appendTo(inputForm);
	        inputForm.attr("action", "matter/assignMatter");
	        $("#assignMatterDiv").show();
	        $("#handleMatterDiv").html("");
//	        $("#handleMatterDiv").hide("");

	        // 获取项目成员
	    	$.post("project/getMemberList.json", {projectId: data.project.id}, function(result){
	    		for (var int = 0; null != result && int < result.length; int++) {
	    			$("#replyUidDiv").append("<input class='replyUidCheckbox' name='replyUids' type='checkbox' value='"+result[int].member.id+"' />"+result[int].member.name+"　");
	    		}
	            btn += '<button onclick="applyDelay()" type="button" class="btn btn-danger" data-dismiss="modal">申请延期</button>';
		        btn += '<button onclick="assignMatter()" type="button" class="btn btn-success" data-dismiss="modal">提交</button>';
		        $("#buttons").hide()
		        $("#matterButtons").html(btn);
		        $("#matterButtons").show();
	    	});
	        
		} else if (data.processStatus == 2) { // 处理中
			$.post("matter/replyList.json", {matterId: data.id}, function(replyList){
			    console.log(replyList);
				for (var int = 0; int < replyList.length; int++) {
					if (userid == replyList[int].user.id && replyList[int].subStatus == 1) {
//				        inputForm.attr("action", "matter/replySubmit.json");
				        $("#assignMatterDiv").html();
				        $("#handleMatterDiv").show("");
				        $('<input/>').attr({type: 'hidden', id: 'replyId', name: 'id', value: replyList[int].id}).appendTo(inputForm);
				        btn += '<button onclick="replySubmit()" class="btn btn-primary" data-dismiss="modal">提交</button>';
				        break;
					}
				}
		        $("#buttons").hide()
		        $("#matterButtons").html(btn);
		        $("#matterButtons").show();
			});
		} else if (data.processStatus == 3) { // 待确认进来
	        $("#assignMatterDiv").html("");
	        $("#handleMatterDiv").html("");
			$.post("matter/replyList.json", {matterId: data.id}, function(replyList){
		        $('<input/>').attr({type: 'hidden', id: 'matterId', name: 'id', value: data.id}).appendTo(inputForm);
			    console.log(replyList);
				var showResultDiv = "";
				for (var int = 0; int < replyList.length; int++) {
					showResultDiv += '<div class="col-lg-12"><p align="left" class="col-lg-9">解决方案：';
					showResultDiv += replyList[int].solution+" (回复人:"+replyList[int].user.name+" - "+timestamp2DateStr(replyList[int].createdDate)+")";
					showResultDiv += '</p></div>';
				}
				$("#showResultDiv").html(showResultDiv);
				$("#showResultDiv").show();
				if (userid == data.feedbackUser.id) {
					btn += '<button onclick="auditMatter(4)" class="btn btn-danger" data-dismiss="modal">未解决</button>';
					btn += '<button onclick="auditMatter(5)" class="btn btn-success" data-dismiss="modal">已解决</button>';
				}
		        $("#buttons").hide()
		        $("#matterButtons").html(btn);
		        $("#matterButtons").show();
			});
		} else { // 其他为查看
			$.post("matter/replyList.json", {matterId: data.id}, function(replyList){
			    console.log(replyList);
		        $("#assignMatterDiv").html("");
		        $("#handleMatterDiv").html("");
				var showResultDiv = "";
				for (var int = 0; int < replyList.length && replyList[int].subStatus == 2; int++) {
					showResultDiv += '<div class="col-lg-12"><p align="left" class="col-lg-9">解决方案：';
					if (null != replyList[int].attachment) {
						for (var img in replyList[int].attachment.split(",")) {
							showResultDiv += '<img src="'+img+'" width="150" height="100">';
						}
					}
					showResultDiv += replyList[int].solution+" (回复人:"+replyList[int].user.name+" - "+timestamp2DateStr(replyList[int].createdDate)+")";
					showResultDiv += '</p></div>';
				}
				$("#showResultDiv").html(showResultDiv);
				$("#showResultDiv").show();
		        $("#buttons").hide()
		        $("#matterButtons").html(btn);
		        $("#matterButtons").show();
			});
		}
	} else if (action == 'detail') {
		$.post("matter/replyList.json", {matterId: data.id}, function(replyList){
		    console.log(replyList);
	        $("#assignMatterDiv").html("");
	        $("#handleMatterDiv").html("");
			var showResultDiv = "";
			for (var int = 0; int < replyList.length && replyList[int].subStatus == 2; int++) {
				showResultDiv += '<div class="col-lg-12"><p align="left" class="col-lg-9">解决方案：';
				if (null != replyList[int].attachment) {
					var imgs = replyList[int].attachment.split(",");
				    console.log(imgs);
				    for (var i = 0; i < imgs.length; i++) {
						showResultDiv += '<a class="attachment1" href="'+imgs[i]+'"><img src="'+imgs[i]+'" width="150" height="100"></a>';
					}
				}
				showResultDiv += replyList[int].solution+" (回复人:"+replyList[int].user.name+" - "+timestamp2DateStr(replyList[int].createdDate)+")";
				showResultDiv += '</p></div>';
			}
			$("#showResultDiv").html(showResultDiv);
			$("#showResultDiv").show();
	        $("#buttons").hide()
	        $("#matterButtons").html(btn);
	        $("#matterButtons").show();
	        
	        // 鼠标悬浮大图
	        $(".attachment1").hover(function(){
	            $(this).append("<p id='pic'><img src='"+this.href+"' id='pic1'></p>");
	            $(".attachment1").mousemove(function(e){
	              $("#pic").css({
	    	          "top":(e.pageY - 390)+"px",
	    	          "left":(e.pageX - 420)+"px"
	    	      }).fadeIn("fast");
	            });
	        },function(){
	            $("#pic").remove();
	        });
		});
	}
});
