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
<script type="text/javascript" src="${ctxPath}/js/product/input.js"></script>
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
    <!-- 默认是上线中 -->
    <div class="col-md-12">
        <%--左边--%>
        <div class="col-md-6 form_me" >
            <div>
                <label class="form-label col-lg-3">产品中文名称</label>
                <input class="form-control1 col-lg-8" id="nameCn" name="nameCn" type="text" />
            </div>
            <div>
                <label class="form-label col-lg-3">产品英文名称</label>
                <input class="form-control1 col-lg-8" id="nameEn" name="nameEn" type="text" />
            </div>
            <div>
                <label class="form-label col-lg-3">产品类型</label>
                <select id="typeSelectSub" name="type" class="form-control1 col-lg-8"></select>
            </div>
            <div>
                <label class="form-label col-lg-3">产品状态</label>
                <select id="statusSelectSub" name="status" class="form-control1 col-lg-8">
                    <option value="1">未上线</option>
                    <option value="2" selected="selected">已上线</option>
                </select>
            </div>
            <div>
                <label class="form-label col-lg-3">产品标题</label>
                <input class="form-control1 col-lg-8" id="material" name="material" type="text" />
            </div>
            <!-- 
            <div>
                <label class="form-label col-lg-3">产品参数:</label>
            </div>
            <div>
                <label class="form-label col-lg-3">名称</label>
                <input class="form-control1 col-lg-8" id="parameterName" name="parameterName" type="text" />
            </div>
            <div>
                <label class="form-label col-lg-3">值</label>
                <input class="form-control1 col-lg-8" id="parameterValue" name="parameterValue" type="text" />
            </div>
             -->
        </div>

        <%--右边--%>
        <div class="col-md-6 form_me">
            <div id="picUrlsDiv" class="col-md-12 form-horizontal border">
	            <label class="col-md-2 form-label" for="uploadfile" style="text-align: right;">产品图</label>
	            <input id="uploadfile" name="files" class="file" type="file" multiple data-min-file-count="1">
	            <input type="hidden" id="picture" name="picture">
            </div>
            
            <div id="picsUrlsDiv" class="col-md-12 form-horizontal border">
                <label class="col-md-2 form-label" for="uploadfile" style="text-align: right;">细节图</label>
                <input id="uploadPics" name="files" class="file" type="file" multiple data-min-file-count="1">
                <button id="clearBtn">
                    <span class="glyphicon glyphicon-trash"></span>
                </button>
                <input type="hidden" id="pictures" name="pictures">
                <div id="picsImgsDiv" ></div>
            </div>
        </div>
    </div>

    <div class="col-md-12 form_me">
        <div style="padding: 0 15px;">
            <label class="form-label col-md-2" style="text-align: right;">产品参数</label>
            <textarea id="description" name="description" class="form-control1 col-md-10" rows="3" style="height: 80px;"></textarea>
        </div>
    </div>

    <div class="col-md-12 form_me">
        <div style="padding: 0 15px;">
            <label class="form-label col-md-2" style="text-align: right;">产品参数西班牙语</label>
            <textarea id="descriptionEs" name="descriptionEs" class="form-control1 col-md-10" rows="3" style="height: 80px;"></textarea>
        </div>
    </div>

    <div class="col-md-12 form_me">
        <div style="padding: 0 15px;">
            <label class="form-label col-md-2" style="text-align: right;">表格数据</label>
            <textarea id="other" name="other" class="form-control1 col-md-10" rows="3" style="height: 80px;"></textarea>
        </div>
    </div>
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