<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- DataTables CSS -->
<link href="${ctxPath}/static/datatables/media/css/dataTables.bootstrap.min.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="${ctxPath}/static/datatables/extensions/Responsive/css/responsive.dataTables.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/media/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/extensions/Select/css/select.dataTables.min.css">
<!-- <link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/extensions/Buttons/css/buttons.dataTables.min.css"> -->
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/layer/skin/layer.css">

<!-- DataTables JavaScript -->
<script src="${ctxPath}/static/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="${ctxPath}/static/datatables/media/js/dataTables.bootstrap.min.js"></script>
<script src="${ctxPath}/static/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
<script src="${ctxPath}/static/datatables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${ctxPath}/static/layer/layer.js"></script>

<script type="text/javascript" src="${ctxPath}/js/resource/list.js"></script>
<div id="search_div">
    <div class="col-lg-12">
        <div class="col-lg-3">
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="名称">
            </div>
        </div>
        <div class="col-lg-3">
            <div class="form-group">
                <select name="level" class="form-control">
                    <option value="-1">--请选择类型--</option>
                    <option value="0">目录</option>
                    <option value="1">菜单</option>
                    <option value="2">按钮</option>
                </select>
            </div>
        </div>
        <div class="col-lg-3">
            <button id="search" class="btn btn-success">查询</button>
            <!-- <button id="reset" class="btn btn-primary">重设</button> -->
        </div>
    </div>
</div>
<br/>
<div class="col-lg-12">
    <div class="form-group">
        <c:forEach items="${btns}" var="res" varStatus="srs">
            <button id="editor_${res.action}" class="btn ${res.icon}">${res.name}</button>
        </c:forEach>
    </div>
</div>
<div class="col-lg-12">
    <div class="dataTable_wrapper">
        <table width="100%"
               class="table table-striped table-bordered table-hover"
               id="listTable">
            <thead>
            <tr>
                <th style="width: 10px"></th>
                <th>名称</th>
                <th>Key</th>
                <th>Url</th>
                <th>类型</th>
                <th>图标</th>
                <th>隐藏</th>
                <th>描述</th>
                <!--<th>顺序</th>-->
                <th>创建日期</th>
                <th>创建者</th>
                <th>更新日期</th>
                <th>更新者</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!-- /.col-lg-12 -->

<div id="inputDiv"></div>
