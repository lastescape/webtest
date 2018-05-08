<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <body>
    <sf:form method="POST" commandName="userInfo" action="register">
        Username: <sf:input path="username" /> <br/>
        Password: <sf:password path="password" /> <br/>
        <input type="submit" value="Register" >
    </sf:form>
    </body>
</html>
