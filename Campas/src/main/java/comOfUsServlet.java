import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//获取需要展示在userPage界面的评论的相关信息
@WebServlet("/comOfUsServlet")
public class comOfUsServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OutputStream out = response.getOutputStream();
        String uname=(String) session.getAttribute("username");
        int uid=daoObj.getIdByName(uname);
        String[][] comments=daoObj.CommentsOfUser(uid);

        //changToJson
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        for (int i = 0; i < comments.length; i++) {
            String[] comment = comments[i];
            if (!first) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("content: '" + comment[0] + "', ");
            sb.append("date: '" + comment[1] + "', ");
            sb.append("upNumber: '" + comment[2] + "', ");
            sb.append("ptitle: '" + comment[3] + "', ");
            sb.append("pid: '" + comment[4] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();

        out.write(json.getBytes("UTF-8"));
        out.close();
    }
}
