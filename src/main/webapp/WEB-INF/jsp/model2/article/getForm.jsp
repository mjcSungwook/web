<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 번호입력</h3>
<form action="get" method="post">
    <p><input type="text" name="articleId" placeholder="게시글 번호" required autofocus /></p>
    <p><button type="submit">확인</button></p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>