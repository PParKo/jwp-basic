package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import next.model.User;

/**
 * @author hoseong
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User databaseUser = DataBase.findUserById(req.getParameter("userId"));

		if (databaseUser != null) {
			databaseUser.setPassword(req.getParameter("password"));
			databaseUser.setName(req.getParameter("name"));
			databaseUser.setEmail(req.getParameter("email"));
		}

		resp.sendRedirect("/user/list");
	}
}
