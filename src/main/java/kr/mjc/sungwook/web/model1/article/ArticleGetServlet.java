package kr.mjc.sungwook.web.model1.article;


import kr.mjc.sungwook.web.dao.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/model1/article/articleGet")
public class ArticleGetServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Article article = (Article) session.getAttribute("ArticleGet");
        out.println("<html><body><h3>게시글 정보</h3>");
        out.format("<p>제목: %s</p>", article.getTitle());
        out.format("<p>내용: %s</p>", article.getContent());
        out.println("</body></html>");
        out.close();


    }

}
