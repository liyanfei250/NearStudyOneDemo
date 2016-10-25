package bean;

/**
 * Created by LYF on 2016/10/20.
 * 模拟的方块的item数据
 */
public class MenuPo {

    private String imgId;
    private String dishName;
    private String dishIntro;
    private String likeNum;
    private String commentNum;

    public String getImgId(){
        return imgId;
    }

    public void setImgId(String imgId){
        this.imgId=imgId;
    }

    public String getDishName(){
        return dishName;
    }

    public void setDishName(String dishName){
        this.dishName=dishName;
    }

    public String getDishIntro(){
        return dishIntro;
    }

    public void setDishIntro(String dishIntro){
        this.dishIntro=dishIntro;
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
}
