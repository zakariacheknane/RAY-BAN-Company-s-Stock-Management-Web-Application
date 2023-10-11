<!DOCTYPE html>
<html
 xmlns="http://www.w3.org/1999/xhtml" xmlns:ui="http://java.sun.com/jsf/facelets" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta charset="UTF-8" />
<title></title>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
	
	  <!-- Favicons -->
<link href="resources/assets/img/logo.png" rel="icon"/>
  <link href="https://fonts.gstatic.com" rel="preconnect"/>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet"/>

 <link href="resources/assets/vendor/simple-datatables/style.css" rel="stylesheet"/>
  <link href="resources/assets/css/style.css" rel="stylesheet"/>

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect"/>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet"/>

  <!-- Template Main CSS File -->
</head>
<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index.xhtml" class="logo d-flex align-items-center">
        <img src="resources/assets/img/logo.png" alt=""/>
        <span class="d-none d-lg-block"></span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

  </header><!-- End Header -->
 
  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="index.xhtml">
          <i class="bi bi-menu-button-wide"></i>
          <span>Products</span>
        </a>
      </li><!-- End Dashboard Nav -->



      <li class="nav-item">
        <a class="nav-link collapsed"  href="add.xhtml">
         <i class="bi bi-bag-plus"></i><span>Add Product</span>
        </a>
       
      </li><!-- End Add Product Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="commande.xhtml">
          <i class="bi bi-cart3"></i><span>Orders</span>
        </a>
       
      </li><!-- End Orders Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed " href="addcommande.xhtml">
         <i class="bi bi-cart-plus"></i><span>Add Order</span>
        </a>
       
      </li><!-- End Add order Nav -->

      <li class="nav-item">
       <h:form>
     <i class="bi bi-box-arrow-in-right nav-link collapsed" >
     <h:commandButton class="nav-link collapsed" value="Logout" action="#{loginManagedBean.logout}" rendered="#{loginManagedBean.loggedIn}"/>
     </i>
       </h:form>
      </li><!-- End Logout Page Nav -->
    </ul>
  </aside><!-- End Sidebar-->
    
    <!-- Vendor JS Files -->
  <script src="resources/assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="resources/assets/vendor/chart.js/chart.umd.js"></script>
  <script src="resources/assets/vendor/echarts/echarts.min.js"></script>
  <script src="resources/assets/vendor/quill/quill.min.js"></script>
  <script src="resources/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="resources/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="resources/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
<script src="resources/style.js"></script>
  <script src="resources/assets/js/main.js"></script>


</body>
</html>