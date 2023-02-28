package frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.User;
import backend.UserRepo;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserRepo repo = UserRepo.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = repo.getUser(username, password);
		if ( u != null) {
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();//invalido la sessione se esiste
			}
			
			HttpSession currentSession = request.getSession();//creo una nuova sessione
			currentSession.setAttribute("username", username);
			currentSession.setMaxInactiveInterval(2*60);//inattività di 2 minuti
			
			response.sendRedirect("LoginSuccess.jsp");
			
		}else{
			response.getWriter().println(error());
		}
	}
	
	private String error() {
		return "<html><body><p>errore</p></body></html>";
	}

}
