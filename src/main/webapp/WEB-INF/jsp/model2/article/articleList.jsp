<%@ page import="java.util.List" %>
<%@ page import="kr.mjc.sungwook.web.dao.Article" %>
<!DOCTYPE html>
<html>
<head>
    <font size="6">
        게시글 목록  &ensp;
    </font>
</head>
<a href="/articleIndex.html" >홈으로</a>
<a href="/articleIndex.html" >로그아웃 <%session.invalidate();%></a>
<body>
<%
    List<Article> articleList = (List<Article>) request.getAttribute("articleList");
    for (Article article : articleList) {%>
<p><%= article %>
</p>
<%
    }
%>
</body>
</html>
