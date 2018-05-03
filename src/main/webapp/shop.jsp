<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
  <head>
    <%@include file="common/commonFront.jsp"%>
    <script src="static/jquery.pagination.js"></script>
    <link rel="stylesheet" href="css/pagination.css">
    <style type="text/css">
        .product-upper{
            width:200px;
            height:200px;
        }
        
        .product-upper img{
            width:100%;
            height:100%;
        }
    </style>
  </head>
  <body>
    <%@include file="header.jsp"%>
    <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>Shop</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="single-product-area">
        <div class="container">
            <div id="productList" class="row">
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="m-style pagination" style="width: 100%;text-align: center;"></div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
    <script>
        $("#shopLi").addClass("active");
        
        function getList(start, length){
        	$.post(contextPath+'/product/list.json',
       			{status:2,index:start,length:length},
       			function(result){
       			    var html = '';
       			    for (var i = 0; i < result.data.length; i++) {
                        html += '<div class="col-md-3 col-sm-6">';
                        html += '<div class="single-shop-product">';
                        html += '<div class="product-upper">';
                        html += '<img src="'+result.data[i].picUrls+'" alt="'+result.data[i].picUrls+'" width="150" height="150">';
                        html += '</div>';
                        html += '<h2><a href="single-product.jsp?id='+result.data[i].id+'">'+result.data[i].nameEn+'</a></h2>';
                        html += '<div class="product-carousel-price">';
                        html += '<ins>$'+result.data[i].amount+'</ins> <del>$'+result.data[i].amountOld+'</del>';
                        html += '</div>';
                        html += '<div class="product-option-shop">';
                        html += '<a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="single-product.jsp?id='+result.data[i].id+'">show detail</a>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
       			    }
                    $("#productList").empty();
       			    $("#productList").append(html);

       		        $('.pagination').pagination({
       		            totalData: result.recordsTotal,
       		            showData: length,
       		            current: result.index==0?1:result.index,
       		           // mode: 'fixed',
       		            coping: true,
       		            homePage: '首页',
       		            endPage: '末页',
       		            prevContent: '上页',
       		            nextContent: '下页',
       		            callback: function (api) {
       		                getList(api.getCurrent(), length)
       		            }
       		        });
       			},
       			'json'
       		);
        }
        
        getList(0,12);
    </script>
  </body>
</html>