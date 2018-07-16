import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//用于修改用户信息
@WebServlet("/modifyInfoServlet")
public class modifyInfoServlet extends HttpServlet {
    Dao daoObj=new Dao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        OutputStream out = response.getOutputStream();
        String uname=(String) session.getAttribute("username");
        int uid=daoObj.getIdByName(uname);
        String sex = request.getParameter("sex");
        String school = request.getParameter("school");
        String profession = request.getParameter("profession");
        String grade = request.getParameter("grade");
        boolean modify=daoObj.changeUserInfo(uid,sex,grade,profession,school);
        if (modify) {
            String result = "success";
            out.write(result.getBytes("UTF-8"));
        } else {
            String result = "failed";
            out.write(result.getBytes("UTF-8"));
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
