var stompClient = null;

//加载完浏览器后调用connect（），打开通道
$(function () {
    //打开双通道
    connect()
})

//强制关闭浏览器时调用websocket.close（）,进行正常关闭
window.onunload = function () {
    disconnect()
}

//打开通道
function connect() {
    //连接SockJS的endpoint名称为"endpoint"
    var socket = new SockJS('/endpoint');
    stompClient = Stomp.over(socket);//使用STMOP子协议的WebSocket客户端
    stompClient.connect({}, function (frame) {//连接WebSocket服务端

        console.log('Connected:' + frame);
        //广播接收信息
        stompTopic();

    });
}

//关闭通道
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

//一对多，发起订阅
function stompTopic() {
    //通过stompClient.subscribe订阅目标(destination)发送的消息（广播接收信息）
    stompClient.subscribe('/mass/getResponse', function (response) {
        var data = JSON.parse(response.body);
        $('#showContent').append("[" + data.sendFrom + "]发来群聊:\n" + data.message + "\n");
    });
}

//一对一，发起订阅
function stompQueue() {
    var userId = $("#sendFrom").val();
    alert("监听:" + userId)
    //通过stompClient.subscribe订阅目标(destination)发送的消息（队列接收信息）
    stompClient.subscribe('/queue/' + userId+'/alone',
        function (response) {
            var data = JSON.parse(response.body);
            $('#showContent').append("[" + data.sendFrom + "]发来私聊:\n" + data.message + "\n");
        });
}

function sendMessage() {
    var message = $('#writeContext').val().trim();
    var sendTo = $('#sendTo').val();
    var sendFrom = $('#sendFrom').val();
    if (message == '' || sendFrom == '') {
        alert("发送失败，未选择发送人，或填写发送内容");
        return;
    }
    var data = {};
    data.message = message;
    data.sendFrom = sendFrom;
    data.sendTo = sendTo;

    if (sendTo == '1') {
        stompClient.send("/massRequest", {}, JSON.stringify(data));
        $('#showContent').append("[我]发送群聊:\n" + data.message + "\n");
    } else {
        stompClient.send("/queueRequest", {}, JSON.stringify(data));
        $('#showContent').append("[我]发送私聊给["+data.sendTo+"]:\n" + data.message + "\n");
    }
}




