<!DOCTYPE html>
<html lang="en">
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>订单Id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                <td>
                                <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                </#if>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                <#else >
                    <li><a href="/sell/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a></li>
                </#if>

                <#list 1..orderDTOPage.getTotalPages() as index>
                    <#if currentPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else >
                    <#--<#if index gte 3 || orderDTOPage.getTotalPages() - index gte 5>-->
                    <#--<li class="disabled"><a href="#">..</a></li>-->
                    <#--<#else >-->
                            <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    <#--</#if>-->
                    </#if>
                </#list>

                <#if currentPage gte orderDTOPage.getTotalPages()>
                    <li class="disabled"><a href="#">下一页</a></li>
                <#else >
                    <li><a href="/sell/seller/order/list?page=${currentPage +1}&size=${size}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#--弹窗-->

<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                您有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="document.getElementById('notice').pause();" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>

    </div>

</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://emart.s1.natapp.cc/sell/webSocket');
    } else {
        alert('该浏览器不支持webSocket!');
    }

    webSocket.onopen = function (ev) {
        console.log('建立连接');
    }
    webSocket.onclose = function (ev) {
        console.log('关闭连接');
    }
    webSocket.onmessage = function (ev) {
        console.log('收到消息：' + ev.data);
        //弹窗提醒，播放音乐
        $('#myModal').modal('show');
        document.getElementById('notice').play();
    }
    webSocket.onerror = function (ev) {
        console.log('webSocket 通信发生错误');
    }
    webSocket.onbeforeunload = function () {
        webSocket.close();
    }
</script>
</body>
</html>