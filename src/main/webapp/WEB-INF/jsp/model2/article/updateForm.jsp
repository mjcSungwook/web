<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 수정</h3>
<form action="update" method="post">
    <input type="hidden" name="articleId" value="<%= session.getAttribute("ArticleId") %>">
    <p><input type="text" name="title" size="70" value= "<%= session.getAttribute("ArticleTitle")%>" required ></p>
    <p><textarea name="content" cols="70" rows="30" required><%= session.getAttribute("ArticleContent")%></textarea></p>
    <p><button type="submit">수정</button></p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>