import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
//获取passage.jsp界面的评论列表
@WebServlet("/commentServlet")
public class commentServlet extends HttpServlet {

    Dao daoObject=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pid = Integer.parseInt(request.getParameter("pid"));
        OutputStream out = response.getOutputStream();
        //将查询所得的二维数组转化为json传至jsp
        String data[][]=daoObject.commentsofPassage(pid);
        String json=daoObject.changCommenttoJson(data);
        out.write(json.getBytes("UTF-8"));
        out.close();
    }
}
