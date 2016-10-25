package bean;

/**
 * Created by LYF on 2016/10/20.
 */
public enum ItemType {
    SIGN_MALL(0),
    TAG(1),
    SPECIAL(2),
    AD(3),
    MENU(4),
    MEAL_SHOW(5),
    TALENT_SHOW(6);

    public int getValue(){
        return value;
    }

    private int value;
    ItemType(int value){
        this.value = value;
    }
}
