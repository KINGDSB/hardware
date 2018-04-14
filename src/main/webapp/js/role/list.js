/*
 * Created by ryan on 2016-03-17.
 */
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
            url: "role/list.json",
            type: "POST",
            data: function( reqData) {
                $("#search_div").find("input").each(function() {
                    reqData[$(this).attr('name')] = $(this).val();
                    console.log(reqData)
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
            { data: "status" },
            { data: "roleKey" },
            { data: "description" },
            { data: "createdDate", render: function(data) {
                if (!data) return '';
                return !data || new Date(data).Format("yyyy-MM-dd hh:mm:ss");
            } },
            { data: "createdBy" },
            { data: "updatedDate", render: function(data) {
                if (!data) return '';
                return new Date(data).Format("yyyy-MM-dd hh:mm:ss");
            }},
            { data: "updatedBy" }
        ],
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });

    // New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("role/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("创建角色");
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
        ap.ajaxGet("role/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑角色");
            $("#formBody").html(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("role/remove", "id=" + selected['id'], function(resp) {
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

    $('#editor_perm').on('click', function (e) {
        var selected = table.rows({ selected: true }).data()[0];
        if (!selected || selected.length == 0) {
            layer.msg('请选择用户！');
            return;
        }
        ap.ajaxGet("resource/select", null, function(data) {
            $("#inputDiv").html(data);
        });
    } );

    $("#search").on('click', function() {
        table.ajax.reload();
    });
});