<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
    <input id="message" placeholder="Your message">
    <button onclick="sendMessage('${userId}' ,$('#message').val())">Send</button>
</div>
<div>
    <h4>New questions</h4>
    <ul id="messages">

    </ul>
    <h4>Previously asked questions</h4>
    <#if listMessages?size != 0>
        <#list listMessages as message>
            <li class="list-group-item">${message.text}</li>
        </#list>
    </#if>
</div>
</body>
</html>