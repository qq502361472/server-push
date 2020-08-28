$(function () {
    sseShow();
})

function sseShow() {

    console.log("sse");
    if(!window.EventSource){
        $('#sse').display("block");
        return;
    }

    var eventSource = new EventSource("/sse/getSSE");

    eventSource.onmessage=function (evt) {
        console.log(evt)
        var map = JSON.parse(evt.data);
        console.log(map);
        $('#apple').val(map.apple);
        $('#banana').val(map.banana);
        $('#orange').val(map.orange);
        $('#peach').val(map.peach);
    }

    eventSource.onopen=function (e) {
        console.log("Connecting server!");
    };

    eventSource.onerror=function () {
        console.log("error");
    };
}