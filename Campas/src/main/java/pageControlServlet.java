import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//用于获取查询所得的结果集的个数
@WebServlet("/pageControlServlet")
public class pageControlServlet extends HttpServlet {
    private static Dao daoObject = new Dao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //获取要跳转的页数和查询条件后，将查询得到的记录的数目返回jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        int pageNow = 1;
        if (pageNo != null) {
            pageNow = Integer.parseInt(pageNo);
        }
        String searchCon = request.getParameter("serchCon");
        OutputStream out = response.getOutputStream();

        int pageCount = daoObject.pageQuery1(pageNow, searchCon);
        String pageStr = Integer.toString(pageCount);
        out.write(pageStr.getBytes("UTF-8"));
        out.close();

    }
}

