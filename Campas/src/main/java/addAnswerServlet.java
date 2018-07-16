import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
//提交评论
@WebServlet("/addAnswerServlet")
public class addAnswerServlet extends HttpServlet {
    Dao daoObject = new Dao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("username");
        String answer = request.getParameter("answer");
        int uid = daoObject.getIdByName(uname);
        User user = daoObject.getUserById(uid, daoObject.getConn());
        String result = null;
        OutputStream out = response.getOutputStream();
;
        if (answer.equals("")) {
            result = "内容不能为空";
        } else {
            int pid = Integer.parseInt(request.getParameter("pid"));
            Passage passage = daoObject.getPassageById(pid, daoObject.getConn());


            Comment c = new Comment();
            c.setCommentContent(answer);
            c.setOwner(user);
            c.setFloor(1);
            c.setPassage(passage);
            c.setThumbsUpTimes(0);
            c.setProduceTime(new Date());
            boolean addC = daoObject.addComment(c);

            if (addC) {
                result = "回答成功，感谢您的帮助";
            } else {
                result = "出错！请稍后重试";
            }

        }
        out.write(result.getBytes("UTF-8"));
        out.close();
    }
}
