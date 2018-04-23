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
<script type="text/javascript" src="${ctxPath}/js/staticData/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<form role="form" class="form-horizontal" id="inputForm" method="post">
    <div class="form-group">
        <label for="dataKey" class="col-lg-3 control-label">Key：</label>
        <div class="col-lg-8">
            <input class="form-control" id="dataKey" name="dataKey" type="text" placeholder="请输入key">
        </div>
    </div>
    <div class="form-group">
        <label for="dataValueEn" class="col-lg-3 control-label">英文值：</label>
        <div class="col-lg-8">
            <input class="form-control" id="dataValueEn" name="dataValueEn" type="text" placeholder="请输入英文值">
        </div>
    </div>
    <div class="form-group">
        <label for="dataValueCn" class="col-lg-3 control-label">中文值：</label>
        <div class="col-lg-8">
            <input class="form-control" id="dataValueCn" name="dataValueCn" type="text" placeholder="请输入中文值">
        </div>
    </div>
    <div class="form-group">
        <label for="description" class="col-lg-3 control-label">描述：</label>
        <div class="col-lg-8">
            <textarea class="form-control" id="description" name="description" placeholder="请输入描述"/>
        </div>
    </div>
</form>