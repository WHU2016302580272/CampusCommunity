import java.util.Date;

public class Comment {
    private int cid;
    private String commentContent;
    private Date produceTime;
    private User owner;
    private Passage passage;
    private int thumbsUpTimes;
    private int floor;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Passage getPassage() { return passage; }

    public void setPassage(Passage passage) { this.passage = passage; }

    public int getThumbsUpTimes(){return thumbsUpTimes; }

    public void setThumbsUpTimes(int thumbsUpTimes){this.thumbsUpTimes = thumbsUpTimes; }

}
