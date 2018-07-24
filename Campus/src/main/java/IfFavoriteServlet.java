import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/IfFavoriteServlet")
public class IfFavoriteServlet extends HttpServlet {
    Dao daoObject=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream out=response.getOutputStream();
        int pid = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        String uname=(String)session.getAttribute("username");
        int uid = daoObject.getIdByName(uname);
        boolean hasFavorite=daoObject.everFavoritePassage(uid,pid);
        String result="";
        if(hasFavorite)
            result="true";
        else result="false";
        out.write(result.getBytes("UTF-8"));
        out.close();
    }
}
