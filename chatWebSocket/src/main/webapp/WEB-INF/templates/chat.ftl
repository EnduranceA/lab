<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var webSocket;
        function connect() {
            webSocket = new SockJS('http://localhost:8080/chat');
            joinRoom();
            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messages').append('<li>' + json['userId'] + ':' + json['text'] + '</li>');
            };
        }
        function sendMessage(userId, roomIdentifier, text) {
            let body = {
                userId: userId,
                text: text,
                roomIdentifier: roomIdentifier
            };
            webSocket.send(JSON.stringify(body))
        }
        function joinRoom() {
            let body = {
                userId: '${userId}',
                roomIdentifier: '${roomIdentifier}'
            };
            $.ajax({
                url: "/connect",
                type: "POST",
                data: JSON.stringify(body),
                contentType: "application/json",
                dataType: "json",
            });
        }
    </script>
</head>
<body onload="connect()">
<h1>User ID: ${userId}</h1>
<h1>Room: ${roomIdentifier}</h1>
<div>
    <input id="message" placeholder="Your message">
    <button onclick="sendMessage('${userId}', '${roomIdentifier}', $('#message').val())">Send</button>
</div>
<div>
    <ul id="messages">
        <#list lastMessages as message >
            <li>${message.user.id}: ${message.text}</li>
        </#list>
    </ul>
</div>
</body>
</html>