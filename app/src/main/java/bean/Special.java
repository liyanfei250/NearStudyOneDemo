package bean;

/**
 * Created by LYF on 2016/10/20.
 *模擬的實體類
 */
public class Special {
    private String imgId;
    private String title;
    private String content;
    private String commentNum;
    private String likeNum;

    public String getImgId(){
        return imgId;
    }

    public void setImgId(String imgId){
        this.imgId=imgId;
    }

    public String getLikeNum(){
        return likeNum;
    }

    public void setLikeNum(String likeNum){
        this.likeNum=likeNum;
    }

    public String getCommentNum(){
        return commentNum;
    }

    public void setCommentNum(String commentNum){
        this.commentNum=commentNum;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }
}
