<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>


<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="css/vm-carousel.css">
<script src="js/jquery.vm-carousel.js"></script>
<script src="js/modernizr.js"></script>
<style type="text/css">
.vmc-centered {
	padding: 10px 0;
}

.vmc-centered img {
	transition: all 0.3s ease;
}

.vmc-centered .vmc_active img {
	transform: scale(1.2);
}

.vmc-viewport img {
	border-style: groove
}
</style>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div class="slider-area">
		<!-- <div class="zigzag-bottom"></div> -->
		<div id="slide-list" class="carousel carousel-fade slide"
			data-ride="carousel">
			<div class="slide-bulletz">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<ol class="carousel-indicators slide-indicators">
								<li data-target="#slide-list" data-slide-to="0" class="active"></li>
								<li data-target="#slide-list" data-slide-to="1"></li>
								<li data-target="#slide-list" data-slide-to="2"></li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<div class="single-slide">
						<div class="slide-bg"
							style="background-image: url(${staticData eq null? 'http://47.254.38.242:8082/files/hardware/20190228/slide-11.jpg': staticData["indexInfoImg1"].dataValueEn})"></div>
						<div class="slide-text-wrapper">
							<div class="slide-text">
								<%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle1"].dataValueEn }</h2>
                                                <p>
                                                    ${staticData["indexInfoText1"].dataValueEn }
                                                </p>
                                                <a href="${staticData["indexInfoHref1"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
							</div>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="single-slide">
						<div class="slide-bg"
							style="background-image: url(${staticData eq null? 'http://47.254.38.242:8082/files/hardware/20190228/slide-22.jpg': staticData["indexInfoImg2"].dataValueEn})"></div>
						<div class="slide-text-wrapper">
							<div class="slide-text">
								<%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle2"].dataValueEn }</h2>
                                                <p>${staticData["indexInfoText2"].dataValueEn }</p>
                                                <a href="${staticData["indexInfoHref2"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
							</div>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="single-slide">
						<div class="slide-bg"
							style="background-image: url(${staticData eq null? 'http://47.254.38.242:8082/files/hardware/20190228/slide-33.jpg': staticData["indexInfoImg3"].dataValueEn})"></div>
						<div class="slide-text-wrapper">
							<div class="slide-text">
								<%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle3"].dataValueEn }</h2>
                                                <p>${staticData["indexInfoText3"].dataValueEn }</p>
                                                <a href="${staticData["indexInfoHref3"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End slider area -->
	<div class="container">
		<div style="margin-top: 2%;">
			<div class="container" style="padding: 1; width: 100%;">
				<!-- <h2>Products Series:</h2> -->
				<!-- 
	           <ul class="vmcarousel-centered vmc-centered">
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/02119.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/06401.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/05411.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/02135.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/06103.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/ce2c7a70-fa1a-4690-88b1-151ef04ab216.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/01107.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/05411.jpg" alt="" style="height:200;width:200" ></li>
	              <li><img src="http://47.254.38.242:8082/files/hardware/20180426/02135.jpg" alt="" style="height:200;width:200" ></li>
	           </ul>
	            -->
				<div class="col-md-3" style="padding: 0;">
					<a
						href="/hardware/seriesProduct.jsp?id=1"><img
						src="http://47.254.38.242:8082/files/hardware/series/series-01.jpg"
						alt="" style="height: 250; width: 80%;"></a>
				</div>
				<div class="col-md-3" style="padding: 0;">
					<a
						href="/hardware/seriesProduct.jsp?id=2"><img
						src="http://47.254.38.242:8082/files/hardware/series/series-02.jpg"
						alt="" style="height: 250; width: 80%;"></a>
				</div>
				<div class="col-md-3" style="padding: 0;">
					<a
						href="/hardware/seriesProduct.jsp?id=3"><img
						src="http://47.254.38.242:8082/files/hardware/series/series-03.jpg"
						alt="" style="height: 250; width: 80%;"></a>
				</div>
				<div class="col-md-3" style="padding: 0;">
					<a
						href="/hardware/seriesProduct.jsp?id=4"><img
						src="http://47.254.38.242:8082/files/hardware/series/series-04.jpg"
						alt="" style="height: 250; width: 80%;"></a>
				</div>
			</div>
		</div>
		<!-- End promo area -->
		<br />
		<div class="row"
			style="background-color: #F2F2F2; width: 100%; margin: 0px; padding-bottom: 10px; height: 300px;">
			<div class="col-sm-6" style="padding: 0">
				<div class="product-images">
					<div id="picUrl" class="product-main-img" style="">
						<img
							src="http://47.254.38.242:8082/files/hardware/20180426/companyPic.jpg"
							alt="" style="float: right; width: 100%; height: 260px;">
					</div>
				</div>
			</div>
			<div class="col-sm-5" style="margin-top: 1%;">
				<div class="product-inner" style="">
					<h2 id="infoTitle">About Artesano</h2>
					<div class="product-inner-category">
						<p id="description" style="font-size: 15px">In 2008,The
							Olympic games were successfully held in Beijing, China. At this
							moment, ARTESANO began a new chapter in manufacturing:
							specializing in furniture and furniture hardware fittings. In the
							past 10 years, our founding belief is offering exceptional
							quality, at a competitive price, and a dedication to exceptional
							flexibility for our clients.</p>
					</div>

					<div role="tabpanel">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" >
								<h2 id="infoText2" >Integrity, Dedication to Superior Quality, Creative and
									Exceptional Customer Service</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
      $("#indexLi").addClass("active");
      // 清空系列产品页码
      window.localStorage.setItem("pageNumber",0);
      
      if (pageLanguage == 1) {
          $("#infoTitle").text("About Artesano");
          $("#description").text("In 2008,The Olympic games were successfully held in Beijing, China. At this moment, ARTESANO began a new chapter in manufacturing: specializing in furniture and furniture hardware fittings. In the past 10 years, our founding belief is offering exceptional quality, at a competitive price, and a dedication to exceptional flexibility for our clients.");
          $("#infoText2").text("Integrity, Dedication to Superior Quality, Creative and Exceptional Customer Service");
      } else {
          $("#infoTitle").text("Sobre Artesano");
          $("#description").text("En 2008, los olímpicos se celebraron con éxito en Beijing, China. En este momento, ARTESANO comenzó un nuevo capítulo en la fabricación: especializarse en herrajes para muebles y profesional  para los accesorios de muebles . En los últimos 10 años, nuestra creencia fundamental es ofrecer una calidad excepcional, a un precio competitivo y una dedicación a la flexibilidad excepcional para nuestros clientes.Grupo de artesano.");
          $("#infoText2").text("Integridad, dedicación a la calidad superior,  creativo y servicio al cliente excepcional.");
      }
 /* 
      jQuery(function($) {
		$('.vmcarousel-centered').vmcarousel({
			centered : true,
			start_item : 2,
			autoplay : false,
			infinite : false
		});

		$('.vmcarousel-centered-infitine').vmcarousel({
			centered : true,
			start_item : 1,
			autoplay : false,
			infinite : true
		});

		$('.vmcarousel-normal').vmcarousel({
			centered : false,
			start_item : 0,
			autoplay : false,
			infinite : false
		});
	});
       */
	</script>
</body>
</html>