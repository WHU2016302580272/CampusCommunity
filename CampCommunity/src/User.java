import java.awt.*;

public class User {
    private String userName;
    private String password;
    private String school;
    private String grade;
    private String id;
    private String profession;
    private String sex;
    private Passage[] publishPassages;
    private Passage[] collectPassages;

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    private Image avatar;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
