<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!-- FormValidation CSS file -->
<link rel="stylesheet" href="${ctxPath}/static/formvalidation/css/formValidation.min.css">
<!-- ladda -->
<link rel="stylesheet" href="${ctxPath}/static/ladda-bootstrap/dist/ladda-themeless.min.css">
<!-- FormValidation plugin and the class supports validating Bootstrap form -->
<script src="${ctxPath}/static/formvalidation/js/formValidation.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/framework/bootstrap.min.js"></script>
<script src="${ctxPath}/static/formvalidation/js/language/zh_CN.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<script type="text/javascript">var action = '${action}';</script>
<script type="text/javascript" src="${ctxPath}/js/matter/detail.js"></script>
<style>
#pic{position: absolute; display: none; z-index:999;}
.form-group label:first-of-type{
	color:#a1a1a1;
	font-weight: initial;
}
.form-group label:last-child{
	font-weight: initial;
}
	. new_lab label{
		margin-top: 0 !important;
	}
	.form-horizontal{
		height: auto;
		overflow: auto;
		font-size: 14px;
	}
	#showResultDiv .form-group label{
		padding: 0 !important;
	}
.new_lab{
	height: auto;
	overflow: auto;
}

</style>
<form role="form" class="form-horizontal" id="inputForm" method="post">
    <div style="word-break:keep-all;white-space:nowrap;height: auto;overflow: auto;">
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">项目名称：</label>
	        <label id="projectName" class="col-lg-6">欣享家</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">系统版本：</label>
	        <label id="version" class="col-lg-6">0.0.1</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">问题类型：</label>
	        <label id="matterTypeName" class="col-lg-6">功能BUG</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">　　处理等级：</label>
	        <label id="grade" class="col-lg-6">致命</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">问题反馈人：</label>
	        <label id="feedbackUname" class="col-lg-6">林海鹏</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-6">问题创建人：</label>
	        <label id="createUserName" class="col-lg-6">林海鹏</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-5">创建时间：</label>
	        <label id="createTime" class="col-lg-7">2018-02-30 11:11</label>
	    </div>
	    <div class="form-group col-lg-3">
	        <label class="col-lg-5">　　预计时间：</label>
	        <label id="expectTime" class="col-lg-7">2018-02-30 11:11</label>
	    </div>
    </div>
    <div class="form-group col-lg-12 new_lab">
        <label class="col-lg-2">问题描述：</label>
        <label id="description" class="col-lg-10">哈哈哈</label>
    </div>
    <div class="form-group col-lg-12">
        <label class="col-lg-2">附件：</label>
        <div id="imgDiv" style="float: left;margin: 10px;overflow: hidden;">
		</div>
    </div>
    <!-- <div class="form-group col-lg-12">
        <label class="col-lg-2">预计时间：</label>
        <input type="text" class="form-control form_datetime col-lg-5" readonly="readonly" id="expectTime" name="expectTime" placeholder="提交日期">
        <label id="expectTime" class="col-lg-7">2018-02-30 11:11</label>
    </div> -->
    <!-- 指派问题div -->
    <div id="assignMatterDiv" style="display:none">
	    <div class="form-group col-lg-12">
	        <label class="col-lg-2">指派给：</label>
	        <!-- 
	        <select id="replyUid" name="replyUid" class="col-lg-5" multiple="multiple">
	        </select>
	         -->
	        <div id="replyUidDiv"></div>
	    </div>
	    <div class="form-group col-lg-12">
	        <label class="col-lg-2">解决方案：</label>
	        <textarea id="solution" name="solution" class="form-control col-lg-9" rows="3"></textarea>
	    </div>
    </div>
    <!-- 处理问题div -->
    <div id="handleMatterDiv" style="display:none">
        <div class="form-group col-lg-12">
            <label class="col-lg-2">解决方案：</label>
            <textarea id="solution2" class="form-control col-lg-9" rows="3"></textarea>
        </div>
        <div class="form-group col-lg-12">
            <label class="col-lg-2">附件：</label><input type="hidden" id="attachment" name="attachment">
            <input id="uploadfile" name="files" class="file" type="file" multiple data-min-file-count="1">
        </div>
    </div>
    <!-- 查看结果div -->
    <div id="showResultDiv" style="display:none">
        <div class="form-group col-lg-12">
            <label class=" col-lg-3">解决方案：</label>
            <label class="col-lg-6"></label>
        </div>
    </div>
</form>