public class Comment {
    private String commentContent;
    private String produceTime;
    private User owner;
    private int thumbsUpTimes;
    private int floor;

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

    public String getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getThumbsUpTimes(){return thumbsUpTimes; }

    public void setThumbsUpTimes(int thumbsUpTimes){this.thumbsUpTimes = thumbsUpTimes; }

}
