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
				<div id="productSeriesDiv" class="col-md-12">
					<div id="productSeriesList"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
        $("#shopLi").addClass("active");
        // 清空系列产品页码
        window.localStorage.setItem("pageNumber",0);
        /* 
        $.ajax({
            type: "Post",
            url: contextPath+'/product/productSeriesTree.json',
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
                        getList(data.id, 0, 8);
                    }
                });
            },
            error: function () {
                alert("树形结构加载失败！");
            }
        });
         */
        
        // 获取系列列表
        function getProductSeriesList(){
            $.post(contextPath+'/product/getProductSeriesList.json',
                function(result){
            	   console.log(result);
                    var html = '';
                    for (var i = 0; i < result.length; i++) {
                        html += '<div class="col-md-3" style="padding-right: 0;">';
                        html += '<div class="single-shop-product">';
                        html += '<div class="product-upper">';
                        html += '<img src="'+result[i].photo+'" alt="'+result[i].photo+'" width="150" height="150">';
                        html += '</div>';
                        html += '<h2><a href="javascript:;" style="overflow: hidden;text-overflow: ellipsis;display: -webkit-box;line-height: 30px;max-height: 25px;-webkit-line-clamp: 1;-webkit-box-orient: vertical;" onclick="showSeries('+result[i].id+')">';
                        if (1==pageLanguage) {
                            html += result[i].nameEn;
						} else {
                            html += result[i].nameEs;
						}
                        html += '</a></h2>';
                        html += '</div>';
                        html += '</div>';
                    }
                    $("#productSeriesList").empty();
                    $("#productSeriesList").append(html);
                },
                'json'
            );
        }
        getProductSeriesList();

        function showSeries(id){
            location.href="/hardware/seriesProduct.jsp?id="+id;
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
    </script>
</body>
</html>