import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//登录用户的个人页面展示的个人信息
@WebServlet("/userPageServlet")
public class userPageServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uname=(String) session.getAttribute("username");
        OutputStream out = response.getOutputStream();
        int uid=daoObj.getIdByName(uname);
        User u=daoObj.getUserById(uid,daoObj.getConn());
        String[] userInfo=new String[4];
        userInfo[0]=u.getUserName();
        userInfo[1]=u.getSchool();
        userInfo[2]=u.getProfesstion();
        userInfo[3]=u.getGrade();

        //changToJson
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        if (!first) {
            sb.append(",");
        }
        sb.append("{");
        sb.append("username: '" + userInfo[0] + "', ");
        sb.append("school: '" + userInfo[1] + "', ");
        sb.append("profession: '" + userInfo[2] + "', ");
        sb.append("grade: '" + userInfo[3] + "', ");
        sb.append("}");
        first = false;
        sb.append("]");
        json = sb.toString();

        out.write(json.getBytes("UTF-8"));
        out.close();
    }
}
