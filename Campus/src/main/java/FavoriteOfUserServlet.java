import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 侯子坤 on 2018/7/19.
 */
@WebServlet("/FavoriteOfUserServlet")
public class FavoriteOfUserServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OutputStream out = response.getOutputStream();
        String uname=(String) session.getAttribute("username");
        int uid=daoObj.getIdByName(uname);
        String[][] favorites=daoObj.getFavoritePassageById(uid);

        //changToJson
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        for (int i = 0; i < favorites.length; i++) {
            String[] favorite = favorites[i];
            if (!first) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("pid: '" + favorite[0] + "', ");
            sb.append("ptitle: '" + favorite[1] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();

        out.write(json.getBytes("UTF-8"));
        out.close();

    }
}
