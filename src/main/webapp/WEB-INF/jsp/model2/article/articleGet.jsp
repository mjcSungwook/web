<%@ page import="kr.mjc.sungwook.web.dao.Article" %>
<!DOCTYPE html>
<html>
<body>
<h2>게시글 정보</h2>
<b>제목: </b> <%= session.getAttribute("ArticleTitle") %><br>
<b>글번호: </b>  <%= session.getAttribute("ArticleId") %>  &ensp;
<b>작성자: </b>  <%= session.getAttribute("WriterName") %> &ensp;
<b>작성일자: </b> <%= session.getAttribute("cDate") %>   &ensp;
<b>수정일자: </b> <%= session.getAttribute("uDate") %><br>
<h3>내용</h3>
<p><%= session.getAttribute("ArticleContent") %></p>
<p><a href="/articleIndex.html" >홈으로</a></p>
</body>
</html>
