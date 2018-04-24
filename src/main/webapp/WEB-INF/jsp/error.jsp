<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>错误页面</title>
</head>
<body>
<h1>出错了</h1>
<span id="jumpTo">3秒后自动回到首页</span>
<script type="text/javascript">countDown(3,'/');</script>  
${exception.message}
</body>
</html>