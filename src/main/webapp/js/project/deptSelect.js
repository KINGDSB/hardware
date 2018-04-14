var table;

// 初始化方法
function init(){
	getDeptTreeView();
}

function getDeptTreeView(){
    var treeview;
    var addElements = [];
    var removeElements = [];

    $.get("dept/treeView.json", function(data) {
        treeview = $("#resourceTree").treeview({
            data: data.nodes,
            showIcon: false,
            levels: 4,
            showCheckbox: true,
            onNodeChecked: function (event, node) {
            	var deptIds = "";
            	var checkedItems = $('#resourceTree').treeview('getChecked');
            	for (var i = 0; i < checkedItems.length; i++) {
            		deptIds += checkedItems[i].id+",";
            	}
            	deptIds = deptIds.substr(0, deptIds.length-1);
                console.log(deptIds);
            	$("#deptIds").val(deptIds);
                table.ajax.reload();
            },
            onNodeUnchecked: function (event, node) {
            	var deptIds = "";
            	var checkedItems = $('#resourceTree').treeview('getChecked');
            	for (var i = 0; i < checkedItems.length; i++) {
            		deptIds += checkedItems[i].id+",";
            	}
            	deptIds = deptIds.substr(0, deptIds.length-1);
                console.log(deptIds);
            	$("#deptIds").val(deptIds);
                table.ajax.reload();
            }
        });
    });
}

$(function() {
    
    init();    

});
