$(function(){
    $('#selectRole').modal({
        backdrop: "static",
        show: true
    });

    var selected = table.rows({ selected: true }).data()[0];
    var userId = selected['id'];

    var pick;
    $('#selectRole').on('shown.bs.modal', function() {
        $.get("role/roles.json", "userId=" + userId, function(data) {
            var roles = [];
            $.each(data.roles, function(idx, val) {
                roles.push({id: val.id, text: val.name});
            })

            var userRoles = [];
            $.each(data.userRoles, function(idx, val) {
                userRoles.push({id: val.id, text: val.name});
            })

            pick = $("#pickList").pickList({data: roles, pickedData: userRoles, add: "添加", remove: "删除", removeAll: "删除所有", addAll: "添加所有"});
        });
    });

    $("#selectRoleSubmit").on('click', function(e) {
        e.preventDefault();
        console.log(pick.getValues());

        var pickedData = '';
        $.each(pick.getValues(), function(idx, val) {
            pickedData = pickedData + val.id + ",";
        });
        pickedData = pickedData.substring(0, pickedData.length - 1);

        var l = Ladda.create(this);
        l.start();
        ap.ajaxPost("user/role", {userId: userId, roleIds: pickedData}, function(resp) {
            l.stop();
            var result = resp;
            if (result.code == 200) {
                $('#selectRole').modal("hide");
                alertModal("角色修改成功！", "提示");
            }
        }, function(resp) {
            l.stop();
            alertModal("系统繁忙，请稍后在访问！", "错误提示");
        });
    });
});