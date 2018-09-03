<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
  <head>
    <%@include file="common/commonFront.jsp"%>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <script src="js/slider.js" type="text/javascript"></script> 
  </head>
  <body style="margin:0 auto;">
    <%@include file="header.jsp"%>
    <div style="">
        <div class="product-big-title-area" style="height: 300;background-position:center;">
        </div>
        <div class="container" class="single-product-area" style="padding: 10px 0 150px;">
            <div class="container" style="width:90%;">
	            <div class="row">
	                <!-- 
	                <div class="col-md-4">
	                    <div class="single-sidebar">
	                        <h2 class="sidebar-title">Search Products</h2>
	                        <input id="keywordInput" type="text" placeholder="Search products...">
	                        <input id="formSub" type="submit" value="Search">
	                    </div>
	                    <div id="searchList" class="single-sidebar">
	                        <h2 id="H2Products" class="sidebar-title">Related Products</h2>
	                        <div class="thubmnail-recent">
	                            <img src="images/product-thumb-1.jpg" class="recent-thumb" alt="">
	                            <h2><a href="">Sony Smart TV - 2015</a></h2>
	                            <div class="product-sidebar-price">
	                                <ins>$700.00</ins> <del>$800.00</del>
	                            </div>                             
	                        </div>
	                        <div class="thubmnail-recent">
	                            <img src="images/product-thumb-1.jpg" class="recent-thumb" alt="">
	                            <h2><a href="">Sony Smart TV - 2015</a></h2>
	                            <div class="product-sidebar-price">
	                                <ins>$700.00</ins> <del>$800.00</del>
	                            </div>                             
	                        </div>
	                        <div class="thubmnail-recent">
	                            <img src="images/product-thumb-1.jpg" class="recent-thumb" alt="">
	                            <h2><a href="">Sony Smart TV - 2015</a></h2>
	                            <div class="product-sidebar-price">
	                                <ins>$700.00</ins> <del>$800.00</del>
	                            </div>                             
	                        </div>
	                    </div>
	                </div>
	                 -->
	                <div class="col-md-12">
	                    <div class="product-content-right">
	                        <div class="row">
	                            <div class="col-sm-4">
	                                <div class="product-images">
	                                    <div id="picUrl" class="product-main-img" style="border:2px solid #fc894e">
	                                        <img src="images/product-2.jpg" alt="">
	                                    </div>
	                                </div>
	                                <!-- 
		                            <h3>Product Pictures:</h3>
		                            <div id="slider">
	                                </div>  
	                                 -->
	                            </div>
	                            
	                            <div class="col-sm-4">
	                                <div class="product-inner">
	                                    <h2 id="productName" class="product-name">Sony Smart TV - 2015</h2>
	                                    
	                                    <!-- <div class="product-inner-price">
	                                        <ins id="amount">$700.00</ins> <del id="amountOld">$800.00</del>
	                                    </div>  -->   
	                                    <!-- <div class="product-inner-category">
	                                        <p id="productType">Category: <a href="">Summer</a>. Tags: <a href="">awesome</a>, <a href="">best</a>, <a href="">sale</a>, <a href="">shoes</a>. </p>
	                                    </div>  -->
	                                    <div role="tabpanel">
	                                        <div class="tab-content">
	                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
	                                                <p id="productInfoP"></p>
	                                                <!-- 
	                                                <h2>Product Description</h2>  
	                                                <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tristique, diam in consequat iaculis, est purus iaculis mauris, imperdiet facilisis ante ligula at nulla. Quisque volutpat nulla risus, id maximus ex aliquet ut. Suspendisse potenti. Nulla varius lectus id turpis dignissim porta. Quisque magna arcu, blandit quis felis vehicula, feugiat gravida diam. Nullam nec turpis ligula. Aliquam quis blandit elit, ac sodales nisl. Aliquam eget dolor eget elit malesuada aliquet. In varius lorem lorem, semper bibendum lectus lobortis ac.Mauris placerat vitae lorem gravida viverra. Mauris in fringilla ex. Nulla facilisi. Etiam scelerisque tincidunt quam facilisis lobortis. In malesuada pulvinar neque a consectetur. Nunc aliquam gravida purus, non malesuada sem accumsan in. Morbi vel sodales libero.</p>
	                                                 -->
	                                            </div>
	                                            <div role="tabpanel" class="tab-pane fade" id="profile">
	                                                <h2>Reviews</h2>
	                                                <div class="submit-review">
	                                                    <p><label for="name">Name</label> <input name="name" type="text"></p>
	                                                    <p><label for="email">Email</label> <input name="email" type="email"></p>
	                                                    <div class="rating-chooser">
	                                                        <p>Your rating</p>
	
	                                                        <div class="rating-wrap-post">
	                                                            <i class="fa fa-star"></i>
	                                                            <i class="fa fa-star"></i>
	                                                            <i class="fa fa-star"></i>
	                                                            <i class="fa fa-star"></i>
	                                                            <i class="fa fa-star"></i>
	                                                        </div>
	                                                    </div>
	                                                    <p><label for="review">Your review</label> <textarea name="review" id="" cols="30" rows="10"></textarea></p>
	                                                    <p><input type="submit" value="Submit"></p>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    
	                                </div>
	                            </div>
                                <div class="col-sm-4">
                                    
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
        // $("#singleProductLi").addClass("active");
        
        // 获取商品信息
        var url = location.search;
        if(url) {
            $.post(contextPath+'/product/getDetail.json'+url,
                function(data){
                    // alert(data);
                    console.log(data);
                    $("#picUrl").empty().append('<img src="'+data.picture+'" alt="">');
                    $("#productName").text(data.nameEn);
                    // $("#amount").text("$"+data.amount);
                    // $("#amountOld").text("$"+data.amountOld);
                    // $("#productType").text('Category: ').append('<a href="#">'+data.productType.nameEn+'</a>')
                    // $("#description").text(data.description);
                    var productInfo = "<p style='font-size: 15px'>";
                    /* 
                    productInfo += "Specification:　"+data.specification+"<br />";
                    productInfo += "Packing Quantity:　"+data.packingQuantity+"<br />";
                    productInfo += "Material:　"+data.material+"<br />";
                    productInfo += "Accessories:　"+data.accessories+"<br />";
                    productInfo += "Fitting:　"+data.fitting+"<br />";
                     */
                    productInfo += "Description:　<br />"+data.description+"<br />";
                    //productInfo += "Other:　"+data.other+"<br />";
                    
                    /* for(var j = 0,len = data.productProperties; j < data.productProperties.length; j++){
                        productInfo += data.productProperties[j].paramNameEn+":　"+data.productProperties[j].paramValue+"<br />";
                   	} */
                    productInfo += "</p>";
                    $("#productInfoP").html(productInfo);
                    /* 
                    var productPicturesHtml = '';
                    if (data.pictures != null && data.pictures.length > 0) {
                        var imgs = data.pictures.split(",");
                        for (var i = 0; i < imgs.length; i++) {
                            productPicturesHtml += '<div class="spic"><img src="' + imgs[i] + '" /><a href="' + imgs[i] + '">Picture information</a></div>';
                        }
                        $("#slider").append(productPicturesHtml);
                        $('#slider').slider({ speed: 500 })
					} else {
						productPicturesHtml += '<br/><h5>Currently no...</h5>';
	                    $("#slider").append(productPicturesHtml);
					} */
                },
                'json'
            );
        } else {
        	alert("请选择商品！");
        	location.href="shop.jsp";
		}

        /* 
        // 关联产品
        $.post(contextPath+'/product/randList.json',
            {size: 12},
            function(data){
            	// console.log(data);
            	var relatedList = $(".single-product");
            	// console.log(relatedList.length);
                for (var i = 0; i < relatedList.length; i++) {
                    $(relatedList[i]).find(".photoUrlC").attr("src", data[i].picture);
                    $(relatedList[i]).find(".view-details-link").attr("href", "single-product.jsp?id="+data[i].id);
                    $(relatedList[i]).find(".nameC").attr("href", "single-product.jsp?id="+data[i].id).text(data[i].nameEn);
                    $(relatedList[i]).find("ins").text("$"+data[i].amount);
                    $(relatedList[i]).find("del").text("$"+data[i].amountOld);
				}
            },
            'json'
        );
         */
         
         /* 
        // 关键词随机查询方法
        function getLeftList(keyword){
            $.post(contextPath+'/product/randList.json',
                {keyWord: keyword, size: 3},
                function(data){
                    var html = '<h2 id="H2Products" class="sidebar-title">Related Products</h2>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<div class="thubmnail-recent">';
                        html += '<img src="'+data[i].picture+'" class="recent-thumb" alt="" style="width:64px;height:55px;">';
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
         */
        </script>
  </body>
</html>