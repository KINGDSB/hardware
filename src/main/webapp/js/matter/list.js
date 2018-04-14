var table;

// 初始化数据方法
function initData(){
	// 获取project下拉列表框
	$.post("project/getSelect.json",function(result){
        $("#projectSelect").append("<option value=''>请选择项目</option>");
		for (var int = 0; int < result.length; int++) {
			$("#projectSelect").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
		}
	});
	// 获取问题类型下拉列表框
	$.post("matter/getMatterTypeSelect.json",function(result){
        $("#type").append("<option value=''>请选择问题类型</option>");
		for (var int = 0; int < result.length; int++) {
			$("#type").append("<option value='"+result[int].id+"'>"+result[int].name+"</option>");
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
}

// 点击项目名看详情
function showDetail(id){
	var checkBox = undefined;
	table.rows().data().each(function(item,index){
		if (id == item.id) {
			checkBox = $($(".select-checkbox")[index+1]);
		}
	});
	checkBox.click();
	ap.ajaxGet("matter/detail", {action: "detail"}, function(data) {
	  $("#formModalLabel").html("查看");
	  $("#formBody").html(data);
//	  checkBox.click();
	});
}

$(function() {
    // table created
    table = $('#listTable').DataTable({
        responsive: true,
    	bStateSave: true,
    	bFilter: false,
    	columnDefs: [ {
			orderable: false,
			className: 'select-checkbox',
			targets:   0
		} ],
		select: {
			style:    'os',
			selector: 'td:first-child'
		},
		order: [[ '1', 'DESC' ]],
//		bSort: false,
		dom: "Bfrtip",
        processing: true,
        serverSide: true,
        ajax: {
            url: "matter/list.json",
            type: "POST",
            data: function(reqData) {
            	$("#search_div").find(".queryClass").each(function() {
            		if ($(this).val() != null && $(this).val() != "") {
                		reqData[$(this).attr('name')] = $(this).val();
					}
    			});
            },
            dataSrc: function(resp) {
                if (resp.code) {
                    handleAjaxResponse(resp.code);
                    return;
                }
                return resp.data;
            }
        },
        rowId : "id",
        columns: [
		      { data: null, render: function() {
		          return '';
		      } },
		      /*{ data: "id" },*/
		      /*{ data: "project.name" },*/
		      { data: "project.name",render:function (value, show, row) {
			    	return '<a href="javascript:void(0);" onclick="showDetail('+row.id+')">'+value+'</a>';
		      } },
		      { data: "description" },
		      { data: "grade",render:function (data) {
		          if (data == 1) {
		              return '低级';
		          } else if (data == 2) {
		              return '中级';
		          } else if (data == 3) {
		              return '紧急';
		          } else if (data == 4) {
		              return '致命';
		          } else {
		              return '未知';
		          }
		      } },
		      { data: "matterType.name" },
		      { data: "createdDate", render: function(data) {
		          if (!data) return '';
		          return !data || new Date(data).Format("yyyy-MM-dd");
		      }},
		      { data: "feedbackUser.name" },
		      { data: "expectTime", render: function(data) {
		          if (!data) return '';
		          return !data || new Date(data).Format("yyyy-MM-dd");
		      }},
		      { data: "trueTime",render:function (value, show, row) {
			    	if (null != value) {
			    		  return ((row.trueTime - row.createdDate)%(24*3600*1000)/(3600*1000)).toFixed(1);    //计算天数后剩余的毫秒数  
					} else {
						return '-';
					}
		      } },
		      { data: "productUser.name" },
		      { data: "replyNames" },
		      { data: "processStatus",render:function (data) {
		          if (data == 1) {
		              return '待处理';
		          } else if (data == 2) {
		              return '处理中';
		          } else if (data == 3) {
		              return '待确认';
		          } else if (data == 4) {
		              return '未解决';
		          } else if (data == 5) {
		              return '已解决';
		          } else if (data == 6) {
		              return '申请延期中';
		          } else {
		              return '未知';
		          }
		      } },
//		      {
//		    	  data: null, render: function(data, type, row){
//	            	return [
//	                    '<a id="matterDetailEdit" href="javascript:void(0);" onclick="matterDetailEdit('+data.id+')"><span class="glyphicon glyphicon-edit"></span></a>　',
//	                    '<a id="matterDetailComment" href="javascript:void(0);"><span class="glyphicon glyphicon-comment"></span></a>',
//	                ];
//	            }
//		      }
        ],
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });
    
    initData();    

    // New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("matter/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("创建问题");
            $("#formBody").html(data);
        });
    } );

    // Edit record
    $('#editor_detail').on('click', function (e) {
        e.preventDefault();

        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择数据项！');
            return;
        }
    	ap.ajaxGet("matter/detail", {action: "detail"}, function(data) {
	      $("#formModalLabel").html("查看");
	      $("#formBody").html(data);
		});
    } );

    // detail record
    $('#editor_edit').on('click', function (e) {
        e.preventDefault();

        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择编辑项！');
            return;
        }
    	ap.ajaxGet("matter/detail", {action: "edit"}, function(data) {
	      $("#formModalLabel").html("查看明细");
	      $("#formBody").html(data);
		});
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("matter/remove", "id=" + selected['id'], function(resp) {
            var res = resp.code;
            if (res == 200) {
                alertModal("删除成功！", null, function(){
                    table.ajax.reload();
                });
            }
        });
    };
    $('#editor_remove').on('click', function (e) {
        e.preventDefault();

        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择要删除记录！');
            return;
        }

        confirmModal("确认删除？", "删除提示", delCallback);
    } );

    // enabled record
    $("#search").on('click', function() {
        table.ajax.reload();
    });
});

//function matterDetailEdit(id){
//	alert("matterDetailEdit"+id);
//	ap.ajaxGet("matter/detail", {action: "edit"}, function(data) {
//      $("#formModalLabel").html("查看明细");
//      $("#formBody").html(data);
//	});
//}