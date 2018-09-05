var table;

$(function() {
	
	// 产品类型
	$.post("productType/getSelect.json",function(result){
        $("#typeSelect").append("<option value=''>产品类型</option>");
		for (var int = 0; int < result.length; int++) {
			$("#typeSelect").append("<option value='"+result[int].id+"'>"+result[int].nameCn+"</option>");
		}
	});
	
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
		order: [[ 1, 'DESC' ]],
//		bSort: false,
		dom: "Bfrtip",
        processing: true,
        serverSide: true,
        ajax: {
            url: "product/list.json",
            type: "POST",
            data: function( reqData) {
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
            { data: "id" },
            { data: "nameCn" },
            { data: "nameEn" },
            { data: "productType.nameCn" },
            // { data: "description" },
		    { data: "description", render: function(data) {
		    	if (null!=data) {
				    var reg = new RegExp( '<br/>' , "g" )
				    return data.replace( reg , '' );
				}
		    	return "";
		    }},
		    { data: "other", render: function(data) {
		    	if (null!=data) {
				    var reg = new RegExp( '<br/>' , "g" )
				    return data.replace( reg , '' );
				}
		    	return "";
		    }},
		    { data: "status", render: function(data) {
		          if (2 == data) return '上架中';
		          return '未上架';
		    }},
		    { data: "createdDate", render: function(data) {
		          if (!data) return '';
		          return !data || new Date(data).Format("yyyy-MM-dd");
		    }}
        ],
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });

	// New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("product/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("添加类型");
            $("#formBody").html(data);
        });
    } );

    // Edit record
    $('#editor_edit').on('click', function (e) {
        e.preventDefault();

        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择编辑项！');
            return;
        }

        ap.ajaxGet("product/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑类型");
            $("#formBody").html(data);
            console.log(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("product/remove", "id=" + selected['id'], function(resp) {
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
            layer.msg('请选择删除记录！');
            return;
        }

        confirmModal("确认删除该类型？", "删除提示", delCallback);
    } );

    $("#search").on('click', function() {
    	table.ajax.reload();
    });

});