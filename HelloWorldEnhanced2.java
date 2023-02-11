

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorldEnhanced2
 */
public class HelloWorldEnhanced2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorldEnhanced2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		String gender = request.getParameter("gender");
		String experience = request.getParameter("experience");
		
		String docType = "<!doctype html public -\" - //w3c//dtd html 4.0 transitional//en\">\n";
		out.println(docType + 
				"<html>\n"+
				"<head><title>User registration</title></head>\n"+
				"<body>\n"+
				"<h1 align=\"center\"><img src='http://localhost:80/FirstServlet/pics/thanksgiving.gif><br/> Welcome " + name + "</h1>"+
				"<ul>\n"+
				"<li><b> Your name</b>: " + name+ "\n"+
				"<li><b> Your email</b>: " + email+ "\n"+
				"<li><b> Your location</b>: " + location+ "\n"+
				"<li><b> Your gender</b>: " + gender+ "\n"+
				"<li><b> Your experience</b>: " + experience+ "\n"+
				"</ul>\n"+
				"</body></html>");
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
		 String sql;
		 sql = "SELECT * FROM student";
		 ResultSet rs = (ResultSet) stmt.executeQuery(sql);
		 //STEP 5: Extract data from result set
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
		} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
