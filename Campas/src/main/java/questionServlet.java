import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

//用于展示passage.jsp界面除评论外的信息
@WebServlet("/questionServlet")
public class questionServlet extends HttpServlet {
    Dao daoObject=new Dao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int pid = Integer.parseInt(request.getParameter("pid"));
        OutputStream out = response.getOutputStream();
        Passage p=daoObject.getPassageById(pid,daoObject.getConn());
        String[] pInformation=new String[7];
        pInformation[0]=p.getTitle();
        pInformation[1]=p.getPassageContent();
        pInformation[2]=p.getProduceTime().toString();
        pInformation[3]=p.getOwner().getUserName();
        pInformation[4]=p.getOwner().getSchool();
        pInformation[5]=p.getOwner().getGrade();
        pInformation[6]=p.getOwner().getProfesstion();
        String result=daoObject.pChangtoJson(pInformation);
        out.write(result.getBytes("UTF-8"));
        out.close();
    }
}
