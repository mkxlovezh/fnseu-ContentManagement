<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/10/30
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Title</title>
    <!-- Main CSS-->

    <link rel="stylesheet" type="text/css" href="/static/css/1.css">
    <link rel="stylesheet" type="text/css" href="/static/css/pagination.css">
    <link rel="stylesheet" type="text/css" href="/static/css/highlight.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/static/css/common.css">


    <link rel="shortcut icon" type="image/png" href="/static/template/assets/images/icon/favicon.ico">
    <link rel="stylesheet" href="/static/template/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/themify-icons.css">
    <link rel="stylesheet" href="/static/template/assets/css/metisMenu.css">
    <link rel="stylesheet" href="/static/template/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/static/template/assets/css/slicknav.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="/static/template/assets/css/typography.css">
    <link rel="stylesheet" href="/static/template/assets/css/default-css.css">
    <link rel="stylesheet" href="/static/template/assets/css/styles.css">
    <link rel="stylesheet" href="/static/template/assets/css/responsive.css">
    <script src="/static/template/assets/js/vendor/modernizr-2.8.3.min.js"></script>
    <script src="static/js/jquery-1.11.1.min.js"></script>
    <script src="/static/js/jquery.min.js"></script>
    <script>
        var $j=$.noConflict();
        $j('#msg').hide();//此处$j就代表JQuery
    </script>
    <script src="/static/js/highlight.min.js"></script>
    <script src="/static/js/jquery.pagination.js"></script>
</head>
<script>
    $j(function () {
        $j.ajax({
            type:"get",
            url:"/search.action?keywords="+"${param.keywords}"+"&page="+"${param.page}"+"&pageNum=8",
            data:{},
            success:function (da) {
                var re=da.result.news;

                var numfound=da.result.numfound;
                var ul=document.getElementById("u")
                function createElement() {
                    for(var t=0;t<re.length;t++){
                        var li=document.createElement("li")
                        li.setAttribute("class","ls")
                        var div=document.createElement("div")
                        div.setAttribute("class","change")
                        var h3=document.createElement("h3")
                        var a1=document.createElement("a")
                        a1.innerText=re[t].newsTitle
                        a1.setAttribute("href","#")
                        h3.appendChild(a1)
                        var divchild1=document.createElement("div")
                        var span=document.createElement("span")
                        span.innerText=re[t].newsTime;
                        divchild1.appendChild(span)
                        var divchild2=document.createElement("div")
                        var a2=document.createElement("a")
                        a2.setAttribute("href","#")
                        a2.innerText="www.baidu.com"
                        divchild2.appendChild(a2)
                        div.appendChild(h3)
                        div.appendChild(divchild1)
                        div.appendChild(divchild2)
                        var divdrop=document.createElement("div")
                        divdrop.setAttribute("class","dropdown")
                        var btn=document.createElement("button");
                        btn.setAttribute("class","btn btn-light dropdown-toggle")
                        btn.setAttribute("type","button")
                        btn.setAttribute("data-toggle","dropdown")
                        var event=re[t].newsEvent

                        btn.innerText=event[0].title
                        var btndiv=document.createElement("div")
                        btndiv.setAttribute("class","dropdown-menu")
                        for(var j=0;j<event.length;j++){
                            var aa=document.createElement("a")
                            aa.setAttribute("class","dropdown-item")
                            aa.setAttribute("href","/event.jsp?eventId="+event[j].eventId)
                            aa.innerText=event[j].title
                            btndiv.appendChild(aa)
                        }
                        divdrop.appendChild(btn)
                        divdrop.appendChild(btndiv)
                        li.appendChild(div)
                        li.appendChild(divdrop)
                        ul.appendChild(li)
                    }
                }
                createElement()
                var pageCount=Math.ceil(Number(numfound)/8)
                $j('.M-box3').pagination({
                    pageCount:pageCount, //总页数
                    jump:true,
                    coping:true,//是否开启首页和尾页
                    homePage:'首页',
                    endPage:'末页',
                    prevContent:'上页',
                    nextContent:'下页',
                    current:${param.page},  //当前第几页
                    callback:function(api){  //按钮、回调函数
                        window.location.href="/searchResult.jsp?keywords="+"${param.keywords}"+"&page="+api.getCurrent()+"&pageNum=8"
                        <%--$.ajax({--%>
                            <%--url:"/searchResult.jsp?keywords="+"${param.keywords}"+"&page="+api.getCurrent()+"&pageNum=9",--%>
                            <%--type:"get",--%>
                            <%--data:{--%>
                            <%--},--%>
                            <%--success:function(data){--%>
                                <%--$("#u").empty();--%>
                                <%--createElement()--%>
                            <%--}--%>
                        <%--})--%>
                    }

                })



            }
        })
        $('i').click(function () {
            window.location.href="/searchResult.jsp?keywords="+$("#keywords").val()+"&page=1"
        })


    })


</script>
<body>
<%@include file="WEB-INF/jsps/header.jsp"%>
<div class="whole_content">
    <div class="content1">
        <div class="content_plat">
            <div id="search2">

                <div class="content" style="width: 100%;height: 540px">
                    <div style="width: 100%;height: 10px"></div>
                    <ul id="u">

                    </ul>
                    <div class="m-style M-box3"></div>
                </div>





            </div>
        </div>

    </div>

</div>
<!-- jquery latest version -->
<script src="/static/template/assets/js/vendor/jquery-2.2.4.min.js"></script>





<!-- bootstrap 4 js -->
<script src="/static/template/assets/js/popper.min.js"></script>
<script src="/static/template/assets/js/bootstrap.min.js"></script>
<script src="/static/template/assets/js/owl.carousel.min.js"></script>
<script src="/static/template/assets/js/metisMenu.min.js"></script>
<script src="/static/template/assets/js/jquery.slimscroll.min.js"></script>
<script src="/static/template/assets/js/jquery.slicknav.min.js"></script>
<!-- others plugins -->
<script src="/static/template/assets/js/plugins.js"></script>
<script src="/static/template/assets/js/scripts.js"></script>

</body>
</html>
