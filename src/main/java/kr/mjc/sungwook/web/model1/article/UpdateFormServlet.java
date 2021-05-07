package kr.mjc.sungwook.web.model1.article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/model1/article/updateForm")
public class UpdateFormServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String msg = Optional.ofNullable(request.getParameter("msg")).orElse("");
        out.format("""
                <!DOCTYPE html>
                <html>
                <body>
                  <h3>게시글 수정</h3>
                  <form action="update" method="post">
                     <p><input type="text" name="title" placeholder= %s required autofocus /></p>
                     <p><textarea name="content" placeholder= %s required /></p>
                      <p><button type="submit">수정</button></p>
                  </form>
                  <p style="color:red;">%s</p>
                </body>
                </html>
                """,session.getAttribute("ArticleTitle") ,session.getAttribute("ArticleTitle"),msg);
        out.close();
    }

}
