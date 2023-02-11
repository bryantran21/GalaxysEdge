import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HelloWorld
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/sw_db";

		// Database credentials
		String USER = "root";
		String PASS = "";

		Connection conn = null;
		Statement stmt = null;
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = (Statement) conn.createStatement();
		 	//STEP 5: Extract data from result set
			
		} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		////////////////////////////////////
		
		response.setContentType("text/html;charset=UTF-8");
		final PrintWriter out = response.getWriter();
		
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		out.println(docType +
"<html>"+
"<head>"+
"    <meta charset=\"utf-8\">"+
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"+
"	<title>Galaxy's Edge</title>"+
"	 <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi\" crossorigin=\"anonymous\">"+
"	<style>"+
"		body {"+
"		  background-image: url(\"https://i.pinimg.com/originals/4a/a9/9b/4aa99b6ad2d2cbc7cb04a41571885a8f.jpg\");"+
"		}"+
"	</style>"+
"		<link rel=\"icon\" type=\"image/x-icon\" href=\"./favicon.ico\">"+
"</head>"+
""+
"<body>"+
"	 <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>	"+
"	 <script src=\"https://kit.fontawesome.com/fc59a9291c.js\" crossorigin=\"anonymous\"></script>"+
""+
"<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">"+
"	  <div class=\"container-fluid\">"+
"	    <a class=\"navbar-brand\">"+
"	    	<img src=\"https://loodibee.com/wp-content/uploads/Star-Wars-transparent-logo.png\" alt=\"\" width=\"50\" height=\"50\">"+
"	    </a>"+
"	    "+
"	    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">"+
"	      <span class=\"navbar-toggler-icon\"></span>"+
"	    </button>"+
"	    "+
"	    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">"+
"	    	<ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">"+
"				<form action=http://107.23.136.184/FirstServlet/Home method='post'>"+ 
"		        	<li class=\"nav-item\">"+
"		          		<button class=\"btn btn-dark\" style=\"color:yellow\">Home</a>"+
"		        	</li>"+
"	        	</form>"+
"				<form action=http://107.23.136.184/FirstServlet/Movies method='post'>"+ 
"		        	<li class=\"nav-item\">"+
"		          		<button class=\"btn btn-dark\" type=\"submit\" aria-current=\"page\" style=\"color:yellow\">Movies</button>"+
"		        	</li>"+
"	        	</form>"+
"				<form action=http://107.23.136.184/FirstServlet/Actors method='post'>"+
"		        	<li class=\"nav-item\">"+
"		          		<button class=\"btn btn-dark\" type=\"submit\" style=\"color:yellow\">Actors</button>"+
"		        	</li>"+
"	        	</form>"+
"			</ul>"+
"			<ul class=\"nav navbar-nav navbar-right\">"+
"			</ul>"+
"		</div>"+
"	  </div>"+
"	</nav>"+
"	"+
"	<form action=\"http://107.23.136.184/FirstServlet/Home \" method=\"post\">"+
"		<div class=\"container\">"+
"			<div class=\"row align-items-center\">"+
"				  <div class=\"form-floating mb-3\">"+
"					  <input type=\"username\" class=\"form-control\" name=\"username\" required>"+
"					  <label for=\"username\">Username</label>"+
"				  </div>"+
"				  "+
"				  <div class=\"form-floating mb-3\">"+
"					  <input type=\"password\" class=\"form-control\" name=\"password\" required>"+
"					  <label for=\"password\">Password</label>"+
"				  </div>"+
"				  <input type=\"hidden\" name='accountType' value='returningUser'>"+
"				<button type=\"submit\" class=\"btn btn-primary\">Submit</button>"+
"			</div>"+
"		</div>"+
"	</form>"+
"	"+
"	</body>"+
"</html>"
			);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}