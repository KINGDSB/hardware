/*
 * Created by Administrator on 2016-04-01.
 */
var table;

$(function() {
    // table created
    table = $('#listTable').DataTable({
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
            url: "qrtz/list.json",
            type: "POST",
            data: function( reqData) {
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
                        { data: "jobName" },
                        { data: "cronExpression" },
                        { data: "classBean" },
                        { data: "methodName" },
                        { data: "isConcurrent" , render: function(data) {
                                return data ? '是' : '否';
                            }
                        },
                        { data: "isPaused", render: function(data) {
                                return data ? '是' : '否';
                            }
                        },
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
        "createdRow": function ( row, data, index ) {
            var isPaused = data["isPaused"];
            if (isPaused) {
                $('td', row).last().after('<td><a href="#" onclick="javascript:resume('+data["id"]+')">恢复</a></td>');
            } else {
                $('td', row).last().after('<td><a href="#" onclick="javascript:pause('+data["id"]+')">暂停</a></td>');
            }

        },
        language: {
            url: contextPath + '/common/i18n/language_zh.json'
        }
    });

    // New record
    $('#editor_create').on('click', function (e) {
        ap.ajaxGet("qrtz/inputPage", {action: "create"}, function(data) {
            $("#formModalLabel").html("新增");
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
        ap.ajaxGet("qrtz/inputPage", {action: "edit"}, function(data) {
            $("#formModalLabel").html("编辑");
            $("#formBody").html(data);
        });
    } );

    // Delete a record
    var delCallback = function() {
        var selected = table.rows({ selected: true }).data()[0];
        ap.ajaxPost("qrtz/remove", "id=" + selected['id'], function(resp) {
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

function pause(id) {
    confirmModal("确认暂停？", "暂停提示", function(){
        ap.ajaxPost("qrtz/pause", "id=" + id, function(resp) {
            var res = resp.code;
            if (res == 200) {
                alertModal("暂停成功！", null, function(){
                    table.ajax.reload();
                });
            }
        });
    });
}
function resume(id) {
    confirmModal("确认恢复任务？", "恢复提示", function(){
        ap.ajaxPost("qrtz/resume", "id=" + id, function(resp) {
            var res = resp.code;
            if (res == 200) {
                alertModal("恢复成功！", null, function(){
                    table.ajax.reload();
                });
            }
        });
    });
}
