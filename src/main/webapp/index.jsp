<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html lang="en">
  <head>
    <%@include file="common/commonFront.jsp"%>
  </head>
  <body style="margin:0 auto;width: 80%">
    <%@include file="header.jsp"%>
    <div class="slider-area">
        <!-- <div class="zigzag-bottom"></div> -->
        <div id="slide-list" class="carousel carousel-fade slide" data-ride="carousel">
            <div class="slide-bulletz">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ol class="carousel-indicators slide-indicators">
                                <li data-target="#slide-list" data-slide-to="0" class="active"></li>
                                <li data-target="#slide-list" data-slide-to="1"></li>
                                <li data-target="#slide-list" data-slide-to="2"></li>
                            </ol>                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <div class="single-slide">
                        <div class="slide-bg" style="background-image: url(${staticData["indexInfoImg1"].dataValueEn })"></div>
                        <div class="slide-text-wrapper">
                            <div class="slide-text">
                                <%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle1"].dataValueEn }</h2>
                                                <p>
                                                    ${staticData["indexInfoText1"].dataValueEn }
                                                </p>
                                                <a href="${staticData["indexInfoHref1"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="single-slide">
                        <div class="slide-bg" style="background-image: url(${staticData["indexInfoImg2"].dataValueEn })"></div>
                        <div class="slide-text-wrapper">
                            <div class="slide-text">
                                <%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle2"].dataValueEn }</h2>
                                                <p>${staticData["indexInfoText2"].dataValueEn }</p>
                                                <a href="${staticData["indexInfoHref2"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="single-slide">
                        <div class="slide-bg" style="background-image: url(${staticData["indexInfoImg3"].dataValueEn })"></div>
                        <div class="slide-text-wrapper">
                            <div class="slide-text">
                                <%-- <div class="container">
                                    <div class="row">
                                        <div class="col-md-6 col-md-offset-6">
                                            <div class="slide-content">
                                                <h2>${staticData["indexInfoTitle3"].dataValueEn }</h2>
                                                <p>${staticData["indexInfoText3"].dataValueEn }</p>
                                                <a href="${staticData["indexInfoHref3"].dataValueEn }" class="readmore">Learn more</a>
                                            </div>
                                        </div>
                                    </div>
                                </div> --%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>        
    </div> <!-- End slider area -->
    <div class="row"style="margin-top: 1%;margin-bottom: 1%;">
        <div class="col-sm-6">
            <div class="product-images">
                <div id="picUrl" class="product-main-img" style="margin-right: 10%">
                    <img src="http://47.254.38.242:8082/files/hardware/20180426/company.png" alt="" style="float: right;">
                </div>
            </div>
        </div>
        
        <div class="col-sm-5"style="margin-top: 1%;>
            <div class="product-inner">
                <h2 class="product-name" style="background-color: #fc894e">Company Name XXX</h2>
                <div class="product-inner-category">
                    <p id="productType">Company Tags: <a href="">awesome</a>, <a href="">best</a>, <a href="">sale</a>, <a href="">shoes</a>. </p>
                </div> 
                
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                            <h2>Company Description</h2>
                            <p id="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tristique, diam in consequat iaculis, est purus iaculis mauris, imperdiet facilisis ante ligula at nulla. Quisque volutpat nulla risus, id maximus ex aliquet ut. Suspendisse potenti. Nulla varius lectus id turpis dignissim porta. Quisque magna arcu, blandit quis felis vehicula, feugiat gravida diam. Nullam nec turpis ligula. Aliquam quis blandit elit, ac sodales nisl. Aliquam eget dolor eget elit malesuada aliquet. In varius lorem lorem, semper bibendum lectus lobortis ac.Mauris placerat vitae lorem gravida viverra. Mauris in fringilla ex. Nulla facilisi. Etiam scelerisque tincidunt quam facilisis lobortis. In malesuada pulvinar neque a consectetur. Nunc aliquam gravida purus, non malesuada sem accumsan in. Morbi vel sodales libero.</p>
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
    </div>
    <div class="promo-area">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="single-promo" style="border: 1px solid #17a78b;">
                        <i class="fa fa-refresh"></i>
                        <p>30 Days return</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="single-promo" style="border: 1px solid #17a78b;">
                        <i class="fa fa-truck"></i>
                        <p>Free shipping</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="single-promo" style="border: 1px solid #17a78b;">
                        <i class="fa fa-lock"></i>
                        <p>Secure payments</p>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="single-promo" style="border: 1px solid #17a78b;">
                        <i class="fa fa-gift"></i>
                        <p>New products</p>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End promo area -->
    <%@include file="footer.jsp"%>
    <script>
	    $("#indexLi").addClass("active");
    </script>
  </body>
</html>