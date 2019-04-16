$(document).ready(function () {
    $.ajax(
        {
            type:"get",
            url:"/news.action?startTime=2018-01-01%2000:00:00%20&endTime=%202018-01-30%2023:59:59%20&limit=5",
            data:{

            },
            success:function (da) {
                var data=[];
                var eventlines=da.result.eventLines
                for(var i=0;i<eventlines.length;i++ ){
                    var tbody=document.getElementsByTagName("tbody")[0];
                    var tr=document.createElement("tr")
                    tbody.appendChild(tr)
                    var td1=document.createElement("td")
                    td1.innerText=eventlines[i].eventlineTitle
                    var td2=document.createElement("td")
                    td2.innerText=eventlines[i].eventLineStartTime
                    var td3=document.createElement("td")
                    td3.innerText=eventlines[i].passion
                    var td4=document.createElement("td")
                    td4.innerText=eventlines[i].passionNow
                    var td5=document.createElement("td")
                    td5.setAttribute("class","text-right")
                    var a=document.createElement("a")
                    a.setAttribute("href","/eventline.jsp?eventlineId="+eventlines[i].eventLineId+"&startTime="+eventlines[i].eventLineStartTime)
                    a.setAttribute("class","a_post")
                    a.innerText="查看"
                    td5.appendChild(a)
                    tr.appendChild(td1)
                    tr.appendChild(td2)
                    tr.appendChild(td3)
                    tr.appendChild(td4)
                    tr.appendChild(td5)

                    var a={};
                    a.name=eventlines[i].eventlineTitle;
                    a.type="spline";
                    a.toolTipContent="{customPropperty}:{y}";
                    a.ValueFormatString="#0.## P";
                    a.showInLegend=true;
                    a.dataPoints=[];
                    var eventLineEvents=eventlines[i].eventLineEvents
                    for(var j=0;j<eventLineEvents.length;j++)
                    {
                        var datapoint={};
                        var startTime = new Date(eventLineEvents[j].eventStartTime);
                        var Startyear=startTime.getFullYear();
                        var Startmonth=startTime.getMonth();
                        var Startday=startTime.getDate();
                        var Starthour=startTime.getHours();
                        var Startminute=startTime.getMinutes();
                        datapoint.x=new Date(Startyear,Startmonth,Startday,Starthour,Startminute,0);
                        datapoint.y=parseInt(eventLineEvents[j].passion);
                        datapoint.customPropperty=eventLineEvents[j].eventTitle;
                        a.dataPoints.push(datapoint);
                    }
                    data.push(a)
                }
                var chart = new CanvasJS.Chart("chartContainer", {

                    animationEnabled: true,
                    title:{
                        text: "当前最热事件线趋势图"
                    },
                    axisX: {
                        valueFormatString: "YY-MM-DD HH:MM"
                    },
                    axisY: {
                        title: "文章数 (in P)",
                        includeZero: false,
                        suffix: " P"
                    },
                    legend:{
                        cursor: "pointer",
                        fontSize: 16,
                        itemclick: toggleDataSeries
                    },
                    toolTip:{
                        shared: true
                    },
                    data: data
                });
                chart.render();

                function toggleDataSeries(e){
                    if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                        e.dataSeries.visible = false;

                    }
                    else{
                        e.dataSeries.visible = true;
                    }
                    chart.render();
                }

                var socket=new WebSocket('ws://localhost:8080/ws')
                socket.onopen=function (event) {

                }
                socket.onmessage=function (event) {
                    alert(event.data)
                }
            },
            error:function () {
                alert("系统异常，请稍后重试！")
            }

        }
    )
    $('i').click(function () {
        window.location.href="/searchResult.jsp?keywords="+$("#keywords").val()+"&page=1"
    })
})
