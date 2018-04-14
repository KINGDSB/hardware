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
<script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>
<%-- <script src="${ctxPath}/static/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="${ctxPath}/static/datatables/media/js/dataTables.bootstrap.min.js"></script> --%>
<script src="${ctxPath}/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctxPath}/static/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
<script src="${ctxPath}/static/layer/layer.js"></script>
<script src="${ctxPath}/static/tree/js/bootstrap-treeview.js"></script>

<script type="text/javascript" src="${ctxPath}/js/project/list.js"></script>
<div id="search_div">
    <input id="deptIds" class="queryClass" name="deptIds" type="hidden" />
</div>
<div class="col-lg-12 row text-center">
    <div class="panel panel-primary col-lg-4">
        <h4>项目名称</h4>
        <ul id="projectList" class="list-group text-left">
        </ul>
    </div>
    <div class="panel panel-primary col-lg-4">
        <h4>团队成员</h4>
        <!-- 
        <div id="resourceTree" class="modal-body col-lg-3 text-left">
        </div>
        <button onclick="choseDept()" type="button" class="btn btn-primary" data-dismiss="modal">选择部门</button>
        <div class="dataTable_wrapper col-lg-9">
            <table width="100%"
                class="table table-striped table-bordered table-hover"
                id="listTable">
                <thead>
                    <tr>
                        <th></th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>岗位</th>
                        <th>手机</th>
                    </tr>
                </thead>
            </table>
        </div>
         -->
        <div id="projectMembers" class="col-lg-12 ">
            <form id="projectMembersForm" action="project/saveMemberList" method="post" class="form-horizontal">
                <input id="projectId" name="projectId" type="hidden" />
                <div class="form-group" id="productDiv">
                    <label for="productUserSelect" class="col-lg-3 control-label">项目经理：</label>
                    <div class="col-lg-7">
                        <select id="productUserSelect" onchange="changeProductUid()" name="productUid" class="form-control"></select>
                    </div>
                </div>
                <div class="form-group memberDiv">
                    <label for="memberUid" class="col-lg-3 control-label"><!-- <span id="projectName"></span> -->项目成员：</label>
                    <div class="col-lg-7">
                        <select id="memberUid" name="memberUid" class="form-control member"></select>
                    </div>
                </div>
                <button onclick="addMember()" type="button" class="btn btn-success">添加项目成员</button>
                <button id="memberFormSubmit" type="button" class="btn btn-primary">保存</button>
            </form>
        </div>
    </div>
</div>
<!-- /.col-lg-12 -->

<div id="inputDiv">
</div>
