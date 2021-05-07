package kr.mjc.sungwook.web.model1.user;

import kr.mjc.sungwook.web.dao.User;
import kr.mjc.sungwook.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/model1/user/login")
public class LoginServlet extends HttpServlet {

  @Autowired
  private UserDao userDao;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      User user = userDao.login(email, password);
      HttpSession session = request.getSession(); //세션을 만들어서
      session.setAttribute("USER", user);   //세션에 유저가 있느냐 없느냐로 판단
      response.sendRedirect(request.getContextPath() + "/model1/user/userInfo");
    } catch (EmptyResultDataAccessException e) {
      response.sendRedirect(request.getContextPath() +
          "/model1/user/loginForm?msg=Wrong email or password");
    }
  }
}
