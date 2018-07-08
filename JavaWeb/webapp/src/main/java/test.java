import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class test{
   public static void main(String arg[]){
        Dao d=new Dao();
        User u=new User();
        u.setUserName("小米");
        u.setPassword("123456");
        u.setSchool("whu");
        u.setGrade("大一");
        u.setProfesstion("Computer");
        u.setSex("f");
        if(d.addUser(u))System.out.println("success");
        else System.out.println("failed");
        Connection conn=d.getConn();
        String s="select * from User";
        try {
            PreparedStatement pst = conn.prepareStatement(s);
            ResultSet rs = pst.executeQuery();
            int num=rs.getMetaData().getColumnCount();
            System.out.println(num);
            while(rs.next())
            {
                for(int i = 0;i<num;i++){
                System.out.println(rs.getString(i+1));}
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Passage p=new Passage();
        p.setTitle("这是一个问题");
        p.setPassageContent("啦啦啦啦啦啦啦啦啦啦啦啦啦");
        p.setProduceTime(new Date());
        p.setFloor(1);
        p.setOwner(u);
        d.addPassage(p);



        Comment co=new Comment();
        co.setCommentContent("刘奶奶找牛奶奶买牛奶，牛奶奶给刘奶奶拿牛奶，刘奶奶说牛奶奶的牛奶不如柳奶奶的牛奶，牛奶奶说柳奶奶的牛奶会流奶，柳奶奶听见了大骂牛奶奶你的才会流奶，柳奶奶和牛奶奶泼牛奶吓坏了刘奶奶，大骂再也不买柳奶奶和牛奶奶的牛奶。");
        co.setProduceTime(new Date());
        co.setThumbsUpTimes(3);
        co.setFloor(1);
        co.setPassage(p);
        co.setOwner(u);
        d.addComment(co);
   }


}
