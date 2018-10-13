<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
<script src="static/jquery.pagination.js"></script>
<link rel="stylesheet" href="css/pagination.css">
<script src="static/tree/js/bootstrap-treeview.js"></script>
<link rel="stylesheet" href="static/tree/css/bootstrap-treeview.css">
<style type="text/css">
.product-upper {
	width: 200px;
	height: 200px;
}

.product-upper img {
	width: 100%;
	height: 100%;
}
.bar6 input { border: 2px solid #171c60; border-radius: 5px; background: transparent; top: 0; right: 0; height: 45;width: 200px;}
</style>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div style="">
		<div class="product-big-title-area"
			style="height: 300; background-position: center;">
           <div class="search bar6" style="float: right;width: 20%;height: 45;position: absolute;vertical-align: middle;top: 100;right: 20;"> 
             <input id="searchInp" type="text" placeholder="search product no" style="background-color: white;" data-trigger="manual" data-placement="bottom" data-content="Product does not exist!"/> 
             <button id="searchBtn" type="button" class="btn btn-primary btn-lg" data-loading-text="Loading..." style="height: 45;font-size: 20;background-color: #171c60;">
              <span class="glyphicon glyphicon-search"></span>
             </button> 
           </div> 
        </div>
		<div class="container" class="single-product-area">
			<div class="container" style="width: 90%; padding-top: 20;">
				<div class="row">
					<div class="col-md-12" style="padding: 0">
						<div id="productList"></div>
						<div id="paginationId" class="m-style pagination"
							style="width: 100%; text-align: center;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
		$("#newProductLi").addClass("active");
	     // 清空系列产品页码
	     window.localStorage.setItem("pageNumber",0);
		function getList(type, start, length) {
			$
					.post(
							contextPath + '/product/list.json',
							{
								type : type,
								status : 2,
								newProduct : 1,
								index : start,
								length : length
							},
							function(result) {
								var html = '';
								for (var i = 0; i < result.data.length; i++) {
									html += '<div class="col-md-3 col-sm-6">';
									html += '<div class="single-shop-product" style="background-color: #80808029;">';
									html += '<div class="product-upper">';
									html += '<a style="color: white" href="single-product.jsp?id='
											+ result.data[i].id
											+ '"><img src="'+result.data[i].picture+'" alt="'+result.data[i].picture+'" width="150" height="150"></a>';
									html += '</div>';
									html += '<h2 style="text-align: center;background-color: #171c60;height: 30px;"><a style="color: white" href="single-product.jsp?id='
											+ result.data[i].id
											+ '">'
											+ result.data[i].nameEn
											+ '</a></h2>';
									// html += '<div class="product-carousel-price">';
									// html += '<ins>$'+result.data[i].amount+'</ins> <del>$'+result.data[i].amountOld+'</del>';
									// html += '</div>';
									// html += '<div class="product-option-shop">';
									// html += '<a class="add_to_cart_button" href="single-product.jsp?id='+result.data[i].id+'">show detail</a>';
									// html += '</div>';
									html += '</div>';
									html += '</div>';
								}
								$("#productList").empty();
								$("#productList").append(html);

								$("#paginationId").empty();
								console.log(result.recordsTotal)
								if (result.recordsTotal > length) {
									$('#paginationId')
											.pagination(
													{
														totalData : result.recordsTotal,
														showData : length,
														current : result.index == 0 ? 1
																: result.index,
														// mode: 'fixed',
														coping : true,
														homePage : '首页',
														endPage : '末页',
														prevContent : '上页',
														nextContent : '下页',
														callback : function(api) {
															getList(
																	type,
																	api
																			.getCurrent(),
																	length)
														}
													});
								} else if (result.recordsTotal == 0) {
									$("#paginationId")
											.append(
													"<h2>Currently the series no goods</h2>");
								}
							}, 'json');
		}

		getList(0, 0, 12);
		

        $("#searchBtn").click(function(){
            var name = $("#searchInp").val();
            if (name != null && name != '') {
                $("#searchBtn").button('loading');
                $.get(contextPath+'/product/getIdByName.json?name='+name,
                        function(result){
                            console.log(result);
                            $("#searchBtn").button('reset');
                            if (result!=0) {
                                location.href="/hardware/single-product.jsp?id="+result;
                            } else {
                                $("#searchInp").popover('show');
                                //2秒后消失提示框
                                setTimeout(function () {$("#searchInp").popover('hide');}, 2000);
                            }
                        },
                        'json'
                    );
            } else {
                $("#searchInp").attr('data-content', 'product no is null!');
                $("#searchInp").popover('show');
                //2秒后消失提示框
                setTimeout(function () {$("#searchInp").popover('hide');}, 2000);
            }
        });
	</script>
</body>
</html>