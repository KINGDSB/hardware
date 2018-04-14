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
        autoWidth: false,
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
            url: "resource/list.json",
            type: "POST",
            data: function(reqData) {
                $("#search_div").find(".form-control").each(function() {
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
            { data: "resKey" },
            { data: "resUrl" },
            { data: "level", render: function (data) {
                return data == 0 ? "目录" : data == 1 ? "菜单" : "按钮";
            } },
            { data: "icon" },
            { data: "isHidden", render: function (data) {
                return data ? "是" : "否";
            } },
            { data: "description" },
//            { data: "seq" },
            { data: "createdDate", render: function(data) {
                if (!data) return '';
                return !data || new Date(data).Format("yyyy-MM-dd hh:mm:ss");
            } },
            { data: "createdBy" },
            { data: "updatedDate", render: function(data) {
                if (!data) return '';
                return !data || new Date(data).Format("yyyy-MM-dd hh:mm:ss");
            } },
            { data: "updatedBy" }
        ],
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });

    // New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("resource/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("创建菜单");
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
        ap.ajaxGet("resource/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑菜单");
            $("#formBody").html(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("resource/remove", "id=" + selected['id'], function(resp) {
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

    $("#search").on('click', function() {
        table.ajax.reload();
    });
});