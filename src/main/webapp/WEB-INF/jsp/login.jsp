<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <link rel="Shortcut Icon" href="/center/images/favicon_48X48.ico" type="image/x-icon" /> 
    <%@include file="/common/common.jsp"%>
    <link rel="stylesheet" href="${ctxPath}/static/formvalidation/css/formValidation.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/layer/skin/layer.css">

    <script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
    <script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
    <script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>
    <script src="${ctxPath}/static/layer/layer.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .modal-header, h5, .close {
            background-color: #337ab7;
            color: white !important;
            text-align: center;
            font-size: 30px;
        }
        .modal-footer {
            background-color: #f9f9f9;
        }
        .container{
            width: 100%;
            height: 100%;
            background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .modal-header{
            background: white;
            border: none;
        }
        .modal-header h5{
            color: black !important;
            background: white;
            font-size: 18px;
        }
        .modal-content1{
            width: 400px;
            background: white;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            overflow: hidden;
        }
        .modal-content1>div{
            width: 100%;
        }
        .form-group input{
            background: white;
        }
        .alert{
            font-size: 14px;
            padding:5px 10px;
        }
        /*.footer a{*/
            /*color: white;*/
        /*}*/
    </style>

</head>

<body>

    <div class="container">
    	<div style="margin-top: -150px;">
          <!-- Modal content-->
          <div class="modal-content1">
            <div class="modal-header">
              <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
                <img src="/hardware/images/logo.png" width="150px">
              <h5>商城后台管理</h5>
              <!-- <h3><span class="glyphicon glyphicon-lock"></span> 登陆</h3> -->
            </div>
            <div class="modal-body" style="padding:10px 35px;">
              <form id="loginForm" role="form" action="${ctxPath}/login" method="post">
                <div class="form-group">
                  <%--<label for="usrname"><span class="glyphicon glyphicon-user"></span> 用户名</label>--%>
                  <input class="form-control" name="username" id="username" type="text" placeholder="输入帐号">
                </div>
                <div class="form-group">
                  <%--<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> 密码</label>--%>
                  <input class="form-control" name="password" type="password" placeholder="输入密码">
                </div>
                <!-- <div class="checkbox">
                  <label><input type="checkbox" value="" checked>Remember me</label>
                </div> -->

                <div class="form-group" style="margin-top: 30px">
                  <button type="submit" id="login" class="btn btn-primary btn-block" >登&nbsp;&nbsp;陆</button>
                </div>
              </form>
            </div>
            <%--<div class="modal-footer">--%>
            <%--<!----%>
              <%--<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 取消</button>--%>
               <%--<p>Not a member? <a href="#">Sign Up</a></p>--%>
              <%--<p>Forgot <a href="#">Password?</a></p> -->--%>
            <%--</div>--%>
          </div>
          
        </div>
    </div>
    <div class="footer" style="width: 100%;text-align: center;font-size: 12px;position: fixed;bottom: 0;">
         <p class="cr">Copyright © 2015-2018 广州XXX有限公司  <img src="http://ehr.honey-lovely.cn/Public/images/2017-02-16/bottom_itlogo.png" style="vertical-align:middle;"><br><a href="http://www.miitbeian.gov.cn/" title="备案号" target="_blank">粤ICP备XXXX号 </a><br>纳税人识别号：XXX</p>
    </div>
</body>

<script type="text/javascript">
	$(function(){
	    var errorMsg = '${errorMsg}';

        var loader;
		$("#login").click(function(){
		    $("#loginForm").formValidation({
                framework: 'bootstrap',
                icon: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    username: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 3,
                                max: 30,
                                message: '用户名必须大于3个字符，小于30个字符'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_\.]+$/,
                                message: '用户名只能由字母，数字和下划线组成'
                            }
                        }
                    },
                    password: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                                stringLength: {
                                min: 3,
                                max: 30,
                                message: '密码必须大于4个字符'
                            }
                        }
                    }
                }
            }).on('success.form.fv', function(e) {
                // Prevent form submission
                e.preventDefault();

                // You can get the form instance
                var $form = $(e.target);

                // and the FormValidation instance
                var fv = $form.data('formValidation');

                // Use the defaultSubmit() method if you want to submit the form
                // See http://formvalidation.io/api/#default-submit
                fv.defaultSubmit();
                loader = layer.load(1, { shade: [0.5,'#fff']});  //0.1透明度的白色背景
            });
		});

		if (errorMsg && errorMsg.length > 0) {
		    layer.close(loader);
            $("#username").val('${username}');
            $('<div class="alert bg-success">' + errorMsg + '</div>').appendTo(".modal-body");
        }
	});
</script>

</html>
