<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
<head>
<%@include file="common/commonFront.jsp"%>
<script src="static/tree/js/bootstrap-treeview.js"></script>
<link rel="stylesheet" href="static/tree/css/bootstrap-treeview.css">
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="js/slider.js" type="text/javascript"></script>
<style>
.magnify-modal {
  box-shadow: 0 0 6px 2px rgba(0, 0, 0, 0.3);
}

.magnify-header .magnify-toolbar {
  background-color: rgba(0, 0, 0, .5);
}

.magnify-stage {
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  border-width: 0;
}

.magnify-footer .magnify-toolbar {
  background-color: rgba(0, 0, 0, .5);
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.magnify-header,
.magnify-footer {
  pointer-events: none;
}

.magnify-button {
  pointer-events: auto;
}
</style>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div style="">
		<div class="product-big-title-area"
			style="height: 300; background-position: center; text-align: center;">
           <div class="search bar6" style="float: right;width: 20%;height: 45;position: absolute;vertical-align: middle;top: 100;right: 20;"> 
             <input id="searchInp" type="text" placeholder="search product no" style="background-color: white;" data-trigger="manual" data-placement="bottom" data-content="Product does not exist!"/> 
             <button id="searchBtn" type="button" class="btn btn-primary btn-lg" data-loading-text="Loading..." style="height: 45;font-size: 20;background-color: #171c60;">
              <span class="glyphicon glyphicon-search"></span>
             </button> 
           </div> 
			<h2 id="titleH"
				style="position: relative; bottom: -95%; background-color: #171c60; color: white; left: 20%; width: 60%;">title</h2>
		</div>
		<div class="container" style="width: 60%; padding: 30 0 10;">
			<div class="row" style="text-align: center;">
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
				<div class="col-sm-3">
					<h2 class="sidebar-title" style="margin-bottom: 10px">Product
						Series</h2>
					<div id="treeview2" class="" style="text-align: left;"></div>
				</div>
				<div class="col-sm-9">
					<div class="row">
						<div class="col-sm-5">
							<!-- <h3>Product Picture</h3> -->
							<div class="product-images">
								<div id="picUrl" class="product-main-img">
									<img
										src="http://47.254.38.242:8082/files/hardware/20180426/logo.png"
										alt="">
								</div>
							</div>
						</div>
	
						<div class="col-sm-7">
							<!-- <h3>Commodity display</h3> -->
							<div class="product-inner">
								<!-- <h2 id="productName" class="product-name">Sony Smart TV - 2015</h2> -->
								<div role="tabpanel">
									<div class="tab-content">
										<div id="pinfo" role="tabpanel"
											class="tab-pane fade in active" style="padding-bottom: 5px">
											<p id="productInfoP"
												style="text-align: left; font-size: 14px"></p>
											<!-- 
	                                        <h2>Product Description</h2>  
	                                        <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tristique, diam in consequat iaculis, est purus iaculis mauris, imperdiet facilisis ante ligula at nulla. Quisque volutpat nulla risus, id maximus ex aliquet ut. Suspendisse potenti. Nulla varius lectus id turpis dignissim porta. Quisque magna arcu, blandit quis felis vehicula, feugiat gravida diam. Nullam nec turpis ligula. Aliquam quis blandit elit, ac sodales nisl. Aliquam eget dolor eget elit malesuada aliquet. In varius lorem lorem, semper bibendum lectus lobortis ac.Mauris placerat vitae lorem gravida viverra. Mauris in fringilla ex. Nulla facilisi. Etiam scelerisque tincidunt quam facilisis lobortis. In malesuada pulvinar neque a consectetur. Nunc aliquam gravida purus, non malesuada sem accumsan in. Morbi vel sodales libero.</p>
	                                         -->
										</div>
										<table border="2" style="width: 100%; text-align: center;border-collapse:collapse">
											<thead id="tableHead" style="background-color: #656cb0;">
												<!-- <tr><td>Item No</td><td>Size(mm)</td></tr> -->
											</thead>
											<tbody id="tableBody">
												<!-- <tr><td>1101.214</td><td>214*214</td></tr>
	                                            <tr><td>1101.246</td><td>246*246</td></tr>
	                                            <tr><td>1101.278</td><td>278*278</td></tr>
	                                            <tr><td>1101.214</td><td>214*214</td></tr>
	                                            <tr><td>1101.214</td><td>214*214</td></tr> -->
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-12">
							<h2 style="text-align: left;">Details Picture:</h2>
							<div id="slider"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
        // $("#singleProductLi").addClass("active");
        
        $.ajax({
            type: "Post",
            url: contextPath+'/product/productSeriesTree.json?pageLanguage='+pageLanguage,
            dataType: "json",
            success: function (result) {
                $('#treeview2').treeview({
                    levels: 1,
                    highlightSelected: true,    //是否高亮选中
                    data: result,         // 数据源
                    //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                    //nodeIcon: 'glyphicon glyphicon-globe',
                    //emptyIcon: '',    //没有子节点的节点图标
                    onNodeSelected: function (event, data) {
                        // alert(data.id);
                        // 清空系列产品页码
                        window.localStorage.setItem("pageNumber",0);
                    	location.href="/hardware/seriesProduct.jsp?id="+data.id;
                    }
                });
                $('#treeview2').treeview('collapseAll');
                //$(".icon").css("display", "none");
            },
            error: function () {
                //alert("树形结构加载失败！");
                console.log("树形结构加载失败！")
            }
        });
        
        // 获取商品信息
        var url = location.search;
        if(url) {
            $.post(contextPath+'/product/getDetail.json'+url,
                function(data){
                    // alert(data);
                    console.log(data);
                    
                    $("#picUrl").empty().append('<a data-magnify="gallery" href="'+data.picture+'"><img width="200" src="'+data.picture+'" style="width: 100%;border: 2px solid #171c60;"></a>');
                    $("#productName").text(data.nameEn);
                    $("#titleH").text(data.material);
                    var productInfo = "";
                     if (pageLanguage == 1) {
                         $("#productInfoP").append(data.description);
                   	} else {
                        $("#productInfoP").append(data.descriptionEs);
                   	}
                    
                    var tableInfos = data.other;
                    if (undefined != tableInfos && null != tableInfos && '' != tableInfos) {
                    	tableInfos = tableInfos.substring(0, tableInfos.lastIndexOf("<br/>"));
                    	var tableRows = tableInfos.split("<br/>");
                        var tableHeadRows = tableRows[0].split(":");
                        var tableHeadHtml = "<tr>";
                        for (var i=0;i<tableHeadRows.length;i++) {
                            if (undefined != tableHeadRows[i] && null != tableHeadRows[i] && '' != tableHeadRows[i]) {
                                tableHeadHtml += "<td>"+tableHeadRows[i]+"</td>";
                            }
                        }
                        tableHeadHtml += "</tr>";
                        $("#tableHead").append(tableHeadHtml);
                        
                        var tableBodyHtml = "";
                        for (var i=1;i<tableRows.length;i++ ) {
                            if (undefined != tableRows[i] && null != tableRows[i] && '' != tableRows[i] && 0 != tableRows[i].length) {
                                tableBodyHtml += "<tr>";
                                var tableCols = tableRows[i].split(":");
                                for (var j=0;j<tableCols.length;j++ ) {
                                    if (undefined != tableCols[j] && null != tableCols[j] && '' != tableCols[j]) {
                                        tableBodyHtml += "<td>"+tableCols[j]+"</td>";
                                    }
                                }
                                tableBodyHtml += "</tr>";
                            }
                        } 
                        //console.log(tableBodyHtml);
                        $("#tableBody").append(tableBodyHtml);
					}
                    
                    var productPicturesHtml = '';
                    if (data.pictures != null && data.pictures.length > 0) {
                        var imgs = data.pictures.split(",");
                        for (var i = 0; i < imgs.length; i++) {
                        	
                        	//'<a data-magnify="gallery" href="'+imgs[i]+'"><img width="200" src="'+imgs[i]+'" style="width: 100%;border: 2px solid #171c60;"></a>'
                            //productPicturesHtml += '<div class="spic"><img src="' + imgs[i] + '" /></div>';
                            
                            if (i < 5) {
                                productPicturesHtml += '<div class="spic"><a data-magnify="gallery" href="'+imgs[i]+'"><img src="' + imgs[i] + '" /></a></div>';
							} else {
	                            productPicturesHtml += '<div class="spic" style="display: none;"><a data-magnify="gallery" href="'+imgs[i]+'"><img src="' + imgs[i] + '" /></a></div>';
							}
                            
                        }
                        $("#slider").append(productPicturesHtml);
                        // $('#slider').slider({ speed: 500 })
                    } else {
                        productPicturesHtml += '<br/><h2>Currently no...</h2>';
                        $("#slider").append(productPicturesHtml);
                    }
                },
                'json'
            );
        } else {
            alert("请选择商品！");
            location.href="shop.jsp";
        }

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

         $('[data-magnify]').magnify({
             headToolbar: [
               'close'
             ],
             footToolbar: [
               'zoomIn',
               'zoomOut',
               'prev',
               'fullscreen',
               'next',
               'actualSize',
               'rotateRight'
             ],
             title: false
         });
    </script>
	<style type="text/css">
#maId-sticky-wrapper {
	width: 100%;
}

.node-treeview2 {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

body {
	font-size: 14px;
}
.bar6 input { border: 2px solid #171c60; border-radius: 5px; background: transparent; top: 0; right: 0; height: 45;width: 200px;}
</style>
</body>
</html>