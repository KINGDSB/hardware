<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/6
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
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
<script type="text/javascript" src="${ctxPath}/js/matter/input.js"></script>
<!-- ladda -->
<script src="${ctxPath}/static/ladda-bootstrap/dist/spin.min.js"></script>
<script src="${ctxPath}/static/ladda-bootstrap/dist/ladda.min.js"></script>

<style>
    input,select{
        border-color: #dadada;
        width: 50%;
    }
    .form-horizontal{
        height: auto;
        overflow: auto;
    }
    .form_me{
        font-size: 14px;
    }
.form_me label{
    margin: 0;
    line-height: 34px;
}
.form_me > div{
    height: auto;
    overflow: auto;
    margin-bottom: 10px;
}
.form_me > div label:first-of-type{
    text-align: right;
    font-weight: initial;
    font-size: 14px;
}
    .border{
        border:1px solid #dadada;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        overflow: initial !important;
        margin-top: 15px;
        padding: 0 25px;
    }
    .form-control1{
        display: block;
        height: 34px;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
</style>
<form role="form" class="form-horizontal" id="inputForm" method="post">

    <div class="col-md-12">
        <%--左边--%>
        <div class="col-md-6 form_me" >
            <div>
                <label class="form-label col-lg-3">项目名称</label>
                <select id="projectSelectSub" name="projectId" onchange="getProjectInfo()" class="form-control1 col-lg-8"></select>
                <input type="hidden" id="productUid" name="productUid">
            </div>

            <div>
                <label class="form-label col-lg-3">系统版本</label>
                <input class="form-control1 col-lg-8" id="version" name="version" type="text" readonly="readonly"/>
            </div>

            <div>
                <label class="form-label col-lg-3">问题类型</label>
                <select id="typeSub" name="type" class="form-control1 queryClass col-lg-8"></select>
            </div>

            <div>
                <label class="form-label col-lg-3">处理等级</label>
                <select id="grade" name="grade" class="form-control1 queryClass col-lg-8">
                    <option value="">请选择</option>
                    <option value="1">低级</option>
                    <option value="2">中级</option>
                    <option value="3">紧急</option>
                    <option value="4">致命</option>
                </select>
            </div>

            <div>
                <label class="form-label col-lg-3">问题反馈人</label>
                <input class="form-control1 col-lg-8" id="feedbackUname" name="feedbackUname" onblur="checkUser()" type="text"/>
                <input id="feedbackUid" name="feedbackUid" type="hidden"/>
            </div>

        </div>

        <%--右边--%>
        <div class="col-md-6 form_me">
            <div class="col-md-12 form-horizontal border">
                <div class="col-md-12" style="text-align: center;padding:0 -10px;margin-top: -20px;font-weight: bold;line-height: 40px;"><p class="col-md-3" style="background-color: white;">项目成员</p></div>
                <div id="pmembers" style="margin-bottom: 10px;">
                    <span>产品经理: ---</span><br/>
                    <span>普通成员: ---</span><br/>
                </div>
            </div>

        </div>
    </div>

    <div class="col-md-12 form_me">
        <div style="padding: 0 15px;">
            <label class="form-label col-md-2" style="text-align: right;">问题描述</label>
            <textarea id="description" name="description" class="form-control1 col-md-10" rows="3" style="height: 80px;"></textarea>
        </div>

        <div style="padding: 0 15px;">
            <label class="col-md-2 form-label" for="description" style="text-align: right;">附件</label>
            <input id="uploadfile" name="files" class="file" type="file" multiple data-min-file-count="1">
            <input type="hidden" id="attachment" name="attachment">
        </div>
    </div>



    <%--<table class="col-md-12" cellspacing="10">--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><label class="form-label">项目名称</label><input type="hidden" id="productUid" name="productUid"></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><select id="projectSelectSub" name="projectId" onchange="getProjectInfo()" class="form-control"></select></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td rowspan="5">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<fieldset class="col-md-12 form-horizontal" style="border-width: 1px; border-color: #666666">&ndash;%&gt;--%>
				  <%--&lt;%&ndash;<legend>项目成员</legend>&ndash;%&gt;--%>
				  <%--&lt;%&ndash;<div id="pmembers">&ndash;%&gt;--%>
	                  <%--&lt;%&ndash;<span>产品经理: ---</span><br/>&ndash;%&gt;--%>
	                  <%--&lt;%&ndash;<span>普通成员: ---</span><br/>&ndash;%&gt;--%>
				  <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
			    <%--&lt;%&ndash;</fieldset>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><label class="form-label">系统版本</label></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><input class="form-control" id="version" name="version" type="text" readonly="readonly"/></td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><label class="form-label">问题类型</label></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><select id="typeSub" name="type" class="form-control queryClass"></select></td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><label class="form-label">处理等级</label></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<select id="grade" name="grade" class="form-control queryClass">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="1">低级</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="2">中级</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="3">紧急</option>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<option value="4">致命</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td><label class="form-label">问题反馈人</label></td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input id="feedbackUid" name="feedbackUid" type="hidden"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input class="form-control" id="feedbackUname" name="feedbackUname" onblur="checkUser()" type="text"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<!-- <select id="feedbackUid" name="feedbackUid" class="form-control queryClass"></select> -->&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--<tr>--%>
            <%--<td><label class="form-label">问题描述</label></td>--%>
            <%--<td colspan="2"><textarea id="description" name="description" class="form-control" rows="3"></textarea></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label class="col-md-5 form-label" for="description">附件</label><input type="hidden" id="attachment" name="attachment"></td>--%>
            <%--<td colspan="2"><input id="uploadfile" name="files" class="file" type="file" multiple data-min-file-count="1"></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
</form>
<style>
form fieldset { 
    background: rgba(255,255,255,.3); 
    border-color: rgba(255,255,255,.6); 
    border-style: solid; 
    border-width: 2px; 
    -moz-border-radius: 5px; 
    -webkit-border-radius: 5px; 
    -khtml-border-radius: 5px; 
    border-radius: 5px; 
    line-height: 30px; 
    list-style: none; 
    padding: 5px 10px; 
    margin-bottom: 2px; 
} 
form fieldset legend { 
    color:#302A2A; 
    font: bold 16px/2 Verdana, Geneva, sans-serif; 
    font-weight: bold; 
    text-align: left; 
    text-shadow: 2px 2px 2px rgb(88, 126, 156); 
}
</style>