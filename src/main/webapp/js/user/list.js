var table;

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
		order: [[ 1, 'asc' ]],
//		bSort: false,
		dom: "Bfrtip",
        processing: true,
        serverSide: true,
        ajax: {
            url: "user/list.json",
            type: "POST",
            data: function( reqData) {
            	$("#search_div").find("input").each(function() {
            		reqData[$(this).attr('name')] = $(this).val();
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
            { data: "name" },
            { data: "username" },
            { data: "genderStr" },
            { data: "birthday", render: function(data) {
                if (!data) return '';
                return !data || new Date(data).Format("yyyy-MM-dd");
            }},
            { data: "mobile" },
            { data: "email" },
            { data: "isLocked", render: function(data) {
            	return data ? '是' : '否';
            } }
        ],
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });

	// New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("user/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("添加用户");
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

        ap.ajaxGet("user/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑用户");
            $("#formBody").html(data);
            console.log(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("user/remove", "id=" + selected['id'], function(resp) {
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

        confirmModal("确认删除用户" + selected['username'], "删除提示", delCallback);
    } );

    // New record
    $('#editor_role').on('click', function (e) {
        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择用户！');
            return;
        }
        ap.ajaxGet("role/select", null, function(data) {
            $("#formModalLabel").html("角色配置");
            $("#inputDiv").html(data);
        });
    } );

    $("#search").on('click', function() {
    	table.ajax.reload();
    });

    $("#editor_pwd").on('click', function() {
        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择用户！');
            return;
        }
        ap.ajaxGet("user/password", {action: "update"}, function(data) {
            $("#formModalLabel").html("商家帐户详情");
            $("#formBody").html(data);
            console.log(data);
        });
    });

});