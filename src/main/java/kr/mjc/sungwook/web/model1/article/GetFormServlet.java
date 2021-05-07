package kr.mjc.sungwook.web.model1.article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/model1/article/getForm")
public class GetFormServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String msg = Optional.ofNullable(request.getParameter("msg")).orElse("");
        out.format("""
        <!DOCTYPE html>
        <html>
        <body>
          <h3>게시글 번호입력</h3>
          <form action="get" method="post">
            <p><input type="text" name="articleId" placeholder="게시글 번호" required autofocus /></p>
            <p><button type="submit">확인</button></p>
          </form>
          <p style="color:red;">%s</p>
        </body>
        </html>
        """, msg);
        out.close();
    }
}

