<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="common/commonFront.jsp"%>
    <script>
	function countDown(secs,surl){     
	    //alert(surl);     
	    if(--secs>0){     
	        setTimeout("countDown("+secs+",'"+surl+"')",1000);     
	    } else {       
	        location.href=surl;     
	    }     
	}     
	</script>
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
					<span id="jumpTo">3秒后自动回到首页</span>
					<script type="text/javascript">countDown(3,'/hardware');</script>  
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>