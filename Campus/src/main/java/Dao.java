import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.hibernate.Hibernate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
            int id = 0;
            while (rs.next()) {
                if(rs.getInt(1)>id)id=rs.getInt(1);
            }
            user.setUid(id+1);
            String sql = "insert into User values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, user.getUid());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getSchool());
            pstmt.setString(5, user.getGrade());
            pstmt.setString(6, user.getProfesstion());
            pstmt.setString(7, user.getSex());
            pstmt.setString(8, user.getAvatar());
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        if (result == -1) return false;
        else return true;
    }
    //更新上传的头像
    public boolean updateAvatar(int uid, String newAvatar) {
        String [] a=newAvatar.split(":");
        newAvatar=a[1];
        String sql = "update User set avatar ='" + newAvatar + "' where uid=" + String.valueOf(uid);
        Connection conn = null;
        Statement st = null;
        int result = -1;
        try {
            conn = getConn();
            st = conn.createStatement();
            result = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, null);
        if (result == 1) return true;
        else return false;
    }

    public User getUserById(int uid,Connection conn) {
        User u = new User();
        String sql = "select * from User where uid = " + String.valueOf(uid);
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
                u.setAvatar(rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(null,st,rs);
        return u;
    }
    public int getIdByName(String name){
        Connection conn=null;
        String sql="select uid from User where uname ='"+name+"'";
        Statement st=null;
        ResultSet rs=null;
        int uid=-1;
        try{
            conn=getConn();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                uid=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,rs);
        return uid;
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
            int id = 0;
            while (rs.next()) {
                if(rs.getInt(1)>id)id=rs.getInt(1);
            }
            passage.setPid(id+1);
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

    public Passage getPassageById(int pid,Connection conn) {
        Passage p = new Passage();
        String sql = "select * from Passage where pid = " + String.valueOf(pid);
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
                p.setOwner(getUserById(rs.getInt(5),conn));
                p.setFloor(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(null, st, rs);
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
            int id = 0;
            while (rs.next()) {
                if(rs.getInt(1)>id)id=rs.getInt(1);
            }
            comment.setCid(id+1);
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
            sql="update Passage set floor = floor+1 where pid="+String.valueOf(comment.getPassage().getPid());
            result+=pstmt.executeUpdate(sql);
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, rs);
        if (result == -1) return false;
        else return true;
    }

    public Comment getCommentById(int cid,Connection conn) {
        Comment c = new Comment();
        String sql = "select * from Comment where cid = " + String.valueOf(cid);
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
                c.setPassage(getPassageById(rs.getInt(6),conn));
                c.setOwner(getUserById(rs.getInt(7),conn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(null, st, rs);
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
            sb.append("pid:'" + passage[5] + "', ");
            sb.append("uid:'" + passage[6] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();
        return json;
    }

    public String changCommenttoJson(String[][] comment) {
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        for (int i = 0; i < comment.length; i++) {
            String[] passage = comment[i];
            if (!first) {
                sb.append(",");
            }
            sb.append("{");
            sb.append("content: '" + passage[0] + "', ");
            sb.append("time: '" + passage[1] + "', ");
            sb.append("thumbsupNum: '" + passage[2] + "', ");
            sb.append("userName: '" + passage[3] + "', ");
            sb.append("avator: '" + passage[4] + "', ");
            sb.append("school: '" + passage[5] + "', ");
            sb.append("uid: '" + passage[6] + "', ");
            sb.append("}");
            first = false;
        }
        sb.append("]");
        json = sb.toString();
        return json;
    }

    public String pChangtoJson(String[] passage) {
        StringBuffer sb = new StringBuffer();
        String json = new String();
        boolean first = true;
        sb.append("[");
        if (!first) {
            sb.append(",");
        }
        sb.append("{");
        sb.append("title: '" + passage[0] + "', ");
        sb.append("passageContent: '" + passage[1] + "', ");
        sb.append("time: '" + passage[2] + "', ");
        sb.append("username: '" + passage[3] + "', ");
        sb.append("school: '" + passage[4] + "', ");
        sb.append("grade: '" + passage[5] + "', ");
        sb.append("professtion: '" + passage[6] + "', ");
        sb.append("avatar: '" + passage[7] + "', ");
        sb.append("hasFavorite: '" + passage[8] + "', ");
        sb.append("uid: '" + passage[9] + "', ");
        sb.append("}");
        first = false;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //分页查询，根据查询得到的结果集将文章对应的用户的头像（现阶段直接设为null）、学校、专业、年级，文章的内容填到二维字符串数组中
    public String[][] pageQuery(int search, int pageNo, String serchCon) {
        String[][] data = new String[10][7];
        String sql;
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String>s= new ArrayList<String>();
        String[] split=serchCon.split(" ");
        for(int i=0;i<split.length;i++)
        {
            split[i]=split[i].replace(" ","");
            if(split[i].equals("")||split[i].equals(" "))continue;
            else s.add(split[i]);
        }
        List<String> searchWords=new ArrayList<>();
        for(int i=0;i<s.size();i++)
        {
            searchWords.addAll(getWordsFromInput(s.get(i)));
        }

        try {
            if(s.size()==0){ sql = "select * from Passage"; }
            else {
                sql = "select * from Passage where title like '%" + searchWords.get(0) + "%'";

                for(int i = 1;i<searchWords.size();i++)
                {
                    sql=sql+" and title like '%" + searchWords.get(i) + "%'";
                }

            }
            if(search==0) sql=sql+" order by time desc ";
            else sql=sql+" order by floor desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            for (int i = 0; i < (pageNo - 1) * 10; i++) {
                rs.next();
            }
            for (int i = 0; i < 10; i++) {
                int uid = rs.getInt(5);
                User u = getUserById(uid,conn);
                data[i][0] = u.getAvatar();
                data[i][1] = u.getUserName();
                data[i][2] = u.getSchool();
                data[i][3] = u.getProfesstion();
                data[i][4] = rs.getString(2);
                data[i][5] = String.valueOf(rs.getInt(1));
                data[i][6] = String.valueOf(rs.getInt(5));
                if (rs.next()) continue;
                else break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn,pstmt,rs);
        return data;
    }

    public List<String> getWordsFromInput(String str) {
        List<String> words=new ArrayList<String>();
        Result result = ToAnalysis.parse(str);
        List<Term> terms = result.getTerms();
        int n=terms.size();
        for(int j=0;j<n;j++) {
            words.add(terms.get(j).getName());}
        return words;
    }
    //根据时间排列Passage
    public Passage[] sortByDate () {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        Passage []passages=null;
        List<Passage> passageList=new ArrayList<>();
        String sql="select pid from Passage";
        try{
            conn=getConn();
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()) passageList.add(getPassageById(rs.getInt(1),conn));
            int row=passageList.size();
            passages=new Passage[row];
            for (int i = 0; i <passageList.size() ; i++) {
                passages[i]=passageList.get(i);
            }
            for (int i = 0; i < passages.length-1; i++) {
                for (int j = 0; j <passages.length-i-1 ; j++) {
                    if(passages[j].getProduceTime().before(passages[j+1].getProduceTime())){
                        Passage p =passages[j];
                        passages[j]=passages[j+1];
                        passages[j+1]=p;
                    }
                }
            }
        }catch (SQLException e) {e.printStackTrace();}
        close(conn,stmt,rs);
        return passages;
    }
    //通过pid得到评论的内容，日期，被赞数，评论者的名字，头像，大学
    public String [][] commentsofPassage(int pid){
        String [][]com=null;
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        String sql="select * from Comment where pid = "+String.valueOf(pid);
        try{
            conn=getConn();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            rs.last();
            int row = rs.getRow();
            rs=st.executeQuery(sql);
            com=new String[row][7];
            int i=0;
            while (rs.next())
            {
                com[i][0]=rs.getString(2);
                com[i][1]=String.valueOf(rs.getTimestamp(3));
                com[i][2]=String.valueOf(rs.getInt(4));
                com[i][3]=getUserById(rs.getInt(7),conn).getUserName();
                com[i][4]=getUserById(rs.getInt(7),conn).getAvatar()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ;
                com[i][5]=getUserById(rs.getInt(7),conn).getSchool();
                com[i][6]=String.valueOf(rs.getInt(7));
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,rs);
        return com;
    }
    //个人界面修改个人信息
    public boolean changeUserInfo(int uid,String sex,String grade,String profession,String school){

        String sql="update User ";
        int changeNum=0;
        if(!sex.equals("----")){sql=sql+"set sex='"+sex+"'";changeNum++;}
        if(!grade.equals("----")){
            if(changeNum==0)sql=sql+"set grade='"+grade+"'";
            else sql=sql+" , grade='"+grade+"'";
            changeNum++;
        }
        if(!profession.equals("----")){
            if(changeNum==0)sql=sql+"set profession='"+profession+"'";
            else sql=sql+", profession='"+profession+"'";
            changeNum++;
        }
        if(!school.equals("----")){
            if(changeNum==0)sql=sql+"set school='"+school+"'";
            else sql=sql+", school='"+school+"'";
            changeNum++;
        }
        if(changeNum==0)return false;
        else sql=sql+" where uid="+String.valueOf(uid);
        int result=-1;
        Connection conn=null;
        Statement st=null;
        try{
            conn=getConn();
            st=conn.createStatement();
            result=st.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,null);
        if(result==1)return true;
        else return false;
    }

    public String [][]PassagesOfUser(int uid){
        String [][]passages=null;
        String sql="select title,pid,time from Passage where uid="+String.valueOf(uid);
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            conn=getConn();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            int i=0;
            rs.last();
            passages=new String[rs.getRow()][5];
            rs=st.executeQuery(sql);
            while (rs.next()){
                passages[i][0]=rs.getString(1);
                passages[i][1]=String.valueOf(rs.getInt(2));
                passages[i][3]=String.valueOf(0);
                passages[i][4]=String.valueOf(rs.getTimestamp(3));
                i++;
            }
            for (int j = 0; j < passages.length; j++) {
                String s="select count(pid) from Comment where pid="+passages[j][1];
                rs=st.executeQuery(s);
                rs.next();
                passages[j][2]=String.valueOf(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,rs);
        return passages;
    }

    public String[][]CommentsOfUser(int uid) {
        String comments[][] = null;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select ccontent,time,thumbsupNum,pid from Comment where commentOwner = " + String.valueOf(uid);
        try {
            conn=getConn();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            int i=0;
            rs.last();
            comments=new String[rs.getRow()][5];
            rs=st.executeQuery(sql);
            while (rs.next()){
                comments[i][0]=rs.getString(1);
                comments[i][1]=String.valueOf(rs.getTimestamp(2));
                comments[i][2]=String.valueOf(rs.getInt(3));
                comments[i][4]=String.valueOf(rs.getInt(4));
                i++;
            }
            for (int j = 0; j < comments.length; j++) {
                String s="select title from Passage where pid="+comments[j][4];
                rs=st.executeQuery(s);
                rs.next();
                comments[j][3]=rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,rs);
        return comments;
    }


    public String[][] getFavoritePassageById(int uid){
        String [][]passages=null;
        String sql="select * from UserFavoritePassage where userId="+String.valueOf(uid);
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            conn=getConn();
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            rs.last();
            int row=rs.getRow();
            passages=new String[row][2];
            rs=st.executeQuery(sql);
            rs.next();
            for (int i = 0; i < row; i++) {
                passages[i][0]=String.valueOf(rs.getInt(2));
                passages[i][1]=getPassageById(rs.getInt(2),conn).getTitle();
                rs.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return passages;
    }

    //收藏文章功能实现
    public boolean everFavoritePassage(int uid,int pid) {
        String sql = "select * from UserFavoritePassage where userId=" + String.valueOf(uid) + " and passageId= " + String.valueOf(pid);
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        boolean result=false;
        try {
            conn = getConn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) result=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn,st,rs);
        return result;
    }
    public boolean userFavoritePassage(int uid,int pid) {
        String sql = "insert into UserFavoritePassage values(" + String.valueOf(uid) + "," + String.valueOf(pid) + ")";
        Connection conn = null;
        Statement st = null;
        int result = 0;
        try {
            conn = getConn();
            st = conn.createStatement();
            result = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close(conn, st, null);
        if (result == 1) return true;
        else return false;
    }
    public boolean userCancelFavoritePassage(int uid,int pid){
        String sql="delete from UserFavoritePassage where userId=" +String.valueOf(uid)+" and passageId ="+String.valueOf(pid);
        Connection conn = null;
        Statement st = null;
        int result=0;
        try{
            conn=getConn();
            st=conn.createStatement();
            result=st.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        close(conn,st,null);
        if(result==1)return true;
        else return false;
    }
/*    //后台读取的blob转为base64String直接传到前端使用
    public String convertBlobToBase64String(Blob blob) {
        String result = "";
        if(null != blob) {
            try {
                InputStream msgContent = blob.getBinaryStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[100];
                int n = 0;
                while (-1 != (n = msgContent.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                result =new BASE64Encoder().encode(output.toByteArray()) ;
                output.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }else{
            return null;
        }
    }
    //把base64转为blob（用户用户上传头像时候，前端把base64转到后台的转化，以方便上传到数据库）
    public Blob getStringImage(String base64String) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(base64String);
        //上面和下面是两种获得bytes的方法
*//*        String bString = base64String.replaceFirst("data/image/png/base64/","data:image/png;base64,");
        byte[] b = bString.getBytes();*//*

        Blob blob = Hibernate.createBlob(bytes);
        return blob;
    }*/


}
