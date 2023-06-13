<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<div>
    <h1>eto raha anampy olona</h1>
    <form action="./manampy.do">
        <input type="text" name="nom" placeholder="nom" >
        <input type="number" name="age"placeholder="age" >
        <input type="submit">
    </form>

</div>
</body>
</html>