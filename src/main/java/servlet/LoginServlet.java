package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	public static final String COOKIE_REMEMBER = "username";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}

		// Check remember cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (COOKIE_REMEMBER.equals(c.getName())) {
					String username = c.getValue();
					UserService s = new UserServiceImpl();
					User u = s.findByUserName(username);
					if (u != null) {
						session = req.getSession(true);
						session.setAttribute("account", u);
						resp.sendRedirect(req.getContextPath() + "/waiting");
						return;
					}
				}
			}
		}

		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRemember = "on".equals(req.getParameter("remember"));

		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
			return;
		}

		UserService service = new UserServiceImpl();
		User user = service.login(username, password);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);

			if (isRemember) {
				Cookie ck = new Cookie(COOKIE_REMEMBER, username);
				ck.setMaxAge(30 * 24 * 3600); // 30 days
				resp.addCookie(ck);
			}

			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		}
	}
}
