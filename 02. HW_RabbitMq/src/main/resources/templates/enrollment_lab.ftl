<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>
    <title>Hello, world!</title>
</head>
<body style="font-family: Georgia, 'Times New Roman', serif;">
<div style="text-align: right;">
    <b>И.о директора Высшей школы ИТИС <br/>
        Абрамскому Михаилу Михайловичу <br/>
        Студент:</b> ${user.userDto.lastName} ${user.userDto.firstName} ${user.userDto.patronymic} <br/>
    <b>группа:</b>  ${user.userDto.groupNumber} <br/>
    <b>телефон:</b> ${user.userDto.phoneNumber} <br/>
</div>
<div style="text-align: center">
    <b>ЗАЯВЛЕНИЕ</b>
</div> <br/>
<b>Прошу меня зачислить в лабораторию </b> ${user.labName}
<br/><br/>
<h6>Подпись студента ____________________</h6>
<h6>Дата ______________________</h6>
<h6>Подпись преподавателя _________________</h6>

</body>
</html>