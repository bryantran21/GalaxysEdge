import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String gender = request.getParameter("gender");
		String experience = request.getParameter("experience");

		
		response.setContentType("text/plain;charset=UTF-8");
		final PrintWriter out = response.getWriter();
		out.println("Welcome to back: " + name);
		out.println("Your name: " + name);
		out.println("Your email: " + email);
		out.println("Your location: " + location);
		out.println("Your gender: " + gender);
		out.println("Your experience: " + experience);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
