var table;

// 初始化方法
function init(){
	getProjectList();
//	getDeptTreeView();
//	tableCreated();
	getProductUserSelect();
	initProjectMemberForm();
}

function getProjectInfo(projectId){
	$("#projectId").val(projectId)
    if (null != projectId || "" != projectId) {
    	$.post("project/getProjectInfo.json",{projectId: projectId},function(result){
//    		$("#projectName").text(result.name);
    		if (result.members.length > 0) {
    			$(".memberDiv").remove();
    			$("#productUserSelect").children().removeAttr("selected");
    			for (var i = 0; i < result.members.length; i++) {
    				if (result.members[i].type == 1) {
//    					alert(result.members[i].member.id)
    					$("#productUserSelect").find("option[value='"+result.members[i].member.id+"']").attr("selected",true);
//    					alert($("#productUserSelect").html())
    				} else {
    					var memberDiv = '<div class="form-group memberDiv"><label for="memberUid" class="col-lg-3 control-label">项目成员：</label>';
    	                memberDiv += '<div class="col-lg-7">';
    	                memberDiv += '<select id="memberUid" name="memberUid" class="form-control member">';
    	                memberDiv += '<option value="'+result.members[i].member.id+'">'+result.members[i].member.name+'</option>';
    	                memberDiv += '</select>';
    	                memberDiv += '</div></div>';
    					$("#productDiv").after(memberDiv);
    				}
    			}
    			if (result.members.length == 1) {
    				var memberDiv = '<div class="form-group memberDiv"><label for="memberUid" class="col-lg-3 control-label">项目成员：</label>';
    	            memberDiv += '<div class="col-lg-7">';
    	            memberDiv += '<select id="memberUid" name="memberUid" class="form-control member">';
    	            memberDiv += '</select>';
    	            memberDiv += '</div></div>';
    				$("#productDiv").after(memberDiv);
    				getMemberUserSelect();
    			}
    		} else {
    			$(".memberDiv").remove();
    			$("#productUserSelect").children().removeAttr("selected");
    			var memberDiv = '<div class="form-group memberDiv"><label for="memberUid" class="col-lg-3 control-label">项目成员：</label>';
                memberDiv += '<div class="col-lg-7">';
                memberDiv += '<select id="memberUid" name="memberUid" class="form-control member">';
                memberDiv += '</select>';
                memberDiv += '</div></div>';
    			$("#productDiv").after(memberDiv);
    		}
    	});
	}
}

function addMember(){
	var memberDiv = '<div class="form-group memberDiv"><label for="memberUid" class="col-lg-3 control-label">项目成员：</label>';
    memberDiv += '<div class="col-lg-7">';
    memberDiv += '<select id="memberUid" name="memberUid" class="form-control member">';
    $.ajaxSettings.async = false;
	$.post("user/getSelect.json",function(result){
		memberDiv += "<option value=''>请选择</option>";
		
		var selectIds = [];
		$("select option:selected").each(function(){
			selectIds.push($(this).val())
		})
		for (var int = 0; int < result.length; int++) {
			if (selectIds.indexOf(result[int].id+'') < 0) {
				memberDiv += "<option value='"+result[int].id+"'>"+result[int].name+"</option>";
			}
		}
	});
    $.ajaxSettings.async = true;
    memberDiv += '</select>';
    memberDiv += '</div></div>';
	$(".memberDiv:last").after(memberDiv);
}

function getProductUserSelect(){
	// 获取项目经理下拉列表框
	$.post("user/getSelect.json",function(result){
		$("#productUserSelect").empty();
	    $("#productUserSelect").append("<option value=''>请选择</option>");
		for (var int = 0; int < result.length; int++) {
			$("#productUserSelect").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
		}
	});
}

function changeProductUid(){
	getMemberUserSelect();
}

function getMemberUserSelect(){
	// 获取项目经理下拉列表框
	$.post("user/getSelect.json",function(result){
		var productUid = $("#productUserSelect").val();
	    $(".member").empty();
	    $(".member").append("<option value=''>请选择</option>");
		for (var int = 0; int < result.length; int++) {
			if (null != productUid && '' != productUid && result[int].id == productUid) {
				continue;
			}
			$("#memberUid").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
		}
	});
}

function getProjectList(){
	$.post("project/getSelect.json", function(projectList){
	    console.log(projectList);
		var projectItems = "";
		for (var i = 0; i < projectList.length; i++) {
			projectItems += '<a href="javascript:void(0);" onclick="getProjectInfo('+projectList[i].id+')" class="list-group-a"><li class="list-group-item pItem">'+projectList[i].name+'<input type="hidden" value="'+projectList[i].id+'" /></li></a>';
		}
		$("#projectList").empty();
		projectItems += '<li class="list-group-item active text-center" onclick="create()">+添加</li>';
		$("#projectList").append(projectItems);
		
	    $(".pItem").hover(function(){
	    	$(this).addClass("active");
	    	$(this).append('<span class="badge" onclick="remove('+$(this).children("input:first-child").val()+')" style="background-color:#FF0000">X</span>');
	    },function(){
	    	$(this).removeClass("active");
	    	$(this).find(".badge").remove();
	    });
	});
}

function initProjectMemberForm(){

	var inputForm = $("#projectMembersForm");

	inputForm.formValidation({
	    framework: 'bootstrap',
	    icon: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	productUid: {
	            validators: {
	                notEmpty: {
	                    message: '项目经理不能为空'
	                }
	            }
	        }
//    	,
//        memberUid: {
//            validators: {
//                notEmpty: {
//                    message: '成员不能为空'
//                }
//            }
//        }
	    }
	}).on('success.form.fv', function(e) {
	    e.preventDefault();
	});

	$("#memberFormSubmit").on('click', function(e) {
	    e.preventDefault();
	    // 校验projectId
	    if (null == $("#projectId").val() || "" == $("#projectId").val()) {
		    alertModal("请选择项目！", "错误提示");
		    return false;
		}
	    inputForm.data('formValidation').validate();
	    if (inputForm.data('formValidation').isValid()) {
	        var l = Ladda.create(this);
	        l.start();
	        ap.ajaxSubmit(inputForm, function(resp) {
	            l.stop();
	            var res = resp.code;
	            if (res == 200) {
	                alertModal("保存成功！", null, function(){
//	                    table.ajax.reload();
	                });
	            }
	        }, function(resp) {
	            l.stop();
	            alertModal("系统繁忙，请稍后在访问！", "错误提示");
	        });
	    }
	});
}

//function getDeptTreeView(){
//    var treeview;
//    var addElements = [];
//    var removeElements = [];
//
//    $.get("dept/treeView.json", function(data) {
//        treeview = $("#resourceTree").treeview({
//            data: data.nodes,
//            showIcon: false,
//            levels: 4,
//            showCheckbox: true,
//            onNodeChecked: function (event, node) {
//            	var deptIds = "";
//            	var checkedItems = $('#resourceTree').treeview('getChecked');
//            	for (var i = 0; i < checkedItems.length; i++) {
//            		deptIds += checkedItems[i].id+",";
//            	}
//            	deptIds = deptIds.substr(0, deptIds.length-1);
//            	$("#deptIds").val(deptIds);
//                table.ajax.reload();
//            },
//            onNodeUnchecked: function (event, node) {
//            	var deptIds = "";
//            	var checkedItems = $('#resourceTree').treeview('getChecked');
//            	for (var i = 0; i < checkedItems.length; i++) {
//            		deptIds += checkedItems[i].id+",";
//            	}
//            	deptIds = deptIds.substr(0, deptIds.length-1);
//            	$("#deptIds").val(deptIds);
//                table.ajax.reload();
//            }
//        });
//    });
//}
//
//function tableCreated(){
//    // table created
//    table = $('#listTable').DataTable({
//        responsive: true,
//    	bStateSave: true,
//    	bFilter: false,
//    	columnDefs: [ {
//			orderable: false,
//			className: 'select-checkbox',
//			targets:   0
//		} ],
//		select: {
//			style:    'os',
//			selector: 'td:first-child'
//		},
//		order: [[ 1, 'asc' ]],
////		bSort: false,
//		dom: "Bfrtip",
//        processing: true,
//        serverSide: true,
//        ajax: {
//            url: "user/getListByDepts.json",
//            type: "POST",
//            data: function( reqData) {
//            	$("#search_div").find(".queryClass").each(function() {
//            		reqData[$(this).attr('name')] = $(this).val();
//    			});
//            },
//            dataSrc: function(resp) {
//                if (resp.code) {
//                    handleAjaxResponse(resp.code);
//                    return;
//                }
//                return resp.data;
//            }
//        },
//        rowId : "userid",
//        columns: [
//            { data: null, render: function() {
//            	return '';
//            } },
//            { data: "username" },
//            { data: "framework_name" },
//            { data: "position" },
//            { data: "phone" }
//        ],
//        language: {
//            url: contextPath + '/common/i18n/language_zh.json'
//        }
//    });
//}

function remove(id){
    confirmModal("确认删除？", "删除提示", function(){
        console.log(id);
        ap.ajaxPost("project/remove", "id=" + id, function(resp) {
            var res = resp.code;
            if (res == 200) {
                alertModal("删除成功！", null, function(){
                	getProjectList();
                });
            }
        });
    });
}
function create(){
    ap.ajaxGet("project/inputPage", {action: "create"}, function(data) {
        $("#formModalLabel").html("添加项目");
        $("#formBody").html(data);

        $("#formModal").modal('show');
    });
}
//function choseDept(){
//    ap.ajaxGet("dept/deptSelect", function(data) {
//        $("#formModalLabel").html("选择部门");
//        $("#formBody").html(data);
//
//        $("#formModal").modal('show');
//    });
//}

$(function() {
    
    init();    

});
