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
<script type="text/javascript" src="${ctxPath}/js/qrtzg/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<form role="form" class="form-horizontal" id="inputForm" method="post">
            <div class="form-group">
                <label for="q_gname" class="col-lg-3 control-label">名称：</label>
                <div class="col-lg-8">
                    <input class="form-control" id="q_gname" name="gname" type="text" placeholder="名称"/>
                </div>
            </div>
            <div class="form-group">
                <label for="q_description" class="col-lg-3 control-label">描述：</label>
                <div class="col-lg-8">
                    <input class="form-control" id="q_description" name="description" type="text" placeholder="描述"/>
                </div>
            </div>
            <!--
            <div class="form-group">
                <label for="q_isPaused" class="col-lg-3 control-label">暂停：</label>
                <div class="col-lg-8">
                    <input class="form-control" id="q_isPaused" name="isPaused" type="text" placeholder="暂停"/>
                </div>
            </div>
            -->
</form>