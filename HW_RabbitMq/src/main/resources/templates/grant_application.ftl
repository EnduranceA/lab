<!doctype html>
<html lang="ru">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8"/>

    <title>GRANT APPLICATION</title>
</head>
<body style="font-family: Georgia, 'Times New Roman', serif;">
<div style="text-align: right;">
    <b>Министру цифрового развития государственного<br/>
        управления, информационных технологий и связи <br/>
        Республики Татарстан<br/>
        от </b>${user.userDto.lastName} ${user.userDto.firstName} ${user.userDto.patronymic} <br/>
    <b>Паспорт: №</b> ${user.passportSeries} ${user.passportNumber} <br/>
    <b>Контактный телефон:</b> ${user.userDto.phoneNumber}<br/>
    <b>Адрес электронной почты:</b> ${user.email}
</div>

<div style="text-align: center; padding-top: 30px">
    <h6>ЗАЯВЛЕНИЕ</h6>
</div>
Прошу Вас рассмотреть мою кандидатуру на соискание гранта на обучение
на ${user.courseNumber} курсе по направлению подготовки «Программная инженерия» в ФГАОУ ВО «Казанский (Приволжский)
федеральный университет» в 2020 – 2021учебном году.
<div style="text-align: right;">
    ___________________ <br/>
    (подпись) <br/>
    ______________________ <br/>
    (дата подачи заявления)
</div>

</body>
</html>