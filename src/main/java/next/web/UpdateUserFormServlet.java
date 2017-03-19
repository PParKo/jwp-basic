package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;

/**
 * @author hoseong
 */
@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");

		req.setAttribute("user", DataBase.findUserById(userId));
		RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
		rd.forward(req, resp);
	}
}
