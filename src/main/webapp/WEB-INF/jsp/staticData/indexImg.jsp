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
<script type="text/javascript" src="${ctxPath}/js/staticData/indexImg.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<form role="form" class="form-horizontal" id="inputForm" method="post">
    <div class="form-group col-lg-4">
        <label for="file1" class="control-label">轮播图第一张：</label>
        <div id="picUrlsDiv1">
            <input id="uploadfile1" name="files" class="file" type="file" multiple data-min-file-count="1">
            <input type="hidden" id="indexInfoImg1" name="indexInfoImg">
        </div>
    </div>
    <div class="form-group col-lg-4">
        <label for="file2" class="control-label">轮播图第二张：</label>
        <div id="picUrlsDiv2">
            <input id="uploadfile2" name="files" class="file" type="file" multiple data-min-file-count="1">
            <input type="hidden" id="indexInfoImg2" name="indexInfoImg">
        </div>
    </div>
    <div class="form-group col-lg-4">
        <label for="file3" class="control-label">轮播图第三张：</label>
        <div id="picUrlsDiv3">
            <input id="uploadfile3" name="files" class="file" type="file" multiple data-min-file-count="1">
            <input type="hidden" id="indexInfoImg3" name="indexInfoImg">
        </div>
    </div>
</form>