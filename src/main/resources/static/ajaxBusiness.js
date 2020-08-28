$(function () {
    setInterval('ajaxShow()',1000);
})

function ajaxShow() {
    $.get("/ajax/getPrice", function (data, status) {
        console.log("data:" + data);
        console.log("status:" + status);
        $('#apple').val(data.apple);
        $('#banana').val(data.banana);
        $('#orange').val(data.orange);
        $('#peach').val(data.peach);
    })
}