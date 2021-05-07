
package kr.mjc.sungwook.web.model2.article;


import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import kr.mjc.sungwook.web.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/model2/article/get")
public class GetServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Article article = articleDao.getArticle(Integer.parseInt(request.getParameter("articleId")));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userArticle");


            if( session.getAttribute("userArticle") != null ){
                    if (article.getUserId() == user.getUserId()) {
                        session.setAttribute("ArticleId", article.getArticleId());
                        session.setAttribute("ArticleTitle", article.getTitle());
                        session.setAttribute("ArticleContent", article.getContent());
                        response.sendRedirect(request.getContextPath() + "/model2/article/updateForm");
                    } else {
                        response.sendRedirect(request.getContextPath() +
                                "/model2/article/getForm?msg=No permission");
                    }
             /*   }else{
                    if (article.getUserId() == user.getUserId()) {
                        session.setAttribute("ArticleId", article.getArticleId());
                        session.setAttribute("ArticleTitle", article.getTitle());
                        session.setAttribute("ArticleContent", article.getContent());
                        response.sendRedirect(request.getContextPath() + "/model2/article/deleteForm");
                    } else {
                        response.sendRedirect(request.getContextPath() +
                                "/model2/article/getForm?msg=No permission");
                    }
                }*/
            }else {
                session.setAttribute("Article", article);
                session.setAttribute("ArticleId", article.getArticleId());
                session.setAttribute("ArticleTitle", article.getTitle());
                session.setAttribute("WriterName", article.getName());
                session.setAttribute("cDate", article.getCdate());
                session.setAttribute("uDate", article.getUdate());
                session.setAttribute("ArticleContent", article.getContent());
                response.sendRedirect(request.getContextPath() + "/model2/article/articleGet");
            }
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/model2/article/getForm?msg=Doesn't exist.");
        }

    }
}

