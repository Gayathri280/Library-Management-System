import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class LmsDAO {

    static String jdbcURL;
    static String jdbcUserName;
    static String jdbcPassword;
    static Connection jdbcConnection;
    public LmsDAO(String jdbcURL, String jdbcUserName, String jdbcPassword) {

        this.jdbcURL = jdbcURL;
        this.jdbcUserName = jdbcUserName;
        this.jdbcPassword = jdbcPassword;
    }
    // Method to Establish the Connection
    static void connect() throws Exception {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        }
    }
    // Method to Disconnect the Connection
    static void disconnect() throws Exception {
        if (jdbcConnection != null || !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    // Method to get all the data from the database
    public static List<Lms> ListAllUser() throws Exception {
        List<Lms> lmsDaoList = new ArrayList<Lms>();
        String sql = "SELECT * FROM BooksJD";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String ISBN = resultSet.getString("ISBN");
            String Book_Name = resultSet.getString("Book_Name");
            String Author = resultSet.getString("Author");
            String Publisher_Name= resultSet.getString("Publisher_Name");
            
            Lms userobj = new Lms();
            userobj.setISBN(ISBN);
            userobj.setBookName(Book_Name);
            userobj.setAuthor(Author);
            userobj.setPublisherName(Publisher_Name);
            lmsDaoList.add(userobj);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return lmsDaoList;
    }
    
    public boolean updateBook(Lms book) throws Exception {
        String sql = "UPDATE bookJD SET BookName = ?";
        sql += " WHERE isbn = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getISBN());
        statement.setString(2, book.getBookName());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }

    public boolean deleteBook(Lms bookk) throws Exception {
        String sql = "DELETE FROM BooksJD where ISBN = ?";
         
        connect();
         
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql);
        statement2.setString(1, bookk.getISBN());
         
        boolean rowDeleted = statement2.executeUpdate() > 0;
        statement2.close();
        disconnect();
        return rowDeleted;     
    }
    public boolean insertBook(Lms newBooks) throws Exception {
        String sql = "INSERT INTO BooksJD  VALUES (?,?,?,?)";
        connect();
         
        PreparedStatement statement1 = jdbcConnection.prepareStatement(sql);
        statement1.setString(1, newBooks.getISBN());
        statement1.setString(2, newBooks.getBookName());
        statement1.setString(3, newBooks.getAuthor());
        statement1.setString(4, newBooks.getPublisherName());        
        boolean rowInserted = statement1.executeUpdate() > 0;
        statement1.close();
        disconnect();
        return rowInserted;
    }

    public static boolean checkUser(String email,String pass,String role) 
    {
        boolean st =false;
        try {
            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/gayathrirdb","gayathrir","Adgjmptw0@");
            PreparedStatement ps = con.prepareStatement("select * from register where email=? and pass=? and role=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, role);
            ResultSet rs =ps.executeQuery();
            st = rs.next();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
}