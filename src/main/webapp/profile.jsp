<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
</head>
<body style="margin:0 auto;width: 80%">
	<%@include file="header.jsp"%>
	<div class="product-big-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="product-bit-title text-center">
						<h2>Company Profile</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="padding: 40px 0 40px;">
		<div class="container" style="width:50%">
            <div class="row"style="margin-top: 1%;margin-bottom: 1%;">
		        <div class="col-sm-4">
		            <div class="product-images">
		                <div id="picUrl" class="product-main-img" style="margin-right: 10%">
		                    <img src="http://47.254.38.242:8082/files/hardware/20180426/company.png" alt="" style="float: right; max-width: 432px;">
		                </div>
		            </div>
		        </div>
		        
		        <div class="col-sm-8"style="margin-top: 1%;>
		            <div class="product-inner">
		                <h2 class="product-name" style="background-color: #fc894e">Company Name XXX</h2>
		                <div class="product-inner-category">
		                    <p id="productType">Company Tags: <a href="">awesome</a>, <a href="">best</a>, <a href="">sale</a>, <a href="">shoes</a>. </p>
		                </div> 
		                
		                <div role="tabpanel">
		                    <div class="tab-content">
		                        <div role="tabpanel" class="tab-pane fade in active" id="home">
		                            <h2>Company Description</h2>
		                            <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tristique, diam in consequat iaculis, est purus iaculis mauris, imperdiet facilisis ante ligula at nulla. Quisque volutpat nulla risus, id maximus ex aliquet ut. Suspendisse potenti. Nulla varius lectus id turpis dignissim porta. Quisque magna arcu, blandit quis felis vehicula, feugiat gravida diam. Nullam nec turpis ligula. Aliquam quis blandit elit, ac sodales nisl. Aliquam eget dolor eget elit malesuada aliquet. In varius lorem lorem, semper bibendum lectus lobortis ac.Mauris placerat vitae lorem gravida viverra. Mauris in fringilla ex. Nulla facilisi. Etiam scelerisque tincidunt quam facilisis lobortis. In malesuada pulvinar neque a consectetur. Nunc aliquam gravida purus, non malesuada sem accumsan in. Morbi vel sodales libero.</p>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
    <script>
        $("#profileLi").addClass("active");
    </script>
</body>
</html>