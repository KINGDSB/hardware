$(function() {

    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

$.fn.serializeObject = function() {
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

(function() {
    ap = {};
    ap.ajaxGet = function(url, data, callback, type) {
        $.get(url, data, function(resp) {
            try{
                var res = toJson(resp).code;
                if (res == 303) {
                    window.location.href = contextPath + "/login";
                } else if (res == 401) {
                    alertModal("无权限访问！", function () {
                        window.location.href = contextPath + "/index";
                    });
                } else if (res == 400) {
                    alertModal("请求参数有误！");
                } else {
                    callback(resp);
                }
            } catch(e) {
                callback(resp);
            }
        });
    };
    ap.post = function (url, data, callback) {
        $.ajax({
            url: url,
            data: data,
            method: "POST",
            success: function(resp) {
                try{
                    var res = toJson(resp).code;
                    if (res == 303) {
                        window.location.href = contextPath + "/login";
                    } else if (res == 401) {
                        alertModal("无权限访问！", function () {
                            window.location.href = contextPath + "/index";
                        });
                    } else if (res == 400) {
                        alertModal("请求参数有误！");
                    } else {
                        callback(resp);
                    }
                }catch(e){
                    callback(resp);
                }
                console.log(resp.code);
            },
            error: function(res) {
                alertModal("系统繁忙，请稍后在访问！", "错误提示");
            }
        });
    };
    ap.ajaxPost = function (url, data, callback, error) {
        $.ajax({
            url: url,
            data: data,
            dataType : 'json',
            method: "POST",
            success: function(resp) {
                try{
                    var res = resp.code;
                    if (res == 303) {
                        window.location.href = contextPath + "/login";
                    } else if (res == 401) {
                        alertModal("无权限访问！", function () {
                            window.location.href = contextPath + "/index";
                        });
                    } else if (res == 400) {
                        alertModal("请求参数有误！");
                    } else {
                        callback(resp);
                    }
                }catch(e){
                    callback(resp);
                }
            },
            error: function(res) {
                if (error) {
                    error(res);
                } else {
                    alertModal("系统繁忙，请稍后在访问！", "错误提示");
                }
            }
        });
    };
    ap.ajaxSubmit = function(form, success, error) {
        console.log(JSON.stringify(form.serializeObject()));
        $.ajax({
            url: form.attr("action"),
            method: form.attr("method"),
//            processData: false,
            dataType : 'json',
            contentType : 'application/json;charset=utf-8',
            data: JSON.stringify(form.serializeObject()),
            success: function(resp) {
                try{
                    var res = resp.code;
                    if (res == 303) {
                        window.location.href = contextPath + "/login";
                    } else if (res == 401) {
                        alertModal("无权限访问！", function () {
                            window.location.href = contextPath + "/index";
                        });
                    } else if (res == 400) {
                        alertModal("请求参数有误！");
                    } else {
                        success(resp);
                    }
                }catch(e){
                    success(resp);
                }
                console.log(resp);
            },
            error: function(resp) {
                error(resp);
            }
        });
    };

    ap.ajaxUpload = function(form, success, error) {
        var settings = {
            url: form.attr("action"),
            data: form.serialize(),
            method: form.attr("method"),
            processData: false,  // tell jQuery not to process the data
            contentType: false,   // tell jQuery not to set contentType
            success: function(resp) {
                try{
                    var res = toJson(resp).code;
                    if (res == 303) {
                        window.location.href = contextPath + "/login";
                    } else if (res == 401) {
                        alertModal("无权限访问！", function () {
                            window.location.href = contextPath + "/index";
                        });
                    } else if (res == 400) {
                        alertModal("请求参数有误！");
                    }
                    success(resp);
                }catch(e){
                    success(resp);
                }
                console.log(resp);
            },
            error: function(resp) {
                error(resp);
            }
        };

        $.ajax(settings);
    };
})();

function toJson(text) {
	var data = eval("(" + text + ")");
	return data;
};

function toLoginPage() {
    window.location.href = contextPath + "/login";
};

function alertModal (msg, header, fn) {
    $("#confirmBtn").hide();
    if (msg)
        $("#showMessage").html(msg);

    if (header)
        $("#alertModalLabel").html(header);

    if (fn)
        $("#closeAlertModal").on('click', fn);

    $('#alertModal').modal({
        backdrop: "static",
        show: true
    });
};

function confirmModal (msg, header, fn) {
    $("#confirmBtn").show();
    if (msg)
        $("#showMessage").html(msg);

    if (header)
        $("#alertModalLabel").html(header);

    $('#alertModal').modal({
        backdrop: "static",
        show: true
    });

    console.log(fn)
    if (fn) {
        $("#confirmBtn").on('click', fn);
    }
};

function handleAjaxResponse (code) {
    if (code == 303) {
        window.location.href = contextPath + "/login";
    } else if (code == 401) {
        alertModal("无权限访问！", function () {
            window.location.href = contextPath + "/index";
        });
    } else if (code == 400) {
        alertModal("请求参数有误！");
    }
}
