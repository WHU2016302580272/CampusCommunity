import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//获取展示在userPage界面的passage的相关信息；
@WebServlet("/qouServlet")
public class qouServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OutputStream out = response.getOutputStream();
        String uname=(String) session.getAttribute("username");
        int uid=daoObj.getIdByName(uname);
        String[][] passages=daoObj.PassagesOfUser(uid);

        //changeToJson
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        for (int i = 0; i < passages.length; i++) {
            String[] passage = passages[i];
            if (!first) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("title: '" + passage[0] + "', ");
            sb.append("pid: '" + passage[1] + "', ");
            sb.append("commentNum: '" + passage[2] + "', ");
            sb.append("enshrineNum: '" + passage[3] + "', ");
            sb.append("date: '" + passage[4] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();

        out.write(json.getBytes("UTF-8"));
        out.close();
    }
}
