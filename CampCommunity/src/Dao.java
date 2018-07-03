import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dao {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //连接数据库
    public Connection getConn(){
        return null;
    }
    //关闭数据库
    public void close(Connection conn, Statement stmt, ResultSet rs){

    }
    //匹配登录用户名和密码，如果数据库内有对应数据，则返回true，没有返回false
    public boolean match(String name, String password){
        return true;
    }
    //添加新用户数据到数据库（注册），成功返回true，失败返回false
    public boolean addUser(User user){
        return true;
    }
    //添加文章到数据库，成功返回true，失败返回false
    public boolean addPassage(Passage passage){
        return true;
    }
    //添加评论到数据库，成功返回true，失败返回false
    public boolean addComment(Comment comment){
        return true;
    }
}
