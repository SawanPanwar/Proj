package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

@WebServlet(urlPatterns = "/LoginCtl")

public class LoginCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String login = req.getParameter("login");
		String password = req.getParameter("pwd");

		UserModel model = new UserModel();
		try {
			UserBean bean = model.authenticate(login, password);

			if (login.equals("") && password.equals("")) {

				RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
				req.setAttribute("i1", "Login is req...!!");
				req.setAttribute("i2", "Pass is req...!!");
				rd.forward(req, resp);

			} else if (login.equals("") || password.equals("")) {

				if (login.equals("")) {

					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					req.setAttribute("i1", "Login is req...!!");
					rd.forward(req, resp);

				} else {

					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					req.setAttribute("i2", "Pass is req...!!");
					rd.forward(req, resp);

				}
			}

			else {

				if (bean != null) {

					RequestDispatcher rd = req.getRequestDispatcher("WelcomeView.jsp");
					req.setAttribute("user", bean.getName());
					rd.forward(req, resp);

				} else {

					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					req.setAttribute("err", "Invalid User...!!");
					rd.forward(req, resp);

				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
