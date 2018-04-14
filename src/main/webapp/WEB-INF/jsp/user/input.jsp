<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- FormValidation CSS file -->
<link rel="stylesheet" href="${ctxPath}/static/formvalidation/css/formValidation.min.css">
<!-- ladda -->
<link rel="stylesheet" href="${ctxPath}/static/ladda-bootstrap/dist/ladda-themeless.min.css">
<!-- datetimepicker -->
<link rel="stylesheet" href="${ctxPath}/static/datetimepicker/css/bootstrap-datetimepicker.min.css" />

<script type="text/javascript">
    var action = '${action}';
</script>

<!-- FormValidation plugin and the class supports validating Bootstrap form -->
<script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>

<!-- datetimepicker -->
<script type="text/javascript" src="${ctxPath}/static/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctxPath}/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript" src="${ctxPath}/js/user/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<!-- 模态框（Modal） -->

<form role="form" class="form-horizontal" id="inputForm" method="post">
    <div class="form-group">
        <label for="u_name" class="col-lg-3 control-label">姓名：</label>
        <div class="col-lg-8">
            <input class="form-control" id="u_name" name="name" type="text" placeholder="姓名" maxlength="25">
        </div>
    </div>
    <div class="form-group">
        <label for="u_username" class="col-lg-3 control-label">账户：</label>
        <div class="col-lg-8">
            <input class="form-control" id="u_username" name="username" type="text" placeholder="账户">
        </div>
    </div>
    <c:if test="${action == 'create'}">
    <div class="form-group">
        <label for="u_password" class="col-lg-3 control-label">密码：</label>
        <div class="col-lg-8">
            <input class="form-control" id="u_password" name="password" value="888888" type="password" placeholder="密码">
        </div>
    </div>
    </c:if>
    <div class="form-group">
        <label class="col-lg-3 control-label">性别：</label>
        <div class="col-lg-8">
            <select name="gender" class="form-control">
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="u_birthday" class="col-lg-3 control-label">生日：</label>
        <div class="col-lg-8">
            <input class="form-control form_datetime" id="u_birthday" name="birthday" type="text" value='' readonly/>
        </div>
    </div>
    <div class="form-group">
        <label for="u_mobile" class="col-lg-3 control-label">手机号码：</label>
        <div class="col-lg-8">
            <input class="form-control" id="u_mobile" name="mobile" type="text" placeholder="手机号码">
        </div>
    </div>
    <div class="form-group">
        <label for="u_email" class="col-lg-3 control-label">邮箱：</label>
        <div class="col-lg-8">
            <input class="form-control" id="u_email" name="email" type="text" placeholder="exzample@airparking.cn">
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 control-label">是否禁用：</label>
        <div class="col-lg-8">
            <select name="isLocked" class="form-control">
                <option value="false">否</option>
                <option value="true">是</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="u_description" class="col-lg-3 control-label">描述：</label>
        <div class="col-lg-8">
            <textarea class="form-control" id="u_description" name="description"/>
        </div>
    </div>
</form>
