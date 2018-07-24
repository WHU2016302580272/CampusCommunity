import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
//提交passage
@WebServlet("/passageServlet")
public class passageServlet extends HttpServlet {
    private static Dao daoObject=new Dao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        HttpSession session = request.getSession();
        String uname=(String)session.getAttribute("username");
        OutputStream out=response.getOutputStream();
        int uid=0;
        uid=daoObject.getIdByName(uname);
        String result=null;

        if(title.equals("")||content.equals("")){
            result="标题和内容不能为空";
        }
        else{
            Passage newPa=new Passage();
            newPa.setTitle(title);
            newPa.setPassageContent(content);
            newPa.setProduceTime(new Date());
            newPa.setOwner(daoObject.getUserById(uid,daoObject.getConn()));
            boolean addP=daoObject.addPassage(newPa);
            if(addP){
                result="提问成功，请等待热心网游的回答吧";
            }else{
                result="出错！请稍后重试";
            }
        }
        out.write(result.getBytes("UTF-8"));
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
