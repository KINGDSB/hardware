<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- ladda -->
<link rel="stylesheet" href="${ctxPath}/static/ladda-bootstrap/dist/ladda-themeless.min.css">
<!-- FormValidation CSS file -->
<link rel="stylesheet" href="${ctxPath}/static/formvalidation/css/formValidation.min.css">

<script type="text/javascript">
    var action = '${action}';
</script>

<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<!-- FormValidation plugin and the class supports validating Bootstrap form -->
<script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>

<script type="text/javascript" src="${ctxPath}/js/user/password.js"></script>
<form role="form" class="form-horizontal" id="passwordForm" method="post">
    <c:if test="${action == 'modify'}">
    <div class="form-group">
        <label for="old_password" class="col-lg-3 control-label">旧密码：</label>
        <div class="col-lg-7">
            <input class="form-control" id="old_password" name="oldPassword" value="" type="text" placeholder="旧密码">
        </div>
    </div>
    </c:if>
    <div class="form-group">
        <label for="new_password" class="col-lg-3 control-label">新密码：</label>
        <div class="col-lg-7">
            <input class="form-control" id="new_password" name="newPassword" value="" type="text" placeholder="新密码">
        </div>
    </div>
    <c:if test="${action == 'modify'}">
    <div class="form-group">
        <label for="u_password" class="col-lg-3 control-label">确认新密码：</label>
        <div class="col-lg-7">
            <input class="form-control" id="cn_password" name="cnPassword" value="" type="text" placeholder="密码确认">
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-6 col-lg-offset-3">
            <button id="modifyPasswordSubmit" type="button" class="btn btn-primary">提交</button>
        </div>
    </div>
    </c:if>
</form>