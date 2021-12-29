import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
@WebServlet("/UserServlet")

public class LmsServlet extends HttpServlet{
    LmsDAO lmsDAO;
    public void init() throws ServletException{
        String jdbcURL = "jdbc:mysql://db4free.net/gayathrirdb";
        String jdbcUsername = "gayathrir";
        String jdbcPassword = "Adgjmptw0@";
        lmsDAO=new LmsDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }
    @Override
    protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lms> UserList=new ArrayList<Lms>();
        try {
            UserList=LmsDAO.ListAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
        Gson g=new Gson();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String user1 = g.toJson(UserList);
        resp.getWriter().print(user1);
        System.out.println(user1);
    }
    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete called");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Lms deleteItem = new Gson().fromJson(requestData, Lms.class);
        System.out.println("requestData Length->" + requestData.length());
        System.out.println("requestData->" + requestData);
        System.out.println(deleteItem.getISBN()+" "+deleteItem.getBookName()+" "+deleteItem.getAuthor()+" "+deleteItem.getPublisherName());
 
        try {
            lmsDAO.deleteBook(deleteItem);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost called");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Lms newBook = new Gson().fromJson(requestData, Lms.class);
        System.out.println("requestData Length->" + requestData.length());
        System.out.println("requestData->" + requestData);
        System.out.println(newBook.getISBN()+" "+newBook.getBookName()+" "+newBook.getAuthor()+" "+newBook.getPublisherName());
 
        try {
            lmsDAO.insertBook(newBook);
        } catch (Exception e) {
            //TODO: handle exception
        }    
    }

    
}
    
