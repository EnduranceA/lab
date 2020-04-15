<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style.css">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="/static/js/chat.js"></script>
</head>
<body>
<h1>SUPPORT</h1>
<div>
    <h3>Hello! This is a support service. Ask your question, please</h3>
    <input id="message" class="messageInput" placeholder="Your message">
    <button onclick="sendMessage('${userId}' ,$('#message').val())" class="btn btn-dark">Send</button>
</div>
<div>
    <h4>New questions</h4>
    <ul id="messages">

    </ul>
    <h4>Previously asked questions</h4>
    <#if listMessages?size != 0>
        <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Question</th>
            <th scope="col">Answer</th>
        </tr>
        </thead>
        <tbody>
            <#list listMessages as message>
                <tr>
                    <td>${message.sender.firstName}  ${message.sender.lastName}</td>
                    <td>${message.text}</td>
                    <td>${message.answer}</td>
                </tr>
            </#list>
        </tbody>
        </table>
    </#if>
</div>
</body>
</html>