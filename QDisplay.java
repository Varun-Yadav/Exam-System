import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
//public class AQuesa extends HttpServlet
public class QDisplay extends HttpServlet
{
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          ServletOutputStream out=res.getOutputStream();
          String s;
          try
          {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/online","root","root");
                Statement st=c.createStatement();

                s=req.getParameter("subject");
                HttpSession hs=req.getSession(true);
                hs.putValue("scode",s);

                ResultSet rs=st.executeQuery("select * from mquestions where qcode="+s+" ");



          }
          catch(Exception e)
          {
                  out.println(e.toString());
          }
     }
}
