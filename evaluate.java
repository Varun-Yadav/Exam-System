import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class evaluate extends HttpServlet
{
Connection con=null;
Statement st=null;
ResultSet rs=null;
public void doGet(HttpServletRequest req,HttpServletResponse res)
throws IOException,ServletException
{
        int count=0;
        Hashtable ht=null;
        int size=0;
        try
        {                                                               
        ObjectInputStream ois=new ObjectInputStream(req.getInputStream());
        ht=(Hashtable)ois.readObject();
        size=ht.size();
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/online","root","root");
        st=con.createStatement();
        char lang='x',type='y';
        int number=0;
        
        Enumeration e=ht.keys();
        String ans="",key="";
        while(e.hasMoreElements())
        {
        key=(String)e.nextElement();
        lang=key.charAt(0);
        type=key.charAt(1);
        number=Integer.parseInt(key.substring(2));
        ans=(String)ht.get(key);
        rs=st.executeQuery("select answer from mquestions where lang='" + lang + "' and " +
                           "questype='" + type + "' and quesno=" + number);
        rs.next();        
        if(ans.equals(rs.getString(1)))
                count++;
        }        
        ObjectOutputStream oos=new ObjectOutputStream(res.getOutputStream());
        String str=String.valueOf(count);
        oos.writeObject(str);
        }
        catch(Exception e)
        {
        }

}

public void doPost(HttpServletRequest req,HttpServletResponse res)
throws IOException,ServletException
{
        doGet(req,res);
}

}
