import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Dao daoObject = new Dao();


    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        //获取用户名与密码
        User newUser = new User();
        String userName = request.getParameter("userName");
        String passwd = request.getParameter("passwd");
        String pattern = ".*[\\s]+.*";
        OutputStream out = response.getOutputStream();
        boolean match1 = Pattern.matches(pattern, userName);
        boolean match2 = Pattern.matches(pattern, passwd);
        if (match1 || match2) {
            String result = "包含非法字符";
            out.write(result.getBytes("UTF-8"));
            out.close();
        } else if (userName != null && passwd != null) {
            newUser.setUserName(userName);
            newUser.setPassword(passwd);
            newUser.setDefault();
            boolean isExist = daoObject.isExist(userName);//判断该用户名是否被注册
            if (isExist) {
                String result = "existed";
                out.write(result.getBytes("UTF-8"));
                out.close();
            } else {
                boolean added = daoObject.addUser(newUser);//注册成功，将用户的信息加入数据库
                if (added) {
                    session.setAttribute("login", true);
                    String result = "success";
                    out.write(result.getBytes("UTF-8"));
                } else {
                    String result = "failed";
                    out.write(result.getBytes("UTF-8"));
                }
                out.close();
            }
        } else {
            out.write("failed".getBytes("UTF-8"));
            out.close();
        }
    }
}
