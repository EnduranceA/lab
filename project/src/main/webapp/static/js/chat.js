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
            $('#messages').first().after('<li class="list-group-item">' + response[0]['text'] + '</li>' +
                '<div class="alert alert-success" role="alert">' +
                '  <h4 class="alert-heading">Well done!</h4>' +
                '  <p>You have successfully added your question. Please expect a response from administrators soon.</p>' +
                '  <hr>' +
                '  <p class="mb-0">Thank you!</p>' +
                '</div>'
            );
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

