import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String role = request.getParameter("role");
        if(LmsDAO.checkUser( email , pass , role  ))
        {
            HttpSession session=request.getSession();  
           
            session.setAttribute("uname",email);  
            session.setAttribute("role",role);  
            // RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            
            request.setAttribute("uname", (String)session.getAttribute("uname"));
            request.setAttribute("role", (String)session.getAttribute("role"));
            
            System.out.println(role);
            
            if(role.equals("user")){
                
                RequestDispatcher rs = request.getRequestDispatcher("StuLogin.html");
                rs.forward(request, response);
            }
            else   {
                System.out.println("else");
                RequestDispatcher rs = request.getRequestDispatcher("AdmLogin.html");
                rs.forward(request, response);
               
            }
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
    }
}