<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title></title>
</head>
<body>
<h1>
    你的信息：${user.id} ----- ${user.name} ----- ${user.age}
</h1>
<h3>
    <br/>
    <#if user.id lt 12>
        ${user.name} 还是一个小屁孩
    <#elseif user.id lt 18>
        ${user.name} 快成年了
    <#else>
        ${user.name} 已经成年了
    </#if>
</h3>
</body>
</html>