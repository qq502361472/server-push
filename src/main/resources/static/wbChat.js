var socket;

function startConnect() {
    if (typeof (WebSocket) == "undefined") {
        alert("浏览器不支持WebSocket")
        return;
    }
    if(socket!=null){
        socket.close();
    }
    var userId = $('#sendFrom').val();
    socket = new WebSocket("ws://localhost:8080/wsServer/" + userId);
    alert("新用户："+userId+"已连接");

    socket.onopen = function () {
        console.log("Socket 已打开");
    };
    socket.onmessage = function (msg) {
        console.log(msg);
        var data = msg.data.split(",")
        $('#showContent').append("[" + data[0] + "]发来"+data[1]+":\n" + data[2] + "\n");
    };
    socket.onclose = function () {
        console.log("Socket已关闭");
    };
    socket.onerror = function () {
        alert("Socket发生了错误");
    }

    window.unload = function () {
        socket.close();
    };
}

function sendMessage(){
    var message = $('#writeContext').val().trim();
    var sendTo = $('#sendTo').val();
    if (message == '' || sendFrom == '') {
        alert("发送失败，未选择发送人，或填写发送内容");
        return;
    }

    socket.send(sendTo+","+message);
}
