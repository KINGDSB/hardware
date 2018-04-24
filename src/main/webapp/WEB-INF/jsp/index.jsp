<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">

<head>
    <%@include file="/common/common.jsp"%>
    <link rel="Shortcut Icon" href="/center/images/favicon_48X48.ico" type="image/x-icon" /> 
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Morris Charts CSS -->
    <link href="${ctxPath}/static/morrisjs/morris.css" rel="stylesheet">

    <script type="text/javascript" src="${ctxPath}/js/index.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="${ctxPath}/static/raphael/raphael-min.js"></script>
    <script src="${ctxPath}/static/morrisjs/morris.min.js"></script>
    <script src="${ctxPath}/static/morrisjs/morris-data.js"></script>
    <style>
        .content-title{
            padding-left: 15px;
            font-size: 18px;
        }
        #div_logout{
            float: right;
            width: 150px;
            height: 20px;
            margin-top: 0.5%;
        }
        .waitHandleClass{
            background-image: url(/center/images/waitHandle.png);
            background-position-x: initial;
            background-position-y: initial;
            background-size: 100% 100%;
            background-repeat-x: initial;
            background-repeat-y: initial;
            background-attachment: initial;
            background-origin: initial;
            background-clip: initial;
            background-color: initial;
            background-size: 100% 100%;
            display: block;
            width: 80px;
            height: 80px;
        }
        .resolvedClass{
            background-image: url(/center/images/resolved.png);
            background-position-x: initial;
            background-position-y: initial;
            background-size: 100% 100%;
            background-repeat-x: initial;
            background-repeat-y: initial;
            background-attachment: initial;
            background-origin: initial;
            background-clip: initial;
            background-color: initial;
            background-size: 100% 100%;
            display: block;
            width: 80px;
            height: 80px;
        }
        .processingClass{
            background-image: url(/center/images/processing.png);
            background-position-x: initial;
            background-position-y: initial;
            background-size: 100% 100%;
            background-repeat-x: initial;
            background-repeat-y: initial;
            background-attachment: initial;
            background-origin: initial;
            background-clip: initial;
            background-color: initial;
            background-size: 100% 100%;
            display: block;
            width: 80px;
            height: 80px;
        }
        table {
            /*为表格设置合并边框模型*/
            border-collapse: collapse;
            /*列宽由表格宽度和列宽度设定*/
            table-layout: fixed;
        }
        tr{font-size:13px;}
        td {
            border: 1px solid #ddd;
            /*允许在单词内换行。*/
            word-break: break-word;
            /*设置宽度*/
            width: 100px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
    </style>
</head>

<body>

<div id="wrapper" >

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/hardware/backindex">商城数据管理</a>
        </div>
        <!-- /.navbar-header -->

        <%-- <ul class="nav navbar-top-links navbar-right" >
             <li class="dropdown">
                 <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                     <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                 </a>
                 <ul class="dropdown-menu dropdown-user">
                     <li><a href="javascript:void(0);" id="modifyPassword"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                     </li>
                     <li class="divider"></li>--%>
        <div id="div_logout">
            ${user.name }　<a href="javascript:void(0);" id="logout" ><i class="fa fa-sign-out fa-fw"></i> 注销</a>
        </div>
        <%--   </li>
       </ul>
       <!-- /.dropdown-user -->
   </li>
   <!-- /.dropdown -->
</ul>--%>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <c:forEach items="${resources}" var="r" varStatus="rs">
                        <c:if test="${r.level == 0}">
                            <li>
                                <a href="#">${r.name}<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <c:forEach items="${resources}" var="sr" varStatus="srs">
                                        <c:if test="${sr.level == 1 && sr.parentId == r.id}">
                                            <li>
                                                <a href="javascript:void(0);" nav-n="${sr.resUrl},${sr.name},${sr.id}">${sr.name}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div id="content" class="row">
        <h1>欢迎登录商城后台管理系统</h1>
        <%-- 
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="waitHandleClass"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${matterStatisticsData['1'] gt 0 ? matterStatisticsData['1'] : 0 }</div>
                                <div>待处理</div>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" onclick="jumpMatterList(0, 1, 0)">
                        <div class="panel-footer">
                            <span class="pull-left">查看</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="processingClass"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${matterStatisticsData['2'] gt 0 ? matterStatisticsData['2'] : 0 }</div>
                                <div>处理中</div>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" onclick="jumpMatterList(0, 2, 0)">
                        <div class="panel-footer">
                            <span class="pull-left">查看</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-yellow">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="resolvedClass"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${matterStatisticsData['5'] gt 0 ? matterStatisticsData['5'] : 0 }</div>
                                <div>已处理</div>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:void(0);" onclick="jumpMatterList(0, 5, 0)">
                        <div class="panel-footer">
                            <span class="pull-left">查看</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            --%>
            <!-- 勿删！ -->
            <div class="col-lg-12" style="display: none;">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div id="morris-area-chart" style="display: none;"></div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
                <div class="panel panel-default">
                    <div class="panel-heading" style="display: none;">
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body" style="display: none;">
                        <div class="row">
                            <div class="col-lg-4">
                            </div>
                            <!-- /.col-lg-4 (nested) -->
                            <div class="col-lg-8">
                                <div id="morris-bar-chart"></div>
                            </div>
                            <!-- /.col-lg-8 (nested) -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <div class="panel panel-default" style="display: none;">
                    <div class="panel-heading">
                        <i class="fa fa-bar-chart-o fa-fw"></i> Donut Chart Example
                    </div>
                    <div class="panel-body">
                        <div id="morris-donut-chart"></div>
                        <a href="#" class="btn btn-default btn-block">View Details</a>
                    </div>
                    <!-- /.panel-body -->
                </div>
            </div>
            <!-- /.col-lg-4 -->
            <%-- 
            <div class="col-lg-12">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="javascript:void(0);" onclick="jumpMatterList(0, 0, 4)">致命问题</a></h3>
                    </div>
                    <table id="dangerTable" class="table">
                        <thead>
                        <tr>
                            <!-- <th>ID</th> -->
                            <th>项目名称</th>
                            <th>事件描述</th>
                            <th>处理等级</th>
                            <th>问题类型</th>
                            <th>提交日期</th>
                            <th>问题反馈人</th>
                            <th>预计完成时间</th>
                            <th>用时(小时)</th>
                            <th>产品经理</th>
                            <th>处理状态</th>
                        </tr>
                        </thead>
                        <c:forEach items="${dangerList}" var="item">
                            <tr>
                                    <td>${item.id}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="jumpMatterList(${item.project.id}, 0, 0)">${item.project.name}</a>
                                </td>
                                <td title="${item.description}">${item.description}</td>
                                <td>
                                    <c:if test="${item.grade == 1}">低级</c:if>
                                    <c:if test="${item.grade == 2}">中级</c:if>
                                    <c:if test="${item.grade == 3}">紧急</c:if>
                                    <c:if test="${item.grade == 4}">致命</c:if>
                                </td>
                                <td>${item.matterType.name}</td>
                                <td><fmt:formatDate value="${item.createdDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${item.feedbackUser.name}</td>
                                <td><fmt:formatDate value="${item.expectTime}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${not empty item.trueTime}">
                                        <c:set var="useTime" value="${item.trueTime.time - item.createdDate.time}"/>
                                        <fmt:formatNumber value="${useTime/1000/60/60}" pattern="#0.0"/>
                                    </c:if>
                                    <c:if test="${empty item.trueTime}">-</c:if>
                                </td>
                                <td>${item.productUser.name}</td>
                                <td>
                                    <c:if test="${item.processStatus == 1}">待处理</c:if>
                                    <c:if test="${item.processStatus == 2}">处理中</c:if>
                                    <c:if test="${item.processStatus == 3}">待确认</c:if>
                                    <c:if test="${item.processStatus == 4}">未解决</c:if>
                                    <c:if test="${item.processStatus == 5}">已解决</c:if>
                                    <c:if test="${item.processStatus == 6}">申请延期中</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="javascript:void(0);" onclick="jumpMatterList(0, 0, 3)">紧急问题</a></h3>
                    </div>
                    <table id="warningTable" class="table">
                        <thead>
                        <tr>
                            <!-- <th>ID</th> -->
                            <th>项目名称</th>
                            <th>事件描述</th>
                            <th>处理等级</th>
                            <th>问题类型</th>
                            <th>提交日期</th>
                            <th>问题反馈人</th>
                            <th>预计完成时间</th>
                            <th>用时(小时)</th>
                            <th>产品经理</th>
                            <th>处理状态</th>
                        </tr>
                        </thead>
                        <c:forEach items="${warningList}" var="item">
                            <tr>
                                    <td>${item.id}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="jumpMatterList(${item.project.id}, 0, 0)">${item.project.name}</a>
                                </td>
                                <td title="${item.description}">${item.description}</td>
                                <td>
                                    <c:if test="${item.grade == 1}">低级</c:if>
                                    <c:if test="${item.grade == 2}">中级</c:if>
                                    <c:if test="${item.grade == 3}">紧急</c:if>
                                    <c:if test="${item.grade == 4}">致命</c:if>
                                </td>
                                <td>${item.matterType.name}</td>
                                <td><fmt:formatDate value="${item.createdDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${item.feedbackUser.name}</td>
                                <td><fmt:formatDate value="${item.expectTime}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${not empty item.trueTime}">
                                        <c:set var="useTime" value="${item.trueTime.time - item.createdDate.time}"/>
                                        <fmt:formatNumber value="${useTime/1000/60/60}" pattern="#0.0"/>
                                    </c:if>
                                    <c:if test="${empty item.trueTime}">-</c:if>
                                </td>
                                <td>${item.productUser.name}</td>
                                <td>
                                    <c:if test="${item.processStatus == 1}">待处理</c:if>
                                    <c:if test="${item.processStatus == 2}">处理中</c:if>
                                    <c:if test="${item.processStatus == 3}">待确认</c:if>
                                    <c:if test="${item.processStatus == 4}">未解决</c:if>
                                    <c:if test="${item.processStatus == 5}">已解决</c:if>
                                    <c:if test="${item.processStatus == 6}">申请延期中</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="javascript:void(0);" onclick="jumpMatterList(0, 0, 2)">中级问题</a></h3>
                    </div>
                    <table id="defaultTable" class="table">
                        <thead>
                        <tr>
                            <!-- <th>ID</th> -->
                            <th>项目名称</th>
                            <th>事件描述</th>
                            <th>处理等级</th>
                            <th>问题类型</th>
                            <th>提交日期</th>
                            <th>问题反馈人</th>
                            <th>预计完成时间</th>
                            <th>用时(小时)</th>
                            <th>产品经理</th>
                            <th>处理状态</th>
                        </tr>
                        </thead>
                        <c:forEach items="${defaultList}" var="item">
                            <tr>
                                    <td>${item.id}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="jumpMatterList(${item.project.id}, 0, 0)">${item.project.name}</a>
                                </td>
                                <td title="${item.description}">${item.description}</td>
                                <td>
                                    <c:if test="${item.grade == 1}">低级</c:if>
                                    <c:if test="${item.grade == 2}">中级</c:if>
                                    <c:if test="${item.grade == 3}">紧急</c:if>
                                    <c:if test="${item.grade == 4}">致命</c:if>
                                </td>
                                <td>${item.matterType.name}</td>
                                <td><fmt:formatDate value="${item.createdDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${item.feedbackUser.name}</td>
                                <td><fmt:formatDate value="${item.expectTime}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${not empty item.trueTime}">
                                        <c:set var="useTime" value="${item.trueTime.time - item.createdDate.time}"/>
                                        <fmt:formatNumber value="${useTime/1000/60/60}" pattern="#0.0"/>
                                    </c:if>
                                    <c:if test="${empty item.trueTime}">-</c:if>
                                </td>
                                <td>${item.productUser.name}</td>
                                <td>
                                    <c:if test="${item.processStatus == 1}">待处理</c:if>
                                    <c:if test="${item.processStatus == 2}">处理中</c:if>
                                    <c:if test="${item.processStatus == 3}">待确认</c:if>
                                    <c:if test="${item.processStatus == 4}">未解决</c:if>
                                    <c:if test="${item.processStatus == 5}">已解决</c:if>
                                    <c:if test="${item.processStatus == 6}">申请延期中</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        --%>
        </div> 
    </div>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="alertModalLabel">
                    提示
                </h4>
            </div>
            <div id="showMessage" class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" id="closeAlertModal" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button id="confirmBtn" type="button" class="btn btn-primary">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="formModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:1100px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="formModalLabel">
                    添加用户
                </h4>
            </div>
            <div id="formBody" class="modal-body">
            </div>
            <div class="modal-footer">
                <div id="buttons">
	                <button id="formModalClose" type="button" class="btn btn-default" data-dismiss="modal">关闭
	                </button>
	                <button id="formSubmit" class="btn btn-primary ladda-button" data-style="expand-right"><span class="ladda-label">提交</span></button>
                </div>
                <div id="matterButtons"></div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
