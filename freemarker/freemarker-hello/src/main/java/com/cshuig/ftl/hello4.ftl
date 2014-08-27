<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title></title>
</head>
<body>
<h1>
</h1>
<h3>
    <br/>
    <#list users as user>
        ${user.id}  ---  ${user.name}  ---- ${user.age} <br/>
    </#list>
</h3>
</body>
</html>