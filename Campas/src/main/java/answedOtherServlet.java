import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//其他用户回答过的问题
@WebServlet("/answedOtherServlet")
public class answedOtherServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid=Integer.parseInt(request.getParameter("uid"));
        OutputStream out = response.getOutputStream();
        //获取该用户的评论的内容，日期，被赞数，所属问题的标题及id，存在二维数组中并转换为json输出
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
