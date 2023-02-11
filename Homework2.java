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
public class Homework2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homework2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name, email, location, gender, experience;
		name 		= request.getParameter("name");
		email 		= request.getParameter("email");
		location 	= request.getParameter("location");
		gender 		= request.getParameter("gender");
		gender		= gender.substring(0,1);
		experience 	= request.getParameter("experience");
		///////////////////////////////
		
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		//String DB_URL = "jdbc:mysql://52.26.86.130:3306/student";
		String DB_URL = "jdbc:mysql://localhost:3306/testDB";

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
			String sql = "INSERT INTO student (name, email, location, gender, experience) " +
						 "VALUES (\'" + name + "\', \'" + email + "\', \'" + location + "\', \'" + gender + "\', \'" + experience + "\');";
			//sql = "SELECT * FROM student";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		 	//STEP 5: Extract data from result set
			/**
			while(rs.next()){
				//Retrieve by column name
				String myName = rs.getString("name");
				String Myexperience = rs.getString("experience");
				//Display values
				System.out.print("name: " + myName);
				System.out.println(", experience: " + Myexperience);
				//return the query results to client
				name = name + "-" + myName;
				experience = experience + "-" + Myexperience;
			}
			*/
		} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		////////////////////////////////////
		
		response.setContentType("text/html;charset=UTF-8");
		final PrintWriter out = response.getWriter();
		
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
		out.println(docType +
				"<html>\n" +
				"	<head><title>User Registration</title></head>\n" +
				"	<body>\n" +
				"		<h1 align=\"center\"><img src='http://34.204.51.62/pics/thanksgiving.gif'>Welcome " + name + "</h1>\n" +
				"		<ul>\n" +
				"			<li><b>Your name</b>: " + name + "</li>\n" +
				"			<li><b>Your email</b>: " + email + "</li>\n" +
				"			<li><b>Your location</b>: " + location + "</li>\n" +
				"			<li><b>Your gender</b>: " + gender + "</li>\n" +
				"			<li><b>Your experience</b>: " + experience + "</li>\n" +
				"		</ul>\n" +
				"	</body>\n" +
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
