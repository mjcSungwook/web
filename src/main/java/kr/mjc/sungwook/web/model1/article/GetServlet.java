
package kr.mjc.sungwook.web.model1.article;


import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/model1/article/get")
public class GetServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Article article = articleDao.getArticle(Integer.parseInt(request.getParameter("articleId")));
            HttpSession session = request.getSession();
            if( session.getAttribute("userArticle") != null  ){
                session.setAttribute("ArticleTitle", article.getTitle());
                session.setAttribute("ArticleContent", article.getContent());
                response.sendRedirect(request.getContextPath() + "/model2/article/updateForm");
            }else {
                session.setAttribute("ArticleGet", article);
                response.sendRedirect(request.getContextPath() + "/model1/article/articleGet");
            }
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/model1/article/getForm?msg=Doesn't exist.");
        }

    }
}

