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

<script type="text/javascript" src="${ctxPath}/js/productType/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<!-- 模态框（Modal） -->

<form role="form" class="form-horizontal" id="inputForm" method="post">

    <div class="form-group">
        <label for="parentId" class="col-lg-3 control-label">父级类型：</label>
        <div class="col-lg-8">
            <select class="form-control" id="typeSelectSub" name="parentId" maxlength="25"></select>
        </div>
    </div>
    <div class="form-group">
        <label for="nameCn" class="col-lg-3 control-label">中文名称：</label>
        <div class="col-lg-8">
            <input class="form-control" id="nameCn" name="nameCn" type="text" placeholder="中文名称" maxlength="25">
        </div>
    </div>
    <div class="form-group">
        <label for="nameEn" class="col-lg-3 control-label">英文名称：</label>
        <div class="col-lg-8">
            <input class="form-control" id="nameEn" name="nameEn" type="text" placeholder="英文名称">
        </div>
    </div>
    <div class="form-group">
        <label for="nameEs" class="col-lg-3 control-label">西班牙语名称：</label>
        <div class="col-lg-8">
            <input class="form-control" id="nameEn" name="nameEs" type="text" placeholder="西班牙语名称">
        </div>
    </div>
    <div class="form-group">
        <label for="photo" class="col-lg-3 control-label">类型图片：</label>
        <div id="picUrlsDiv" class="col-lg-8">
            <input id="uploadfile" name="files" class="file" type="file" multiple data-min-file-count="1">
            <input type="hidden" id="photo" name="photo">
        </div>
    </div>
</form>
