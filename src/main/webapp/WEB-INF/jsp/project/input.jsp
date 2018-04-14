<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/6
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- FormValidation CSS file -->
<link rel="stylesheet" href="${ctxPath}/static/formvalidation/css/formValidation.min.css">
<!-- ladda -->
<link rel="stylesheet" href="${ctxPath}/static/ladda-bootstrap/dist/ladda-themeless.min.css">

<script type="text/javascript">
    var action = '${action}';
</script>
<!-- FormValidation plugin and the class supports validating Bootstrap form -->
<script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>
<script type="text/javascript" src="${ctxPath}/js/project/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<form role="form" class="form-horizontal" id="inputForm" method="post">
    <%-- <c:if test="${action == 'modify'}">
    <div class="form-group">
        <label for="old_password" class="col-lg-3 control-label">旧密码：</label>
        <div class="col-lg-7">
            <input class="form-control" id="old_password" name="oldPassword" value="" type="text" placeholder="旧密码">
        </div>
    </div>
    </c:if> --%>
    <div class="form-group">
        <label for="new_password" class="col-lg-3 control-label">项目名称：</label>
        <div class="col-lg-7">
            <input class="form-control" id="new_password" name="name" type="text" placeholder="请输入项目名称">
        </div>
    </div>
    <div class="form-group">
        <label for="new_password" class="col-lg-3 control-label">版本号：</label>
        <div class="col-lg-7">
            <input class="form-control" id="new_password" name="version" type="text" placeholder="请输入">
        </div>
    </div>
</form>
