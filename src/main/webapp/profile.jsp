<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
</head>
<body style="margin:0 auto;">
	<%@include file="header.jsp"%>
	<div class="container" style="width: 75%">
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
		<div style="padding: 10px 0 10px;">
			<div class="container" style="width:50%">
	            <div class="row">
			        <div class="col-sm-6">
			            <div class="product-images">
			                <div id="picUrl" class="product-main-img" style="margin-right: 10%">
			                    <img src="http://47.254.38.242:8082/files/hardware/20180426/company.png" alt="" style="float: right; max-width: 432px;">
			                </div>
			            </div>
			        </div>
			        
			        <div class="col-sm-6"style="margin-top: 1%;>
			            <div class="product-inner">
			                <h2 class="product-name" style="background-color: #fc894e">Company Name XXX</h2>
			                <div class="product-inner-category">
			                    <p>
				                    <b>Address:</b> 广东省广州市天河区XXX <br /> 
			                        <b>Postcode:</b> 000000<br /> 
			                        <b>Telephone:</b> 020-00000000<br /> 
			                        <b>Fax:</b> 020-00000000
		                        </p> 
			                </div> 
			                
			                <div role="tabpanel">
			                    <div class="tab-content">
			                        <div role="tabpanel" class="tab-pane fade in active" id="home">
			                            <h2>Company Description</h2>
			                            <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tristique, diam in consequat iaculis, est purus iaculis mauris, imperdiet facilisis ante ligula at nulla. Quisque volutpat nulla risus, id maximus ex aliquet ut. Suspendisse potenti. Nulla varius lectus id turpis dignissim porta.</p>
			                        </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			   </div>
			</div>
		</div>
        <div class="container">
	        <iframe height="40%" width="100%" src="baidumap.html">
	        </iframe>
	    </div>
	</div>
	<%@include file="footer.jsp"%>
    <script>
        $("#profileLi").addClass("active");
    </script>
</body>
</html>