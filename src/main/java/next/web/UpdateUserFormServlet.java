package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import core.db.DataBase;
import next.model.User;

/**
 * @author hoseong
 */
@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		String userId = req.getParameter("userId");
		if (user != null && StringUtils.equals(user.getUserId(), userId)) {
			req.setAttribute("user", DataBase.findUserById(userId));
			RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
			rd.forward(req, resp);

			return;
		}

		req.setAttribute("errorMessage", "다른 사용자 정보는 수정할 수 없습니다.");
		RequestDispatcher rd = req.getRequestDispatcher("/error/updateFormError.jsp");
		rd.forward(req, resp);
	}
}
