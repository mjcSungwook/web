package kr.mjc.sungwook.web.model1.article;

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

@WebServlet("/model1/article/update")
public class ArticleUpdateServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Article article = new Article();

        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));

        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");
        article.setUserId(user.getUserId());


        if (articleDao.updateArticle(article) > 0) {
            System.out.println("글을 수정했습니다.");
            response.sendRedirect(request.getContextPath() + "/model1/article/articleList");
        } else {
            response.sendRedirect(request.getContextPath() +
                    "/model1/article/updateForm?msg=No permission.");
        }

    }
}