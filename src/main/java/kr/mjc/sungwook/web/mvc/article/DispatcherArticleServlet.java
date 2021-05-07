
package kr.mjc.sungwook.web.mvc.article;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvc/*")
public class DispatcherArticleServlet extends HttpServlet {

    @Autowired
    ArticleController ariticleController;

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        switch (uri) {
            case "/mvc/article/articleList" -> ariticleController.articleList(request, response);
            case "/mvc/article/getForm" -> ariticleController.getForm(request, response);
            case "/mvc/article/get" -> ariticleController.get(request, response);
            case "/mvc/article/articleGet" -> ariticleController.articleGet(request, response);
            case "/mvc/article/loginForm" -> ariticleController.loginForm(request, response);
            case "/mvc/article/login" -> ariticleController.login(request, response);
            case "/mvc/article/afterLogin" -> ariticleController.afterLogin(request, response);
            case "/mvc/article/addForm" -> ariticleController.addForm(request, response);
            case "/mvc/article/articleAdd" -> ariticleController.articleAdd(request, response);
            case "/mvc/article/updateForm" -> ariticleController.updateForm(request, response);
            case "/mvc/article/update" -> ariticleController.articleUpdate(request, response);
            case "/mvc/article/deleteForm" -> ariticleController.deleteForm(request, response);
            case "/mvc/article/delete" -> ariticleController.articleDelete(request, response);

            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}


