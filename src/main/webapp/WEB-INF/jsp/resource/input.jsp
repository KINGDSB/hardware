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
<script type="text/javascript" src="${ctxPath}/js/resource/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<form role="form" class="form-horizontal" id="inputForm" method="post">
    <div class="form-group">
        <label for="r_name" class="col-lg-3 control-label">名称：</label>
        <div class="col-lg-8">
            <input class="form-control" id="r_name" name="name" type="text" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="r_resKey" class="col-lg-3 control-label">Key：</label>
        <div class="col-lg-8">
            <input class="form-control" id="r_resKey" name="resKey" type="text" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="r_resUrl" class="col-lg-3 control-label">Url：</label>
        <div class="col-lg-8">
            <input class="form-control" id="r_resUrl" name="resUrl" type="text" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="r_level" class="col-lg-3 control-label">类型：</label>
        <div class="col-lg-8">
            <select name="level" id="r_level" class="form-control">
                <option value="0">目录</option>
                <option value="1">菜单</option>
                <option value="2">按钮</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="r_parentId" class="col-lg-3 control-label">上级菜单：</label>
        <div class="col-lg-8">
            <select name="parentId" id="r_parentId" class="form-control">
                <option value="">无上级菜单</option>
            </select>
        </div>
    </div>
    <div id="btnStyle" class="form-group">
        <label for="r_icon" class="col-lg-3 control-label">图标：</label>
        <div class="col-lg-8">
            <button type="button" class="btn btn-default">按钮</button>
            <button type="button" class="btn btn-primary">按钮</button>
            <button type="button" class="btn btn-success">按钮</button>
            <button type="button" class="btn btn-info">按钮</button>
            <button type="button" class="btn btn-warning">按钮</button>
            <button type="button" class="btn btn-danger">按钮</button>
        </div>
        <div class="col-lg-8 col-lg-offset-3">
            <button type="button" class="btn btn-outline btn-primary">按钮</button>
            <button type="button" class="btn btn-outline btn-success">按钮</button>
            <button type="button" class="btn btn-outline btn-info">按钮</button>
            <button type="button" class="btn btn-outline btn-warning">按钮</button>
            <button type="button" class="btn btn-outline btn-danger">按钮</button>
        </div>
        <input id="r_icon" name="icon" type="hidden" value=''/>
    </div>
    <div class="form-group">
        <label for="r_isHidden" class="col-lg-3 control-label">隐藏：</label>
        <div class="col-lg-8">
            <select name="isHidden" id="r_isHidden" class="form-control">
                <option value="false">否</option>
                <option value="true">是</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="r_seq" class="col-lg-3 control-label">顺序：</label>
        <div class="col-lg-8">
            <input class="form-control" id="r_seq" name="seq" type="text" placeholder=""/>
        </div>
    </div>
    <div class="form-group">
        <label for="r_description" class="col-lg-3 control-label">描述：</label>
        <div class="col-lg-8">
            <textarea class="form-control" id="r_description" name="description" placeholder=""/>
        </div>
    </div>
</form>