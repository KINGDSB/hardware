<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div style="">
		<div class="product-big-title-area"
			style="height: 300; background-position: center;"></div>
		<div class="container" style="padding: 10px 0 10px;">
			<div class="container" style="width: 90%;">
				<div id="row1" class="row">
					
				</div>
				<div id="row2" class="row">
					
				</div>
				<div class="row">
					<div class="product-images">
						<div id="picUrl" class="product-main-img">
							<img
								src="http://47.254.38.242:8082/files/hardware/20180426/companyPic3.jpg"
								alt="" style="height: 500" width="100%">
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<img
								src="http://47.254.38.242:8082/files/hardware/20180426/companyPic.jpg"
								alt="" style="height: 200; width: 80%;">
						</div>
						<div class="col-md-3">
							<img
								src="http://47.254.38.242:8082/files/hardware/20180426/companyPic2.jpg"
								alt="" style="height: 200; width: 80%;">
						</div>
						<div class="col-md-3">
							<img
								src="http://47.254.38.242:8082/files/hardware/20180426/companyPic2.jpg"
								alt="" style="height: 200; width: 80%;">
						</div>
						<div class="col-md-3">
							<img
								src="http://47.254.38.242:8082/files/hardware/20180426/companyPic2.jpg"
								alt="" style="height: 200; width: 80%;">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
        $("#profileLi").addClass("active");
        // 清空系列产品页码
        window.localStorage.setItem("pageNumber", 0);
        
        if (pageLanguage == 1) {
            $("#row1").append('<h2 align="center">About Artesano</h2><p style="font-size: 15px">In 2008,The Olympic games were successfully held in Beijing, China. At this moment, ARTESANO began a new chapter in manufacturing: specializing in furniture and furniture hardware fittings. In the past 10 years, our founding belief is offering exceptional quality, at a competitive price, and a dedication to exceptional flexibility for our clients.</p><br />');
            $("#row2").append('<h2 align="center">Artesano’s team</h2><p style="font-size: 15px">has full export experience through a positive , thoughtful, hardworking, professional business team. Coming alongside our customers to develop the best market for products and maintain bright opportunities for years to come. In the past 10 years, our team has traveled worldwide and products are continuously adapting to the needs of our customers. At Artesano, we work with a vast array of factories and manufacturers to prove industry leading quality components to the most demanding clients worldwide. With our network of production facilities, not only can provide common components, but also specialty and custom components. It is our goal and privilege to come alongside Clients to build their business’s success and future.</p><h3 align="center">Integrity, Dedication to Superior Quality, Creative and Exceptional Customer Service</h3>');
        } else {
            $("#row1").append('<h2 align="center">Sobre Artesano</h2><p style="font-size: 15px">En 2008, los olímpicos se celebraron con éxito en Beijing, China. En este momento, ARTESANO comenzó un nuevo capítulo en la fabricación: especializarse en herrajes para muebles y profesional  para los accesorios de muebles . En los últimos 10 años, nuestra creencia fundamental es ofrecer una calidad excepcional, a un precio competitivo y una dedicación a la flexibilidad excepcional para nuestros clientes.</p><br />');
            $("#row2").append('<h2 align="center">Grupo de artesano</h2><p style="font-size: 15px">Tiene una experiencia de exportación completa a través de un grupo de negocios positivo, reflexivo, profesional de trabajador . Acompañar a nuestros clientes para desarrollar el mejor mercado para productos y mantener oportunidades brillantes en los próximos años. En los últimos 10 años, nuestro grupo ha viajado por todo el mundo y los productos se adaptan continuamente a las necesidades de nuestros clientes. En Artesano, trabajamos con una amplia gama de fábricas y fabricantes para demostrar los componentes de calidad líderes de la industria a los clientes más exigentes del mundo. Con nuestra red de instalaciones de producción, no solo podemos proporcionar componentes comunes, sino también componentes especiales y personalizados. Nuestro objetivo y privilegio es acompañar a los clientes para construir el éxito y el futuro de su negocio.</p><h3 align="center">Integridad, dedicación a la calidad superior,  creativo y servicio al cliente excepcional.</h3>');
        }
    </script>
</body>
</html>