function sendFile() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    // данные для отправки
    let formData = new FormData();
    // забрал файл из input
    let files = ($('#file'))[0]['files'];
    // добавляю файл в formData
    [].forEach.call(files, function (file) {
        formData.append("file", file);
    });

    $.ajax({
        type: "POST",
        beforeSend: function(request) {
            request.setRequestHeader(header, token);},
        url: "/profile/file",
        data: formData,
        processData: false,
        contentType: false
    })
        .done(function (html) {
            $("#content").html(html);
        })
        .fail(function () {
            alert('Error')
        });
}