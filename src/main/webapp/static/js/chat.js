function sendMessage(userId, text) {
    let body = {
        userId: userId,
        text: text
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            receiveMessage(userId)
        }
    });
}

// LONG POLLING
function receiveMessage(userId) {
    $.ajax({
        url: "/messages?userId=" + userId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li class="list-group-item">' + response[0]['text'] + '</li>');
            receiveMessage(userId);
        }
    })
}