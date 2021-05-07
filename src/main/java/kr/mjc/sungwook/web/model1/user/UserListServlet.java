package kr.mjc.sungwook.web.model1.user;

import kr.mjc.sungwook.web.dao.Article;
import kr.mjc.sungwook.web.dao.ArticleDao;
import kr.mjc.sungwook.web.dao.User;
import kr.mjc.sungwook.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/model1/user/userList")
public class UserListServlet extends HttpServlet {

  @Autowired
  private UserDao userDao;

  @Override //URL로 접근
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html"); //html로 한다.
    PrintWriter out = response.getWriter();
    List<User> userList = userDao.listUsers(0, 100);
    out.println("<html><body><h3>사용자 목록</h3>"); //제목
    for (User user : userList)
      out.format("<p>%s</p>\n", user);
    out.println("</body></html>");
    out.close();
  }
}
