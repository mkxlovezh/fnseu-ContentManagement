<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/10/18
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="height: 100%;width: 100%"><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="/static/css/1.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <script src="/static/js/jquery.canvasjs.min.js"></script>
    <script src="/static/js/cytoscape.min.js"></script>



    <script src="/static/js/wordcloud2.js"></script>
    <script src="/static/js/highcharts.js"></script>
    <script src="/static/js/exporting.js"></script>
    <script src="/static/js/oldie.js"></script>
    <script src="/static/js/wordcloud.js"></script>
    <script src="/static/js/event.js"></script>
    <script src="/static/template/assets/js/vendor/modernizr-2.8.3.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="/static/template/assets/images/icon/favicon.ico">
    <link rel="stylesheet" href="/static/template/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/themify-icons.css">
    <link rel="stylesheet" href="/static/template/assets/css/metisMenu.css">
    <link rel="stylesheet" href="/static/template/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/slicknav.min.css">
    <!-- amcharts css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- Start datatable css -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.jqueryui.min.css">
    <!-- style css -->
    <link rel="stylesheet" href="/static/template/assets/css/typography.css">
    <link rel="stylesheet" href="/static/template/assets/css/default-css.css">
    <link rel="stylesheet" href="/static/template/assets/css/styles.css">
    <link rel="stylesheet" href="/static/template/assets/css/responsive.css">
    <!-- modernizr css -->

</head>
<script>


</script>
<body style="height: 100%;width: 100%">
<%@include file="WEB-INF/jsps/header.jsp"%>
<div class="whole_content">
    <div class="content1">
        <div class="content_plat" style="height: 1200px">
            <div id="search2">
                <div class="card">
                    <div class="card-body">

                        <h4 class="header-title">事件包含新闻：</h4>

                        <table id="example" class="table table-striped table-bordered" width="100%"></table>
                    </div>
                </div>
                <div style="width: 1080px; ">
                    <div class="row" style="margin-top: 40px">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>事件信息</h4>
                                </div>
                                <div class="card-body">
                                    <div class="default-tab">
                                        <nav>
                                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home"
                                                   aria-selected="true">事件实体</a>
                                                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact"
                                                   aria-selected="false">舆论聚合</a>
                                            </div>

                                        </nav>
                                        <div class="tab-content pl-3 pt-2" id="nav-tabContent">
                                            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div id="cy"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledbyaria-labelledby="nav-contact-tab">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div  style="width: 1020px;background-color: #f9f9f9;">
                                                            <div id="container1" style="height: 270px;"></div>
                                                        </div>
                                                        <div  style="width: 1020px;background-color: #f9f9f9;">
                                                            <div id="container2" style="height:270px;"></div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>

                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </div>

</div>
<script src="/static/template/assets/js/vendor/jquery-2.2.4.min.js"></script>
<!-- bootstrap 4 js -->
<script src="/static/template/assets/js/popper.min.js"></script>
<script src="/static/template/assets/js/bootstrap.min.js"></script>
<script src="/static/template/assets/js/owl.carousel.min.js"></script>
<script src="/static/template/assets/js/metisMenu.min.js"></script>
<script src="/static/template/assets/js/jquery.slimscroll.min.js"></script>
<script src="/static/template/assets/js/jquery.slicknav.min.js"></script>

<!-- Start datatable js -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap.min.js"></script>
<!-- others plugins -->
<script src="/static/template/assets/js/plugins.js"></script>
<script src="/static/template/assets/js/scripts.js"></script>

</body>
</html>
