package bean;

import java.util.List;

/**
 * Created by LYF on 2016/10/19.
 */
public class HomeItem {
    //对于不同的类型进行了不同的类别
    private ItemType itemType;
    private String tagName;
    private Special special;
    private Ad ad;
    private MenuPo[] menuPos;
    private List<MealShow> mealShowList;
    private List<TalentShow> talentShowList;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Special getSpecial() {
        return special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public MenuPo[] getMenuPos() {
        return menuPos;
    }

    public void setMenuPos(MenuPo[] menuPos) {
        this.menuPos = menuPos;
    }

    public List<MealShow> getMealShowList() {
        return mealShowList;
    }

    public void setMealShowList(List<MealShow> mealShowList) {
        this.mealShowList = mealShowList;
    }

    public List<TalentShow> getTalentShowList() {
        return talentShowList;
    }

    public void setTalentShowList(List<TalentShow> talentShowList) {
        this.talentShowList = talentShowList;
    }
}
