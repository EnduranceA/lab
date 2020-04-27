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
        dataType: "json"
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

function sendResponse(messageId, text) {
    let body = {
        userId: messageId,
        text: text
    };

    $.ajax({
        url: "/admin/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        success: function () {
        }
    });
}

