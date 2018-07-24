import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 侯子坤 on 2018/7/18.
 */
@WebServlet("/FavoritePassageServlet")
public class FavoritePassageServlet extends HttpServlet {
    private static Dao daoObject=new Dao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream out=response.getOutputStream();
        int pid = Integer.parseInt(request.getParameter("pid"));
        String condition=request.getParameter("hasFavorite");
        boolean hasFavorite=false;
        if(condition.equals("true"))
            hasFavorite=true;
        HttpSession session = request.getSession();
        String uname=(String)session.getAttribute("username");
        int uid = daoObject.getIdByName(uname);
        String result="";
        if(!hasFavorite){
            boolean success= false;
            success=daoObject.userFavoritePassage(uid,pid);
            if(success)result="true";
        }
        else {
            boolean success=false;
            success=daoObject.userCancelFavoritePassage(uid,pid);
            if(success)result="false";
        }
        out.write(result.getBytes("UTF-8"));
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
