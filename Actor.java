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
public class Actor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Actor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jumbo="", actorName="", moviesIn="", picture="", movieList="", movieName = "", cover ="";
		int age;
		String id = request.getParameter("id");
		
		boolean signedIn = false;
		String userBtn = "";
		String userPass = "";
		String username = request.getParameter("username");
		
		int movieID;
		int year;
		
		if(username != null){
			signedIn = true;
		}
		
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
			
			if(signedIn == false){
				userBtn = "" +
						"<div class=\"d-flex\">"+
						"	<button class=\"btn btn-dark\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModalToggle\">"+
						"		<i class=\"fa-solid fa-right-to-bracket\" style=\"color: white;\">Log In</i>"+
						"	</button>"+
						"	<div class=\"modal fade\" id=\"exampleModalToggle\" aria-hidden=\"true\" aria-labelledby=\"exampleModalToggleLabel\" tabindex=\"-1\">"+
						"		<div class=\"modal-dialog modal-dialog-centered\">"+
						"			<div class=\"modal-content\">"+
						"				<div class=\"modal-header\">"+
						"					<h5 class=\"modal-title\" id=\"exampleModalToggleLabel\">User Login</h5>"+
						"			    	<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>"+
						"				</div>"+
						"				<div class=\"modal-body\">"+
						"					<form>"+
						"						<div class=\"mb-3 row\" style=\"width:40%; margin: auto\">"+
						"							<a class=\"btn btn-secondary\" href=\"./Login\" role=\"button\">Login</a>"+
						"						</div>"+
						"						<div class=\"mb-3 row\" style=\"width:40%; margin: auto\">"+
						"							<a class=\"btn btn-secondary\" href=\"./SignUp\" role=\"button\">New? Sign Up!</a>"+
						"						</div>"+
						"					</form>"+
						"				</div>"+
						"			</div>"+
						"		</div>"+
						"	</div>"+
						"</div>";
			} else {
				String user = "SELECT * FROM users WHERE username = '" + username + "';";
				System.out.println(user);
				
				ResultSet rs = (ResultSet) stmt.executeQuery(user);
				
				while(rs.next()){
					username = rs.getString("username");
			
					userBtn = "" +
					"<a href=\"./EditProfile\">"+
					"<p style =\"color: white\">View Account, " + username + "</p>"+
					"</a>";
					
					userPass = "<input type='hidden' name='username' value='" + username + "'>";
					System.out.println(userPass);
				}
			}
			
			String movies = "SELECT * FROM movies WHERE FIND_IN_SET(" + id + ",actorsIn);";
			ResultSet rs2 = (ResultSet) stmt.executeQuery(movies);
			
			while(rs2.next()){
				movieName = rs2.getString("name");
				year = rs2.getInt("year");
				cover = rs2.getString("cover");
				movieID = rs2.getInt("id");
				System.out.println(movieID);
				
				movieList = movieList + "" +
						"<div class=\"col-6\">"+
						"	<form id=" + movieID + " action=\"http://107.23.136.184/FirstServlet/Movie\" method='post'>"+
						"	<a href=\"./Movie\" class='list-group-item list-group-item-action' style=\"background-color:white; border-radius: 25px; margin-left: auto; margin-right: auto; width:50%\">"+
						"		<input type='image' src=\"" + cover + "\" class=\"d-block w-100\" alt=\"...\" style='border: 5px solid yellow'>"+
						"		<input type='hidden' name='id' value=" + movieID + ">" + userPass +
						"	</a>"+
						"	</form>"+
						"</div>";	
				}
			
			String sql = "SELECT * FROM actors WHERE id=\'" + id + "\';";
			System.out.println(sql);
		 	//STEP 5: Extract data from result set
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while(rs.next()){
				age = rs.getInt("age");
				actorName = rs.getString("name");
				picture= rs.getString("picture");

				jumbo = jumbo + "" +
						"	<div class=\"jumbotron row\" style=\"background-color:white; border-radius: 25px; margin-left: auto; margin-right: auto; width:50%\">"+
						"	  <h1 class=\"display-4\">"+ actorName + "(" + age + ")" + "</h1>"+
						"		<div class=\"text-center\">"+
						"			<img id= " + id + " src=" + picture + " alt=" + actorName + " style=\"max-height: 24.75rem;height: 100%; margin-left: auto; margin-right: auto;\">"+
						"		</div>"+
						"	  <hr class=\"my-3\">"+
						"	</div>";
			}
			
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
		"	<link rel=\"icon\" type=\"image/x-icon\" href=\"./favicon.ico\">"+
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
		"				<form action=http://107.23.136.184/FirstServlet/Home method='post'>"+ userPass +
		"		        	<li class=\"nav-item\">"+
		"		          		<button class=\"btn btn-dark\" style=\"color:yellow\">Home</a>"+
		"		        	</li>"+
		"	        	</form>"+
		"				<form action=http://107.23.136.184/FirstServlet/Movies method='post'>"+ userPass +
		"		        	<li class=\"nav-item\">"+
		"		          		<button class=\"btn btn-dark\" type=\"submit\" aria-current=\"page\" style=\"color:yellow\">Movies</button>"+
		"		        	</li>"+
		"	        	</form>"+
		"				<form action=http://107.23.136.184/FirstServlet/Actors method='post'>"+ userPass +
		"		        	<li class=\"nav-item\">"+
		"		          		<button class=\"btn btn-dark\" type=\"submit\" style=\"color:yellow\">Actors</button>"+
		"		        	</li>"+
		"	        	</form>"+
		"			</ul>"+
		"			<ul class=\"nav navbar-nav navbar-right\">"+
		""				+ userBtn+
		"			</ul>"+
		"		</div>"+
		"	  </div>"+
		"	</nav>"+
		" 	<div class='container'>" + jumbo +
		"		<div class='row' style=\"color: white\">"+ movieList +
		"		</div>"+
		"	</div>"+
		"</body>"+
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