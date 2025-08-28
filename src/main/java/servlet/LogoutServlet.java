package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends jakarta.servlet.http.HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s != null)
			s.invalidate();

		// remove remember cookie
		Cookie ck = new Cookie(LoginServlet.COOKIE_REMEMBER, "");
		ck.setMaxAge(0);
		resp.addCookie(ck);

		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
