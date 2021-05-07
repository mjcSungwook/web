<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 등록</h3>
<form action="articleAdd" method="post">
    <p><input type="text" size="70" name="title" placeholder="제목" required autofocus/></p>
    <p><textarea cols="70" rows="30" name="content" placeholder="내용" required></textarea></p>
    <p><button type="submit">저장</button></p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>