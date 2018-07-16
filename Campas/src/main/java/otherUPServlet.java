import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//其他用户的个人界面展示的个人信息
@WebServlet("/otherUPServlet")
public class otherUPServlet extends HttpServlet {
    Dao daoObj=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid=Integer.parseInt(request.getParameter("uid"));
        User u=daoObj.getUserById(uid,daoObj.getConn());
        OutputStream out = response.getOutputStream();
        String[] userInfo=new String[4];
        userInfo[0]=u.getUserName();
        userInfo[1]=u.getSchool();
        userInfo[2]=u.getProfesstion();
        userInfo[3]=u.getGrade();


        //将userInfo转化为json输出
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
