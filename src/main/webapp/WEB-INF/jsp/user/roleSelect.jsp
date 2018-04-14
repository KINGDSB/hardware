<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<!-- ladda -->
<link rel="stylesheet" href="${ctxPath}/static/ladda-bootstrap/dist/ladda-themeless.min.css">
<link rel="stylesheet" href="${ctxPath}/static/picklist/pickList.css">

<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>
<script src="${ctxPath}/static/picklist/pickList.js"></script>
<script type="text/javascript" src="${ctxPath}/js/user/role.select.js"></script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="selectRole" data-backdrop="" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="formModalLabel">
               用户角色配置
            </h4>
         </div>
         <div id="pickList" class="modal-body">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
            </button>
            <button id="selectRoleSubmit" class="btn btn-primary ladda-button" data-style="expand-right"><span class="ladda-label">提交</span></button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->