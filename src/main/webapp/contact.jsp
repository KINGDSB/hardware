<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
<style type="text/css">

.divcss5-F00{ border-bottom:3px solid #fb0;} 
/* CSS注释说明：.divcss5-F00 设置对象下边框为红色 */ 

#contactP {
    /* color: transparent; */
    font-size: 20px;
    /* text-align: center; */
    margin-left: 50px;
}
</style>
</head>
<body style="margin:0 auto;">
	<%@include file="header.jsp"%>
    <div class="container" style="width: 75%">
		<div class="product-big-title-area">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="product-bit-title text-center">
							<h2>Contact us</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="padding: 80px 0 100px;">
			<div class="container">
	            <div class="row">
		            <div class="col-md-4" id="contactP">
		                <h2 class="divcss5-F00" style="width: 200px">Company Info</h2>
			            <p> Name: artesano</p> 
						<p> Address: 广东省广州市天河区XXX</p> 
						<p> Postcode: 000000</p> 
						<p> Telephone: 020-00000000</p> 
						<p> Fax: 020-00000000</p> 
		            <!-- 
		                <div class="col-md-3 col-sm-6">
		                    <div class="single-promo" style="background-image: url('images/icon1.png'); width:200px; height:200px">
		                        <p>Phone Number:</p>
		                        <p>135XXXXXXXX</p>
		                        <p>135XXXXXXXX</p>
		                        <p>020-xxxxxxxx</p>
		                    </div>
		                </div>
		                <div class="col-md-3 col-sm-6">
		                    <div class="single-promo" style="background-image: url('images/icon2.png'); width:200px; height:200px">
		                        <p>Maintain Address:</p>
		                        <p>中国广东省广州市XXXXXXXX</p>
		                    </div>
		                </div>
		                <div class="col-md-3 col-sm-6">
		                    <div class="single-promo" style="background-image: url('images/FAQ.png'); width:200px; height:200px">
		                        <p>FQA:</p>
		                        <p>........</p>
		                        <p>........</p>
		                    </div>
		                </div>
		                <div class="col-md-3 col-sm-6">
		                    <div class="single-promo" style="background-image: url('images/icon4.png'); width:200px; height:200px">
		                        <p>Other Question:</p>
		                        <p>........</p>
		                        <p>........</p>
		                    </div>
		                </div>
		                 -->
		            </div>
		            <div class="col-md-7" style="width:600;height:350;border:3px solid #33d882;">
		               <h3>在百度地图上显示公司位置需要注册百度账号并配置公司的定位信息 可参考<a href="https://jingyan.baidu.com/article/870c6fc37966fdb03fe4beec.html" target="_blank">添加商铺定位</a></h3>
		               <br>
		               <h3>谷歌地图点<a href="https://support.google.com/business/answer/6174435?hl=zh-Hans" target="_blank">这里</a>配置公司定位信息(需翻墙)</h3>
		            </div>
	            </div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
    <script>
        $("#contactLi").addClass("active");
    </script>
</body>
</html>