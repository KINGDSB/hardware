<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="common/commonFront.jsp"%>
</head>
<body>
    <%@include file="header.jsp"%>
    <div class="product-big-title-area" style="height: 500px">
        <div class="container" style="">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
				    <h1 style="text-align: left;">出现异常！</h1>
				    <p style="font-size: 25px;text-align: left;">
				        &nbsp;&nbsp;错误信息:
                    </p>
                    <p style="font-size: 25px;text-align: left;">
                        ${exception.message}
                    </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>