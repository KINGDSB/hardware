<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
</head>
<body>
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
	<div class="single-product-area">
		<div class="container" style="width:50%">
			<div class="row">
                <div class="woocommerce " >
                    <div class="woocommerce-info">
                        Company Profile:  
                        <a class="showlogin" data-toggle="collapse" href="#companyProfile" aria-expanded="false" aria-controls="companyProfile">Show Details</a>
                    </div>
                    <div id="companyProfile" class="login collapse" method="post">
                        <p>${staticData["companyProfile"].dataValueEn }</p>
                    </div>

                    <div class="woocommerce-info">
                        Company Values:  
                        <a class="showcoupon" data-toggle="collapse" href="#companyValues" aria-expanded="false" aria-controls="companyValues">Show Details</a>
                    </div>
                    <div id="companyValues" class="checkout_coupon collapse">
                        <p>${staticData["companyValues"].dataValueEn }</p>
                    </div>
                    
                    <div class="woocommerce-info">
                        Global Cooperation:  
                        <a class="showcoupon" data-toggle="collapse" href="#globalCooperation" aria-expanded="false" aria-controls="globalCooperation">Show Details</a>
                    </div>
                    <div id="globalCooperation" class="checkout_coupon collapse">
                        <p>${staticData["globalCooperation"].dataValueEn }</p>
                    </div>
                </div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
    <script>
        $("#profileLi").addClass("active");

        // 关键词随机查询方法
        function getLeftList(keyword){
            $.post(contextPath+'/product/randList.json',
                {keyWord: keyword, size: 4},
                function(data){
                    var html = '<h2 id="H2Products" class="sidebar-title">Products</h2>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<div class="thubmnail-recent">';
                        html += '<img src="'+data[i].picUrls+'" class="recent-thumb" alt="" style="width:64px;height:55px;">';
                        html += '<h2><a href="single-product.jsp?id='+data[i].id+'">'+data[i].nameEn+'</a></h2>';
                        html += '<div class="product-sidebar-price">';
                        html += '<ins>$'+data[i].amount+'</ins> <del>$'+data[i].amountOld+'</del>';
                        html += '</div>';
                        html += '</div>';
                    }
                    $("#searchList").empty();
                    $("#searchList").append(html);
                },
                'json'
            );
        }
        
        getLeftList('');
        
        $("#formSub").click(function(){
            getLeftList($("#keywordInput").val());
        })
    </script>
</body>
</html>