<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
</head>
<body style="margin:0 auto;width: 60%">
	<%@include file="header.jsp"%>
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
            <div class="row" id="contactP">
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
            </div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
    <script>
        $("#contactLi").addClass("active");
    </script>
</body>
</html>