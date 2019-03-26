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
            url: "staticData/list.json",
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
            { data: "id" },
            { data: "dataKey" },
            { data: "dataValueEn" },
            { data: "dataValueCn" },
            { data: "description" },
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
        ap.ajaxGet("staticData/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("新增数据");
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
        ap.ajaxGet("staticData/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑数据");
            $("#formBody").html(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("staticData/remove", "id=" + selected['id'], function(resp) {
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

    // 修改首页图片
    $('#editor_indexImg').on('click', function (e) {
        ap.ajaxGet("staticData/indexImg", {action: "edit"}, function(data) {
            $("#formModalLabel").html("修改首页图片");
            $("#formBody").html(data);
        });
    } );
});