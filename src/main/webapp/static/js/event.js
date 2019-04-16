$(function(){
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
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
        if(r!=null)return  unescape(r[2]); return null;
    }
    $.ajax(
        {
            type:"get",
            url:"/news_event.action?eventId="+GetQueryString("eventId"),
            data:{

            },
            success:function (da) {
                var news=da.result.news;
                var d=new Array(news.length)
                for(var i=0;i<news.length;i++){
                    var row=new Array(2)
                    row[0]=news[i].newsTitle
                    row[1]=news[i].newsTime
                    d[i]=row
                }

                $('#example').DataTable( {
                    data: d,
                    columns: [
                        { title: "标题" },
                        { title: "时间" },

                    ],
                    "lengthMenu": [[10, -1], [10, "All"]]
                } );
                var scape={};
                scape.container=document.getElementById('cy');
                scape.style=[
                    { selector: 'node[label = "EventNode"]',
                        css: {'background-color': '#6FB1FC', 'content': 'data(name)'}
                    },
                    { selector: 'node[label = "EntityNode"]',
                        css: {'background-color': '#F5A45D', 'content': 'data(title)'}
                    },
                    { selector: 'edge',
                        css: {'content': 'data(relationship)', 'target-arrow-shape': 'triangle'}
                    }
                ];
                scape.layout={ name: 'breadthfirst'}
                scape.elements={};
                scape.elements.nodes=[];
                scape.elements.edges=[];
                var eventdata={};
                eventdata.data={};
                eventdata.data.id="00000000";
                eventdata.data.name=da.eventId;
                eventdata.data.label="EventNode"
                scape.elements.nodes.push(eventdata)
                var entities=da.result.entities
                for(var i=0;i<entities.length;i++){
                    var entitydata={};
                    var edge={};
                    entitydata.data={};
                    edge.data={}
                    var uuid=getuuid();
                    entitydata.data.id=uuid;
                    entitydata.data.title=entities[i];
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
    $('i').click(function () {
        window.location.href="/searchResult.jsp?keywords="+$("#keywords").val()+"&page=1"
    })

});