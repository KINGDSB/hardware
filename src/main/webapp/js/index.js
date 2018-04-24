var g_last_page_vals = "";
$(function() {
    $('#alertModal').on('show.bs.modal', function() {
        $(this).css({
//            'margin-top': function () {
//                console.log(window.outerHeight);
//                console.log(($(window).height() - $(".modal-dialog").height()) / 2);
//                return Math.max(0, ($(window).scrollTop()) + ($(window).height() - $(".modal-dialog").height()) / 2 ) / $(window).height();
//            },
            'z-index': function () {
                //console.log($(this).css("z-index"));
                return 1071;
            }
        });
        $(".modal-backdrop").css({
            'z-index': function () {
                //console.log($(this).css("z-index"));
                return 1061;
            }
        });
    });
    $('#alertModal').on('hidden.bs.modal', function() {
        $(".modal-backdrop").css({
            'z-index': function () {
                //console.log($(this).css("z-index"));
                return 1040;
            }
        });

        $("#confirmBtn").off();
    });

    $('#formModal').on('hidden.bs.modal', function() {
    	Ladda.create(document.getElementById("formSubmit")).stop();
        $("#formSubmit").off();
        $("#formSubmit").show();
        $('.modal-footer').show();
        $(".modal-lg").removeClass("modal-lg");
    });

    /*
    $('#alertModal').on('shown.bs.modal', function() {
        $(".modal-backdrop:last").css({
            'z-index': function () {
                //console.log($(this).css("z-index"));
                return 1061;
            }
        });
    });
    */

    $("[nav-n]").each(function () {
        $(this).bind("click",function(){
            var nav = $(this).attr("nav-n");
            var vals = nav.split(",");
            g_last_page_vals = vals;
            ap.ajaxGet(contextPath + "/" + vals[0], {"resId": vals[2]}, function(data) {
                $("#content").html(data);
                if(table != undefined && table != null && table.ajax != null){
                	table.ajax.reload();
                }
                // console.log(data);
            });
            /* var pageContent = $("#content");
            pageContent.load(contextPath + "/" + vals[0], function(response) {
                if (toJson(response).result == 303) {
                    //console.log(toJson(response).result);
                    //$("#loginDialog").modal();
                    window.location.href = contextPath + "/login";
                }
            }); */
            $(".page-header").html(vals[1]);
        });
    });
    var logoutCallback = function() {
       ap.ajaxPost("logout", null, function(resp) {
            var code = resp.code;
            if (code == 200) {
                toLoginPage();
            } else {
                alertModal("退出失败！", "提示");
            }
       });
    }

    $("#logout").on("click", function(e) {
        e.preventDefault();

        confirmModal("确认退出？", "提示", logoutCallback);
    });
    
    $("#modifyPassword").on("click", function(e) {
        e.preventDefault();
        $(".page-header").html("修改密码");

        ap.ajaxGet("user/password", {action: "modify"}, function(data) {
            console.log(data);
            $("#content").html(data);
        });
    });
    
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
        	window.history.pushState('forward', null, '/hardware/backindex');
        	window.history.forward(1);
            location.reload();
        });
    }
	window.history.pushState('forward', null, '/hardware/backindex'); //在IE中必须得有这两行
	window.history.forward(1);
});
function back(){
	var vals = g_last_page_vals;
    ap.ajaxGet(contextPath + "/" + vals[0], {"resId": vals[2]}, function(data) {
        $("#content").html(data);
        if(table != undefined && table != null && table.ajax != null){
        	table.ajax.reload();
        }
    });
    $(".page-header").html(vals[1]);
};