<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- DataTables CSS -->
<link href="${ctxPath}/static/datatables/media/css/dataTables.bootstrap.min.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="${ctxPath}/static/datatables/extensions/Responsive/css/responsive.dataTables.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/media/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/datetimepicker/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/extensions/Select/css/select.dataTables.min.css">
<!-- <link rel="stylesheet" type="text/css" href="${ctxPath}/static/datatables/extensions/Buttons/css/buttons.dataTables.min.css"> -->
<link rel="stylesheet" type="text/css" href="${ctxPath}/static/layer/skin/layer.css">

<!-- DataTables JavaScript -->
<script src="${ctxPath}/static/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="${ctxPath}/static/datatables/media/js/dataTables.bootstrap.min.js"></script>
<script src="${ctxPath}/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctxPath}/static/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
<script src="${ctxPath}/static/datatables/extensions/Select/js/dataTables.select.min.js"></script>
<script src="${ctxPath}/static/layer/layer.js"></script>

<script type="text/javascript" src="${ctxPath}/js/matter/list.js"></script>
<style>
    #search_div{
        overflow: auto;
        padding:15px 0;
        border-bottom: 1px solid #f2f2f2;
    }
.table-hover tbody tr:hover{
    background: #f2ffd1;
}
</style>
<div id="search_div">
    <div class="col-lg-12 row text-center">

        <div class="col-lg-2">
            <div class="form-group">
                <select id="projectSelect" name="projectId" class="form-control queryClass" placeholder="项目名称"></select>
                <!-- <input type="text" id="projectSelect" name="projectId" class="form-control queryClass" placeholder="项目名称"> -->
            </div>
        </div>
        <div class="col-lg-2">
            <div class="form-group">
                <input type="text" id="createdDate" name="createdDate" class="form-control form_datetime queryClass"  placeholder="提交日期">
            </div>
        </div>

        <div class="col-lg-2">
            <div class="form-group">
                <select name="processStatus" id="processStatus" class="form-control queryClass" placeholder="处理状态" id="processStatus">
                    <option value="">选择处理状态</option>
                    <option value="1">待处理</option>
                    <option value="2">处理中</option>
                    <option value="3">待确认</option>
                    <option value="4">未解决</option>
                    <option value="5">已解决</option>
                    <option value="6">申请延期中</option>
                </select>
            </div>
        </div>

        <div class="col-lg-2">
            <div class="form-group">
                <!-- <input type="text" id="type" name="type" class="form-control queryClass" placeholder="问题类型"> -->
                <select id="type" name="type" class="form-control queryClass" placeholder="问题类型"></select>
            </div>
        </div>

        <div class="col-lg-2">
            <div class="form-group">
                <select name="grade" id="grade" class="form-control queryClass" placeholder="处理等级" id="grade">
                    <option value="">请选择处理等级</option>
                    <option value="1">低级</option>
                    <option value="2">中级</option>
                    <option value="3">紧急</option>
                    <option value="4">致命</option>
                </select>
            </div>
        </div>

        <div class="col-lg-2">
            <div class="form-group">
                <input type="text" id="keyWord" name="keyWord" class="form-control queryClass" placeholder="请输入关键字搜索">
            </div>
        </div>
<%--
        <div id="col">
        <div class="col-md-2 form-group">
            <label  for="projectId">项目名称</label>
            <div class="col-md-7">
                <select id="projectSelect" name="projectId" class="form-control queryClass"></select>
            </div>
        </div>
        <div class="col-md-2 form-group">
            <label  for="createdDate">提交日期</label>
            <div class="col-md-7">
                <input type="text" class="form-control form_datetime queryClass" id="createdDate" name="createdDate" placeholder="提交日期">
            </div>
        </div>
        <div class="col-md-2 form-group">
            <label for="processStatus">处理状态</label>
            <div class="col-md-7">
                <select id="processStatus" name="processStatus" class="form-control queryClass">
                    <option value="">请选择</option>
                    <option value="1">待处理</option>
                    <option value="2">处理中</option>
                    <option value="3">待确认</option>
                    <option value="4">未解决</option>
                    <option value="5">已解决</option>
                    <option value="6">申请延期中</option>
                </select>
            </div>
        </div>
        <div class="col-md-2 form-group">
            <label for="type">问题类型</label>
            <div class="col-md-7">
                <select id="type" name="type" class="form-control queryClass"></select>
            </div>
        </div>
        <div class="col-md-2 form-group">
            <label  for="grade">处理等级</label>
            <div class="col-md-7">
                <select id="grade" name="grade" class="form-control queryClass">
                    <option value="">请选择</option>
                    <option value="1">低级</option>
                    <option value="2">中级</option>
                    <option value="3">紧急</option>
                    <option value="4">致命</option>
                </select>
            </div>
        </div>
        <div class="col-md-2 form-group">
            <label  for="keyWord">关键字</label>
            <div class="col-md-7">
                <input id="keyWord" type="text" name="keyWord" class="form-control queryClass" placeholder="请输入关键字搜索">
            </div>
        </div>

        </div>
--%>
        <div class="col-lg-12" id="search_col">
            <button id="search" class="btn btn-success">&nbsp;&nbsp;&nbsp;查询&nbsp;&nbsp;&nbsp;</button>
            <%--<button id="reset" class="btn btn-primary">重设</button>--%>
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
               class="table table-bordered table-hover"
               id="listTable" style="color: #646464;">
            <thead style="color: black;">
             <tr>
              <th style="width: 10px"></th>
              <th style="width: 50px">项目名称</th>
              <th>事件描述</th>
              <th style="width: 50px">处理等级</th>
              <th>问题类型</th>
              <th>提交日期</th>
              <th>问题反馈人</th>
              <th>预计完成时间</th>
              <th style="width: 15px">用时</th>
              <th>产品经理</th>
              <th>处理人</th>
              <th>处理状态</th>
              <!-- <th>操作</th> -->
             </tr>
           </thead>
        </table>
    </div>
</div>
<!-- /.col-lg-12 -->

<div id="inputDiv"></div>
<style>
    #search_col{
        float: right;
    }
    div#col {
        width: 100%;
        height: 40px;
        margin-top: 30px;
    }
    label {
        float: left;
        margin-top: 2%;
    }

    tr{
        white-space:nowrap;
    }
    td:hover{
       overflow: visible;
       white-space: normal;
    }
   /* .table{
        table-layout: fixed;
    }
    .table td {
        word-wrap: break-word;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }*/
 </style>
