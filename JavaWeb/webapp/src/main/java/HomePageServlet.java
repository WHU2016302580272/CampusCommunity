import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
    private static Dao daoObject = new Dao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
//用于处理主界面的搜索内容的展示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要跳转的页数和查询条件
        String pageNo = request.getParameter("pageNo");
        String searchCon=request.getParameter("serchCon");
        int pageNow=1;
        if (pageNo != null) {
            pageNow = Integer.parseInt(pageNo);

        }

        OutputStream out = response.getOutputStream();
//将查询所得的二维数组转化为json传至jsp
        String data[][]=daoObject.pageQuery(pageNow,searchCon);
        String json=daoObject.changtoJson(data);

        out.write(json.getBytes("UTF-8"));
        out.close();

    }
}
