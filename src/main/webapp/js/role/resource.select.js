$(function(){
    $('#selectResource').modal({
        backdrop: "static",
        show: true
    });

    var selected = table.rows({ selected: true }).data()[0];
    var roleId = selected['id'];

    var treeview;
    var addElements = [];
    var removeElements = [];
    $('#selectResource').on('shown.bs.modal', function() {
        $.get("resource/listSelect.json", "roleId=" + roleId, function(data) {
            treeview = $("#resourceTree").treeview({
                data: data.nodes,
                showIcon: false,
                levels: 3,
                showCheckbox: true,
                onNodeChecked: function(event, node) {
                    var parentNode = $('#resourceTree').treeview('getParent', node);
                    if (parentNode && parentNode.state) {
                        treeview.treeview('checkNode', parentNode);
                    }

                    var idx1 = elementIndexOf(addElements, node.id);
                    if (idx1 < 0) {
                        var idx2 = elementIndexOf(removeElements, node.id);
                        if(idx2 < 0) {
                            addElements.push(node.id);
                        } else {
                            removeElements.splice(idx2, 1);
                        }
                    }
                },
                onNodeUnchecked: function (event, node) {
                    var children = node.nodes;

                    if (children) {
                        $.each(children, function(idx, data) {
                            if(data.state) {
                                treeview.treeview('uncheckNode', [ data.nodeId, { silent: $('#chk-check-silent').is(':checked') }]);
                            }
                        });
                    }

                    var idx1 = elementIndexOf(removeElements, node.id);
                    if (idx1 < 0) {
                        var idx2 = elementIndexOf(addElements, node.id);
                        if(idx2 < 0) {
                            removeElements.push(node.id);
                        } else {
                            addElements.splice(idx2, 1);
                        }
                    }
                }
            });
        });
    });

    $("#selectResourceSubmit").on('click', function(e) {
        e.preventDefault();
        console.log(addElements);
        console.log(removeElements);

        var addIds = '';
        var removeIds = '';
        $.each(addElements, function(idx, val) {
            addIds = addIds + val + ",";
        });
        addIds = addIds.substring(0, addIds.length - 1);

        $.each(removeElements, function(idx, val) {
            removeIds = removeIds + val + ",";
        });
        removeIds = removeIds.substring(0, removeIds.length - 1);

        var l = Ladda.create(this);
        l.start();
        ap.ajaxPost("role/perms", {roleId: roleId, addIds: addIds, removeIds: removeIds}, function(resp) {
            l.stop();
            var result = resp;
            if (result.code == 200) {
                $('#selectResource').modal("hide");
                alertModal("权限保存成功！", "提示");
            }
        }, function(resp) {
            l.stop();
            alertModal("系统繁忙，请稍后在访问！", "错误提示");
        });
    });
});

/* JS Array contains */
function elementIndexOf(arr, ele) {
    if (arr) {
        for (var i = 0; i < arr.length; i++) {
            if (ele == arr[i])
                return i;
        }
    }

    return -1;
}