import java.sql.*;

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
    public Connection getConn() {
        Connection conn = null;
        String url = "jdbc:mysql://120.79.70.239:3306/CampusCommunity?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        userName = "root";
        password = "123456";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//打印出错详细信息
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库
    public void close(Connection conn, Statement stmt, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {//关闭记录集
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {//关闭声明
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExist(String name) {
        String sql = "select * from User where uname = " + "'" + name + "'";
        Connection conn = getConn();
        boolean result = false;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("uid");
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, pstmt, rs);
        return result;
    }

    //匹配登录用户名和密码，如果数据库内有对应数据，则返回true，没有返回false
    public boolean match(String name, String password) {
        String sql = "select upwd from User where uname = " + "'" + name + "'";
        Connection conn = getConn();
        boolean result = false;
        Statement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("upwd").equals(password))
                    result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, pstmt, rs);
        return result;
    }

    //添加新用户数据到数据库（注册），成功返回true，失败返回false
    public boolean addUser(User user) {
        int result = -1;
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            String rowSql = "select * from User";
            st = conn.createStatement();
            rs = st.executeQuery(rowSql);
            int rownum = 0;
            while (rs.next()) {
                rownum++;
            }
            user.setUid(rownum);
            String sql = "insert into User values (?,?,?,?,?,?,?,null)";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUid());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getSchool());
            pstmt.setString(5, user.getGrade());
            pstmt.setString(6, user.getProfesstion());
            pstmt.setString(7, user.getSex());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        if (result == -1) return false;
        else return true;
    }

    public User getUserById(int uid) {
        User u = new User();
        String sql = "select * from User where uid = " + String.valueOf(uid);
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                u.setUid(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setSchool(rs.getString(4));
                u.setGrade(rs.getString(5));
                u.setProfesstion(rs.getString(6));
                u.setSex(rs.getString(7));
                //u.setAvatar(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        return u;
    }

    //添加文章到数据库，成功返回true，失败返回false
    public boolean addPassage(Passage passage) {
        int result = -1;
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            String rowSql = "select * from Passage";
            st = conn.createStatement();
            rs = st.executeQuery(rowSql);
            int rownum = 0;
            while (rs.next()) {
                rownum++;
            }
            passage.setPid(rownum);
            String sql = "insert into Passage values(?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, passage.getPid());
            pstmt.setString(2, passage.getTitle());
            pstmt.setString(3, passage.getPassageContent());
            Timestamp t = new Timestamp(passage.getProduceTime().getTime());
            pstmt.setTimestamp(4, t);
            pstmt.setInt(5, passage.getOwner().getUid());
            pstmt.setInt(6, passage.getFloor());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        close(conn, st, rs);
        if (result == -1) return false;
        else return true;
    }

    public Passage getPassageById(int pid) {
        Passage p = new Passage();
        String sql = "select * from Passage where pid = " + String.valueOf(pid);
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                p.setPid(rs.getInt(1));
                p.setTitle(rs.getString(2));
                p.setPassageContent(rs.getString(3));
                p.setProduceTime(rs.getTimestamp(4));
                p.setOwner(getUserById(rs.getInt(5)));
                p.setFloor(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        return p;
    }

    //添加评论到数据库，成功返回true，失败返回false
    public boolean addComment(Comment comment) {
        int result = -1;
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            String rowSql = "select * from Comment";
            st = conn.createStatement();
            rs = st.executeQuery(rowSql);
            int rownum = 0;
            while (rs.next()) {
                rownum++;
            }
            comment.setCid(rownum);
            String sql = "insert into Comment values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, comment.getCid());
            pstmt.setString(2, comment.getCommentContent());
            Timestamp t = new Timestamp(comment.getProduceTime().getTime());
            pstmt.setTimestamp(3, t);
            pstmt.setInt(4, comment.getThumbsUpTimes());
            pstmt.setInt(5, comment.getFloor());
            pstmt.setInt(6, comment.getPassage().getPid());
            pstmt.setInt(7, comment.getOwner().getUid());

            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        if (result == -1) return false;
        else return true;
    }

    public Comment getCommentById(int cid) {
        Comment c = new Comment();
        String sql = "select * from Comment where cid = " + String.valueOf(cid);
        Connection conn = getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                c.setCid(rs.getInt(1));
                c.setCommentContent(rs.getString(2));
                c.setProduceTime(rs.getTimestamp(3));
                c.setThumbsUpTimes(rs.getInt(4));
                c.setFloor(rs.getInt(5));
                c.setPassage(getPassageById(rs.getInt(6)));
                c.setOwner(getUserById(rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        return c;
    }

    //二维数组转化成json格式
    public String changtoJson(String[][] passageList) {
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        for (int i = 0; i < passageList.length; i++) {
            String[] passage = passageList[i];
            if (!first) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("avatar: '" + passage[0] + "', ");
            sb.append("school: '" + passage[1] + "', ");
            sb.append("profession: '" + passage[2] + "', ");
            sb.append("grade: '" + passage[3] + "', ");
            sb.append("pcontent: '" + passage[4] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();
        return json;
    }

    //获取查询得到的记录的数目，pageNo参数没用
    public int pageQuery1(int pageNo, String searchCon) {
        String sql;
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            sql = "select * from Passage where title like '%" + searchCon + "%'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String[][] pageQuery(int pageNo, String serchCon) {
        //分页查询，根据查询得到的结果集将文章对应的用户的头像（现阶段直接设为null）、学校、专业、年级，文章的内容填到二维字符串数组中
        String[][] data = new String[10][5];
        String sql;
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            sql = "select * from Passage where title like '%" + serchCon + "%'";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            for (int i = 0; i < (pageNo - 1) * 10; i++) {
                rs.next();
            }
            for (int i = 0; i < 10; i++) {
                int uid = rs.getInt(5);
                User u = getUserById(uid);
                data[i][0] = null;
                data[i][1] = u.getSchool();
                data[i][2] = u.getProfesstion();
                data[i][3] = u.getGrade();
                data[i][4] = rs.getString(3);
                if (rs.next()) continue;
                else break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(data[0][0]);
        return data;
    }

}
