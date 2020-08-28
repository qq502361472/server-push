$(function () {
    ajaxShow();
})

function ajaxShow() {
    $.get("/defered/getDefered", function (data, status) {
        console.log("data:" + data);
        console.log("status:" + status);
        $('#apple').val(data.apple);
        $('#banana').val(data.banana);
        $('#orange').val(data.orange);
        $('#peach').val(data.peach);
        ajaxShow();
    })
}