<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="site-branding-area" style="margin-top: 10px">
    <div class="container">
        <div class="row">
            <!-- 
            <div class="col-sm-6" style="margin: 10">
                <div class="logo">
                    <h1><a href="index.jsp">e<span>Electronics</span></a></h1>
                    <img width="50%" src="http://47.254.38.242:8082/files/hardware/20180426/logo.png">
                </div>
            </div>
             -->
            <!-- 
            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart.html">Cart - <span class="cart-amunt">$800</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                </div>
            </div>
             -->
        </div>
    </div>
</div> <!-- End site branding area -->

<div id="maId" class="container mainmenu-area">
    <div class="row">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" style="border-bottom: solid 10px #171c60;">
            <ul class="nav navbar-nav" style="margin-left: 60px">
                <li>　　　　　　　　　　　　</li>
                <li><img style="height:60px; width:auto; float:left;inline-size: auto;" src="http://47.254.38.242:8082/files/hardware/20180426/logo.png"></li>
                <li>　　</li>
                <li id="indexLi"></li>
                <li id="profileLi"></li>
                <li id="shopLi"></li>
                <li id="newProductLi"></li>
                <!-- <li id="singleProductLi"><a href="single-product.jsp" style="cursor: not-allowed;">Single product</a></li> -->
                <li id="contactLi"></li>
                <!-- 
                <li><a href="checkout.html">Checkout</a></li>
                <li><a href="#">Category</a></li>
                <li><a href="#">Others</a></li>
                <li><a href="#">Contact</a></li>
                 -->
            </ul>
        </div>  
        <div class="form-group form-inline" style="float: right;height: 45;position: absolute;top: 20;right: 70;"> 
            <label class="form-label">Language：</label>
            <select id="languageSelect" title="language" name="selectLanguage" class="form-control" style="width: 100px">
                <option value="1" selected="selected">English</option>
                <option value="2">Español</option>
            </select>
       </div> 
    </div>
</div> <!-- End mainmenu area -->
<script>
// 页面语言 1.英语 2.西语
var pageLanguage = window.localStorage.getItem("pageLanguage");
if (pageLanguage == undefined) {
    pageLanguage = 1;
}
$("#languageSelect").val(pageLanguage);

if (pageLanguage == 1) {
    $("#indexLi").append('<a href="index.jsp">Home</a>');
    $("#profileLi").append('<a href="profile.jsp">ABOUT ARTESANO</a>');
    $("#shopLi").append('<a href="shop.jsp">Main Product</a>');
    $("#newProductLi").append('<a href="newProduct.jsp">new Product</a>');
    $("#contactLi").append('<a href="contact.jsp">Contact us</a>');
} else {
    $("#indexLi").append('<a href="index.jsp">CASA</a>');
    $("#profileLi").append('<a href="profile.jsp">SOBRE ARTESANO</a>');
    $("#shopLi").append('<a href="shop.jsp">PRODUCTO PRINCIPAL</a>');
    $("#newProductLi").append('<a href="newProduct.jsp">NUEVO PRODUCTO</a>');
    $("#contactLi").append('<a href="contact.jsp">CONTÁCTENOS</a>');
}

$("#languageSelect").change(function(){
    var val = $(this).children('option:selected').val();
    window.localStorage.setItem("pageLanguage", val);
    window.location.reload();
})
</script>
