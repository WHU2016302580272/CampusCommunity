import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//他人提出过的问题
@WebServlet("/queOfUserServlet")
public class queOfUserServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid=Integer.parseInt(request.getParameter("uid"));
        OutputStream out = response.getOutputStream();
        //获取该用户提出过的问题的标题，id，回答数，收藏数，日期，存在二维数组中并转换为json输出
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
