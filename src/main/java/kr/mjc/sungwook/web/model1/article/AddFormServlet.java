package kr.mjc.sungwook.web.model1.article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/model1/article/addForm")
public class AddFormServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String msg =  //사용자 이름 NULL조건문
                Optional.ofNullable((String) request.getParameter("msg")).orElse("");
        out.format("""
        <!DOCTYPE html>
        <html>
        <body>
          <h3>게시글 등록</h3>
          <form action="articleAdd" method="post"> 
            <p><input type="text" name="title" placeholder="제목" required autofocus /></p>
            <p><textarea name="content" placeholder="내용" required /></textarea></p>
            <p><button type="submit">저장</button></p>
          </form>
          <p style="color:red;">%s</p>
        </body>
        </html>
        """, msg);  //%s에 msg를 집어넣기 위해 .format 사용 , post를 사용해 데이터 전달
        out.close();
    }
}
