import java.awt.*;
//y用户的id，名字，密码，学校，年级，专业，性别，提出的文章集合，收藏的文章集合，头像
public class User {
    private int uid;
    private String userName;
    private String password;
    private String school;
    private String grade;
    private String profession;
    private String sex;
    private Passage[] publishPassages;
    private Passage[] collectPassages;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfesstion() {
        return profession;
    }

    public void setProfesstion(String profession) {
        this.profession = profession;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Passage[] getPublishPassages() {
        return publishPassages;
    }

    public void setPublishPassages(Passage[] publishPassages) {
        this.publishPassages = publishPassages;
    }

    public Passage[] getCollectPassages() {
        return collectPassages;
    }

    public void setCollectPassages(Passage[] collectPassages) {
        this.collectPassages = collectPassages;
    }
//设置年级，专业，学校，性别的默认值
    public User setDefault(){
        this.grade = "2016";
        this.profession = "abc";
        this.school = "bcd";
        this.sex = "m";
        return this;
    }
}
