<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/10/18
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html style="height: 100%;width: 100%">
<head>
    <title>Title</title>
    <!-- Main CSS-->
    <link href="/static/template/css/theme.css" rel="stylesheet" media="all">
    <link rel="stylesheet" type="text/css" href="/static/css/1.css">
    <!-- Fontfaces CSS-->
    <link href="/static/template/css/font-face.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="/static/template/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="/static/template/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/static/template/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="/static/template/css/theme.css" rel="stylesheet" media="all">
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <script src="/static/js/jquery.canvasjs.min.js"></script>
    <script src="/static/js/cytoscape.min.js"></script>

    <script src="/static/js/wordcloud2.js"></script>
    <script src="/static/js/highcharts.js"></script>
    <script src="/static/js/exporting.js"></script>
    <script src="/static/js/oldie.js"></script>
    <script src="/static/js/wordcloud.js"></script>
    <!--<script type="text/javascript" src="/static/js/2.js"></script>-->
    <!--<script type="text/javascript" src="3.js"></script>-->
</head>
<script>
    Date.prototype.format = function(format)
    {
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(),    //day
            "h+" : this.getHours(),   //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
            "S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)if(new RegExp("("+ k +")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length==1 ? o[k] :
                    ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }



    function getDates(filters){
        var tab = filters.tab;
        var startTime = new Date(Date.parse(filters.startTime));
        var endTime = new Date(Date.parse(filters.endTime));
        var length = 0;   //日期跨度变量

        if( 0 == tab ) {
            length = (endTime.getTime() - startTime.getTime()) / (1000*60) + 1;
        }

        var xAxis = new Array(length);
        xAxis[0] = filters.startTime;
        for( var i = 1; i < length; i++ ) {
            if( 0 == tab ) {
                startTime.setDate( startTime.getDate() + 1 );
                xAxis[i] = startTime.format("yyyy-MM-dd hh:mm:ss");
            }
        }

        return xAxis;

    }



    $(function(){
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var strHour=date.getHours();
            var strMinutes=date.getMinutes();

            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate+" "+strHour+":"+strMinutes+":00";
            return currentdate;
        }
        function getuuid() {
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 36; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
            s[8] = s[13] = s[18] = s[23] = "-";

            var uuid = s.join("");
            return uuid;
        }
        $.ajax(
            {
                type:"get",
                url:"/news_eventline.action?eventlineId="+"${param.eventlineId}",
                data:{
                },
                success:function (da) {
                    //趋势图
                    var needDate={
                        "startTime":"${param.startTime}",
                        "endTime":"2018-01-30 00:00:00",
                        "tab":0
                    }
                    var date=getDates(needDate);
                    var data = [];
                    var dataSeries = { type: "line" ,toolTipContent:"{customPropperty}:{y}"};
                    var dataPoints = [];

                    var events=da.result.events
                    for(var i=0;i<events.length;i++ ) {
                        var tbody = document.getElementsByTagName("tbody")[0];
                        var tr = document.createElement("tr")
                        tbody.appendChild(tr)
                        var td1 = document.createElement("td")
                        td1.innerText = events[i].eventTitle
                        var td2 = document.createElement("td")
                        td2.innerText = events[i].eventKeywords
                        var td3 = document.createElement("td")
                        td3.innerText = events[i].eventStartTime
                        var td4 = document.createElement("td")
                        td4.innerText = events[i].eventEndTime
                        var td5=document.createElement("td")
                        td5.innerText=events[i].eventPassion
                        var td6 = document.createElement("td")
                        td6.setAttribute("class", "text-right")
                        var a = document.createElement("a")
                        a.setAttribute("href", "/event.jsp?eventId=" + events[i].eventId)
                        a.innerText = "查看"
                        dataPoints.push({
                            name:events[i].eventId,
                            x:new Date(events[i].eventStartTime),
                            y:events[i].eventPassion,
                            customPropperty:events[i].eventTitle,
                        })
                        td6.appendChild(a)
                        tr.appendChild(td1)
                        tr.appendChild(td2)
                        tr.appendChild(td3)
                        tr.appendChild(td4)
                        tr.appendChild(td5)
                        tr.appendChild(td6)
                    }
                    dataSeries.dataPoints = dataPoints;
                    dataSeries.click=onClick,
                    data.push(dataSeries);
                    var options = {
                        zoomEnabled: true,
                        animationEnabled: true,
                        title: {
                            text: "事件线发展趋势图"
                        },
                        axisX:{
                            valueFormatString:"YY-MM-DD HH:mm"
                        },
                        axisY: {
                            includeZero: false,
                            lineThickness: 1,
                            ValueFormatString:"# pages",
                        },

                        data: data  // random data
                    };
                    var chart = new CanvasJS.Chart("chartContainer", options);
                    chart.render();
                    function onClick(e) {
                        window.location.href = "/event.jsp?eventId="+e.dataPoint.name;
                    }
                    //实体图
                    var scape={};
                    scape.container=document.getElementById('cy');
                    scape.style=[
                        { selector: 'node[label = "EventlineNode"]',
                            css: {'background-color': '#6FB1FC', 'content': 'data(name)'}
                        },
                        { selector: 'node[label = "EntityNode"]',
                            css: {'background-color': '#F5A45D', 'content': 'data(title)'}
                        },
                        { selector: 'edge',
                            css: {'content': 'data(relationship)', 'target-arrow-shape': 'triangle'}
                        }
                    ];
                    scape.layout={ name: 'cose'}
                    scape.elements={};
                    scape.elements.nodes=[];
                    scape.elements.edges=[];
                    var eventlinedata={};
                    eventlinedata.data={};
                    eventlinedata.data.id="00000000";
                    eventlinedata.data.name=da.eventlineId;
                    eventlinedata.data.label="EventlineNode"
                    scape.elements.nodes.push(eventlinedata)
                    var eventLineEntities=da.result.entities
                    for(var i=0;i<eventLineEntities.length;i++)
                    {
                        var entitydata={};
                        var edge={};
                        entitydata.data={};
                        edge.data={}
                        var uuid=getuuid();
                        entitydata.data.id=uuid;
                        entitydata.data.title=eventLineEntities[i][0];
                        entitydata.data.label="EntityNode";
                        scape.elements.nodes.push(entitydata)
                        edge.data.source="00000000";
                        edge.data.target=uuid;
                        edge.data.relationship='related';
                        scape.elements.edges.push(edge)
                    }
                    cytoscape(scape);

                    //词云
                    var keyword=da.result.keywords;
                    var t=[];
                    for(var i=0;i<keyword.length;i++){
                        var obj={};
                        obj.name=keyword[i][0]

                        obj.weight=keyword[i][1]
                        t.push(obj)
                    }
                    Highcharts.chart('container1', {
                        series: [{
                            type: 'wordcloud',
                            data: t
                        }],
                        title: {
                            text: '关键词云'
                        }
                    });
                    var entities=da.result.entities;
                    var tt=[];
                    for(var i=0;i<entities.length;i++){
                        var obj={};
                        obj.name=entities[i][0]

                        obj.weight=entities[i][1]
                        tt.push(obj)
                    }
                    Highcharts.chart('container2', {
                        series: [{
                            type: 'wordcloud',
                            data: tt
                        }],
                        title: {
                            text: '实体词云'
                        }
                    });



                },
                error:function () {
                    alert("系统异常，请稍后重试！")
                }
            }
        )
            
        

    });
</script>
<body style="height: 100%;width: 100%">
<%@include file="WEB-INF/jsps/header.jsp"%>
<div class="whole_content">
    <div class="content1">
        <div class="content_plat" style="height: 1200px">
            <div id="search2">
                <div class="mutl_graph">
                    <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                    <span id="timeToRender" style="display: block;"></span>
                </div>
                <div style="width: 1080px ">
                    <div class="row" style="margin-top: 40px">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4>事件线信息</h4>
                                </div>
                                <div class="card-body">
                                    <div class="default-tab">
                                        <nav>
                                            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home"
                                                   aria-selected="true">事件线实体</a>
                                                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile"
                                                   aria-selected="false">关联事件</a>
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
                                            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="table-responsive table--no-card m-b-30" style="height: 500px">
                                                            <table class="table table-borderless table-striped table-earning">
                                                                <thead>
                                                                <tr>
                                                                    <th>事件标题</th>
                                                                    <th>事件关键词</th>
                                                                    <th>开始时间</th>
                                                                    <th>结束时间</th>
                                                                    <th>热度</th>
                                                                    <th class="text-right">操作</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                            </table>
                                                        </div>
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

<!-- Bootstrap JS-->
<script src="/static/template/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="/static/template/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="/static/template/vendor/slick/slick.min.js">
</script>
<script src="/static/template/vendor/wow/wow.min.js"></script>
<script src="/static/template/vendor/animsition/animsition.min.js"></script>
<script src="/static/template/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
</script>
<script src="/static/template/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="/static/template/vendor/counter-up/jquery.counterup.min.js">
</script>
<script src="/static/template/vendor/circle-progress/circle-progress.min.js"></script>
<script src="/static/template/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="/static/template/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="/static/template/vendor/select2/select2.min.js">
</script>

<!-- Main JS-->
<script src="/static/template/js/main.js"></script>

</body>
</html>
