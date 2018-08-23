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
            <img width="50%" style="height:60px; width:auto; float:left;padding-left: 15%;" src="http://47.254.38.242:8082/files/hardware/20180426/logo.png" >
            <ul class="nav navbar-nav" style="margin-left: 60px">
                <li id="indexLi"><a href="index.jsp">Home</a></li>
                <li id="profileLi"><a href="profile.jsp">Company Profile</a></li>
                <li id="shopLi"><a href="shop.jsp">Shop page</a></li>
                <li id="newProductLi"><a href="newProduct.jsp">new Product</a></li>
                <!-- <li id="singleProductLi"><a href="single-product.jsp" style="cursor: not-allowed;">Single product</a></li> -->
                <!-- <li id="contactLi"><a href="contact.jsp">Contact us</a></li> -->
                <!-- 
                <li><a href="checkout.html">Checkout</a></li>
                <li><a href="#">Category</a></li>
                <li><a href="#">Others</a></li>
                <li><a href="#">Contact</a></li>
                 -->
            </ul>
        </div>  
    </div>
</div> <!-- End mainmenu area -->