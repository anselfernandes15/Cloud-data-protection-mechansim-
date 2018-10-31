<%@include file="header.jsp" %>
<%@include file="navigation.jsp" %>
<%@include file="flash.jsp" %>
<!-- Carousel
================================================== -->



<div id="myCarousel" class="carousel slide" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="item active">
            <img src="img/slide1.jpg" alt="">
            <div class="carousel-caption">
                <h4>Online Support</h4>
                <p>Enjoy 24*7 online support for any of your queries.</p>
            </div>
        </div>
        <div class="item">
            <img src="img/slide2.jpg" alt="">
            <div class="carousel-caption">
                <h4>Micro-Innovation</h4>
                <p>Innovate ideas and implement them with our super fast fiber optic connectivity</p>
            </div>
        </div>
        <div class="item">
            <img src="img/slide3.jpg" alt="">
            <div class="carousel-caption">
                <h4>The future is here</h4>
                <p>Future technology and much more with our R&D department </p>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
<!-- /.carousel -->



<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<br>
<br>
<br>
<div class="container">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="span4">
            <img class="img-circle" src="img/vision.jpg" />
            <h2>Vision</h2>
            <p>An organisation's vision is the long-term change it ideally would like to see if its work is successful. A vision should motivate and enable individuals to see how their effort contributes to an overall inspirational purpose.</p>
            <!--<p><a class="btn" href="#">View details &raquo;</a></p>-->
        </div><!-- /.span4 -->
        <div class="span4">
            <img class="img-circle" src="img/mission.jpg" />
            <h2>Mission</h2>
            <p>Combining leading-edge business strategy and IT knowledge, to develop efficient and effective solutions that keep businesses one-step ahead.</p>
            <!--<p><a class="btn" href="#">View details &raquo;</a></p>-->
        </div><!-- /.span4 -->
        <div class="span4">
            <img class="img-circle" src="img/values.jpg" />
            <h2>Values</h2>
            
           <p>Companies can have core values as well. These are the guiding principles that help to define how the corporation would behave. They are usually expressed in the corporation's mission statement.</p>
           <p>Companies may also have negative core values as well. Companies that are solely motivated by profit, such as tobacco companies who lied to their customers about the dangers of smoking, may have been driven by core values of self-interest and an overly strong profit motive.</p> 
           <p>There are countless types of core values,</p>
           <p> 
           <li>Dependable </li>
           <li>Committed</li>
           <li>Consistent</li>
           <li>Efficient</li>
           <li>Motivated</li>
        </p>
          <!--<p><a class="btn" href="#">View details &raquo;</a></p>-->
        </div><!-- /.span4 -->
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">    
    
    <div class="featurette">
        <img class="featurette-image pull-right" src="img/pic3.jpg">
        <h2 class="featurette-heading">And lastly, this one. <span class="muted">SECURITY...</span></h2>
        <p class="lead">Your files remain secured even when shared in our server</p>
    </div>
    <br>
    <br>
    <br>
    
</div>
<!-- /END THE FEATURETTES -->
<%@include file="footer.jsp" %>