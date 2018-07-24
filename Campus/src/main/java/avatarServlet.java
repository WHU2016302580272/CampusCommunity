import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 侯子坤 on 2018/7/17.
 */
@WebServlet("/avatarServlet")
public class avatarServlet extends HttpServlet {
    private static Dao daoObject = new Dao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String uname=(String) session.getAttribute("username");
        int uid=daoObject.getIdByName(uname);
        String avatar = request.getParameter("avatar");
        session.setAttribute("avatar",avatar);
        OutputStream out = response.getOutputStream();
        boolean success = daoObject.updateAvatar(uid,avatar);

        if (success) {
            String result = "头像修改成功";
            out.write(result.getBytes("UTF-8"));
        } else {
            String result = "繁忙，请稍后重试";
            out.write(result.getBytes("UTF-8"));
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
