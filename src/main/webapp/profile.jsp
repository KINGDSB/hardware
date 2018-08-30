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
			<div class="container" style="width:70%">
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
			                <div role="tabpanel">
			                    <div class="tab-content">
			                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                                        <h2>About Artesano</h2>
			                            <p id="description">In 2008,The Olympic games were successfully held in Beijing, China. At this moment, ARTESANO began a new chapter in manufacturing: specializing in furniture and furniture hardware fittings.
In the past 10 years, our founding belief is offering exceptional quality, at a competitive price, and a dedication to exceptional flexibility for our clients.
</p><br />

                                        <h2>Artesano’s team</h2>
                                        <p id="description">has full export experience through a positive , thoughtful, hardworking, professional business team. Coming alongside our customers to develop the best market for products and maintain bright opportunities for years to come.
In the past 10 years, our team has traveled worldwide and products are continuously adapting to the needs of our customers. At Artesano, we work with a vast array of factories and manufacturers to prove industry leading quality components to the most demanding clients worldwide. With our network of production facilities, not only can provide common components, but also specialty and custom components. It is our goal and privilege to come alongside Clients to build their business’s success and future.
</p>
                                        <h2>Integrity, Dedication to Superior Quality, Creative and Exceptional Customer Service</h2>
                                    </div>
			                        </div>
			                    </div>
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