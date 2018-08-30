<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
  <head>
    <%@include file="common/commonFront.jsp"%>
    <script src="static/jquery.pagination.js"></script>
    <link rel="stylesheet" href="css/pagination.css">
    <script src="static/tree/js/bootstrap-treeview.js"></script>
    <link rel="stylesheet" href="static/tree/css/bootstrap-treeview.css">
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
  <body style="margin:0 auto;">
    <%@include file="header.jsp"%>
    <div class="container" style="width: 75%">
	    <div class="product-big-title-area">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-12">
	                    <div class="product-bit-title text-center">
	                        <h2>Products</h2>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="single-product-area">
	        <div class="container">
	            <div class="row">
	            <!-- 
		            <div class="col-md-3">
	                    <h2 class="sidebar-title">Product Series</h2>
	                    <div id="treeview2" class=""></div>
		            </div>
		             -->
		            <div id="productDiv" class="col-md-12">
		                <div id="productList"></div>
			            <div id="paginationId" class="m-style pagination" style="width: 100%;text-align: center;"></div>
		            </div>
	            </div>
	        </div>
	    </div>
	</div>
    <%@include file="footer.jsp"%>
    <script>
        $("#shopLi").addClass("active");

        function getParamString(name) {
            var paramUrl = window.location.search.substr(1);
            var paramStrs = paramUrl.split('&');
            var params = {};
            for(var index = 0; index < paramStrs.length; index++) {
                params[paramStrs[index].split('=')[0]] = decodeURI(paramStrs[index].split('=')[1]);
            }
            return params[name];
        }
         
        function getList(type, start, length){
        	console.log('type'+type)
        	console.log(contextPath+'/product/list.json')
        	$.post(contextPath+'/product/list.json',
       			{type:type,status:2,index:start,length:length},
       			function(result){
       	            console.log(result)
       			    var html = '';
       			    for (var i = 0; i < result.data.length; i++) {
                        html += '<div class="col-md-3 col-sm-6">';
                        html += '<div class="single-shop-product">';
                        html += '<div class="product-upper">';
                        html += '<img src="'+result.data[i].picture+'" alt="'+result.data[i].picture+'" width="150" height="150">';
                        html += '</div>';
                        html += '<h2><a href="single-product.jsp?id='+result.data[i].id+'">'+result.data[i].nameEn+'</a></h2>';
                        // html += '<div class="product-carousel-price">';
                        // html += '<ins>$'+result.data[i].amount+'</ins> <del>$'+result.data[i].amountOld+'</del>';
                        // html += '</div>';
                        html += '<div class="product-option-shop">';
                        html += '<a class="add_to_cart_button" href="single-product.jsp?id='+result.data[i].id+'">show detail</a>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
       			    }
                    $("#productList").empty();
       			    $("#productList").append(html);

                    $("#paginationId").empty();
                    if (result.data.length > length) {
                        $('#paginationId').pagination({
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
                                getList(type, api.getCurrent(), length)
                            }
                        });
					} else if (result.data.length == 0) {
                        $("#paginationId").append("<h2>Currently the series no goods</h2>");
					}
       			},
       			'json'
       		);
        }
        
        console.log(getParamString('id'))
        getList(getParamString('id'), 0, 8);

    </script>
  </body>
</html>