package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

/**
 * @author hoseong
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");

		User user = DataBase.findUserById(userId);
		if (user == null) {
			loginFailForword(req, resp);
			return;
		}

		String password = req.getParameter("password");
		if (user.getPassword().equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("/");
			return;
		}

		loginFailForword(req, resp);
	}

	private void loginFailForword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		RequestDispatcher rd = req.getRequestDispatcher("login_failed.jsp");
		rd.forward(req, resp);
	}
}
