<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
<style type="text/css">
.divcss5-F00 {
	border-bottom: 3px solid #fb0;
}
/* CSS注释说明：.divcss5-F00 设置对象下边框为红色 */
#contactP {
	/* color: transparent; */
	font-size: 20px;
	/* text-align: center; */
	margin-left: 50px;
}
</style>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div style="">
		<div class="product-big-title-area"
			style="height: 300; background-position: center;"></div>
		<div class="container" style="padding: 10px 0 150px;">
			<div class="container" style="width: 90%;">
				<div class="row">
                    <div class="col-md-10" id="contactP"></div>
					<div class="col-md-7">
						<!-- 
			            <iframe height="60%" width="100%" src="baidumap.html">
			            </iframe>
			             -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
        $("#contactLi").addClass("active");
        // 清空系列产品页码
        window.localStorage.setItem("pageNumber",0);
        
        if (pageLanguage == 1) {
            $("#contactP").append('<br /><h3 class="divcss5-F00" style="width: 350px">ARTESANO INDUSTRIAL LIMITED</h3><p>Tel: 020-8621-1986</p><p>Email: info@artesano-ind.com</p><p>Website: www.artesano-ind.com</p><p>Add: No.1115-1116，A Tower, CaiRun international building, DongGuanZhuang Road No.2, TianHe District, Guangzhou, China.</p><br /><p>THANKS YOUR VISIT, WISH YOU HAVE A GOOD TIME.</p>');
        } else {
            $("#contactP").append('<br /><h3 class="divcss5-F00" style="width: 350px">ARTESANO INDUSTRIAL LIMITED</h3><p>Tel: 020-8621-1986</p><p>Correo electrónico: info@artesano-ind.com</p><p>Web: www.artesano-ind.com</p><p>Add: No.1115-1116，A Tower, CaiRun international building, DongGuanZhuang Road No.2, TianHe District, Guangzhou, China.</p><br /><p>GRACIAS A SU VISITA, DESEA QUE USTED TIENE UN BUEN MOMENTO.</p>');
        }
    </script>
</body>
</html>