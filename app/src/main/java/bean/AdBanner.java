package bean;

/**
 * Created by LYF on 2016/10/20.
 */
public class AdBanner {
    private String[] titles;
    private int[] imgIds;

    public String[] getTitle(){
        return titles;
    }

    public void setTitle(String[] titles){
        this.titles=titles;
    }

    public int[] getImgIds(){
        return imgIds;
    }

    public void setImgIds(int[] imgIds){
        this.imgIds=imgIds;
    }
}
