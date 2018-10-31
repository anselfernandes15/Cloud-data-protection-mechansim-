<%@include file="header.jsp" %>
<%@include file="navigation1.jsp" %>
<%@include file="flash.jsp" %>
<% session.setAttribute("user","admin");%>
                                   
<div id="myCarousel" class="carousel slide" data-interval="3000">
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
  
   
</div>
<!-- /.carousel -->



<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container">

    <h3 class="form-signin-heading">Welcome ADMIN !!</h3>
  
    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="featurette">
        <h2 class="featurette-heading">This is your<span class="muted"> own space.</span></h2>
        <p class="lead">You can upload the decoy files via the Upload Decoy tab.</p>
    </div>

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-left" width="322px" height="322px" src="img/pic1.jpg">
        <h2 class="featurette-heading"> View the Valid and in-valid users. <span class="muted">Keep a track of their uploads.</span></h2>
        <p class="lead">Work towards keeping this the organisation information safe.</p>
    </div>

    <hr class="featurette-divider">

    <div class="featurette">
        <img class="featurette-image pull-right" width="150px" height="150px" src="img/green-office.jpg">
        <h2 class="featurette-heading">And lastly, this one. <span class="muted">Go green!!.</span></h2>
        <p class="lead">We belive in a greener earth, so switch off your systems after using it.</p>
    </div>
</div>
<!-- /END THE FEATURETTES -->
<%@include file="footer.jsp" %>