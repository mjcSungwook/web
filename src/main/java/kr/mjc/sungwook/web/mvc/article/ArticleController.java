
package kr.mjc.sungwook.web.mvc.article;


import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import kr.mjc.sungwook.web.dao.User;
import kr.mjc.sungwook.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ArticleController {


    private final ArticleDao articleDao;
    private final UserDao userDao;


    @Autowired
    public ArticleController(ArticleDao articleDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.userDao = userDao;
    }


    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleList.jsp")
                .forward(request, response);
    }


    public void loginForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/loginForm.jsp")
                .forward(request, response);

    }

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDao.login(email, password);
            HttpSession session = request.getSession(); //세션을 만들어서
            session.setAttribute("userArticle", user);   //세션에 유저가 있느냐 없느냐로 판단
            response.sendRedirect(request.getContextPath() + "/mvc/article/afterLogin");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/model1/user/loginForm?msg=Wrong email or password");
        }

    }

    public void get(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Article article = articleDao.getArticle(Integer.parseInt(request.getParameter("articleId")));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userArticle");


            if( session.getAttribute("userArticle") != null ){
                if (article.getUserId() == user.getUserId()) {
                    session.setAttribute("ArticleId", article.getArticleId());
                    session.setAttribute("ArticleTitle", article.getTitle());
                    session.setAttribute("ArticleContent", article.getContent());
                    response.sendRedirect(request.getContextPath() + "/mvc/article/updateForm");
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
                response.sendRedirect(request.getContextPath() + "/mvc/article/articleGet");
            }
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/model2/article/getForm?msg=Doesn't exist.");
        }

    }

    public void getForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       request.getRequestDispatcher("/WEB-INF/jsp/model2/article/getForm.jsp")
                .forward(request, response);

    }

    public void articleGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleGet.jsp")
                .forward(request, response);

    }

    public void afterLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleLogin.jsp")
                .forward(request, response);

    }

    public void updateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/updateForm.jsp")
                .forward(request, response);
    }

    public void articleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Article article = new Article();
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");
        article.setUserId(user.getUserId());
        articleDao.updateArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");

    }

    public void addForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/addForm.jsp")
                .forward(request, response);
    }

    public void articleAdd(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");
        article.setUserId(user.getUserId());
        article.setName(user.getName());

        articleDao.addArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    public void deleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/deleteForm.jsp")
                .forward(request, response);
    }

    public void articleDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userArticle");

        int userId = user.getUserId();
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        int articleDel = articleDao.deleteArticle(articleId,userId);
        if (articleDel > 0) {
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        }
        else
            response.sendRedirect(request.getContextPath() +
                    "/model1/article/deleteForm?msg=No permission.");
    }
}
