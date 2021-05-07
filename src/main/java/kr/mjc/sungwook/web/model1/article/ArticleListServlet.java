package kr.mjc.sungwook.web.model1.article;

import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/model1/article/articleList")
public class ArticleListServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override //URL로 접근
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html"); //html로 한다.
        PrintWriter out = response.getWriter();
        List<Article> articleList = articleDao.listArticles(0, 100);
        out.println("<html><body><h3>게시글 목록</h3>"); //제목
        for (Article article : articleList)
            out.format("<p>%s</p>\n", article);
        out.println("</body></html>");
        out.close();
    }
}
