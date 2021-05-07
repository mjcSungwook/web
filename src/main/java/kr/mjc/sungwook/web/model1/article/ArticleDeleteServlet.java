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

@WebServlet("/model1/article/delete")
public class ArticleDeleteServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");

        int userId = user.getUserId();
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        int articleDel = articleDao.deleteArticle(articleId,userId);
        if (articleDel > 0) {
            System.out.println("글을 삭제했습니다.");
            response.sendRedirect(request.getContextPath() + "/model1/article/articleList");
        }
        else
        response.sendRedirect(request.getContextPath() +
                "/model1/article/deleteForm?msg=No permission.");
    }
        /*
        if (articleDao.deleteArticle()) > 0) {
            System.out.println("글을 삭제했습니다.");
            response.sendRedirect(request.getContextPath() + "/model1/article/articleList");
        } else {
            response.sendRedirect(request.getContextPath() +
                    "/model1/article/updateForm?msg=No permission.");
        }
           */


}
