package kr.mjc.sungwook.web.model2.article;

import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import kr.mjc.sungwook.web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/model2/article/update")
public class ArticleUpdateServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Article article = new Article();
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");
        article.setUserId(user.getUserId());
        articleDao.updateArticle(article);
        response.sendRedirect(request.getContextPath() + "/model2/article/articleList");


    }
}