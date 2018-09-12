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
</style>
</head>
<body style="margin: 0 auto;">
	<%@include file="header.jsp"%>
	<div class="product-big-title-area"
		style="height: 300; background-position: center;"></div>
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
                        html += '<h2><a href="javascript:;" onclick="showSeries('+result[i].id+')">'+result[i].nameEn+'</a></h2>';
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
    </script>
</body>
</html>