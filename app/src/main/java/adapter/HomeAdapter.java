package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lzq.near.R;

import java.util.List;

import bean.HomeItem;
import viewholder.AdHolder;
import viewholder.MealShowHolder;
import viewholder.MenuHolder;
import viewholder.SignMallHolder;
import viewholder.SpecialHolder;
import viewholder.TagHolder;
import viewholder.TalentShowHolder;

/**
 * Created by LYF on 2016/10/19.
 */
public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<HomeItem> homeItemList;
    private final static int SIGN_MALL=0;
    private final static int TAG=1;
    private final static int SPECIAL=2;
    private final static int AD=3;
    private final static int MENU=4;
    private final static int MEAL_SHOW=5;
    private final static int TALENT_SHOW=6;

    public HomeAdapter(Context context, List<HomeItem> homeItemList){
        this.context=context;
        this.homeItemList=homeItemList;
    }

    @Override
    public int getCount() {
        return homeItemList.size();//头部4个，广告位3个,总共7个，还有listview滑动时的banner
    }

    @Override
    public Object getItem(int i) {
        return homeItemList == null ? null : homeItemList . get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        HomeItem homeItem = homeItemList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        SignMallHolder signMallHolder;
        TagHolder tagHolder;
        SpecialHolder specialHolder;
        MenuHolder menuHolder;
        AdHolder adHolder;
        MealShowHolder mealShowHolder;
        TalentShowHolder talentShowHolder;
        int type = homeItem.getItemType().getValue();
        switch (type){
            case SIGN_MALL:
                 if(convertView==null){
                     convertView=inflater.inflate(R.layout.view_home_sign_mall,null);
                     signMallHolder = new SignMallHolder(convertView);
                     convertView.setTag(signMallHolder);
                 }else{
                     signMallHolder = (SignMallHolder)convertView.getTag();
                 }
                break;
            case TAG:
                if(convertView==null){
                    convertView = inflater.inflate(R.layout.view_home_tag,null);
                    tagHolder = new TagHolder(convertView);
                    convertView.setTag(tagHolder);
                }else{
                    tagHolder = (TagHolder) convertView.getTag();
                }
                tagHolder.refreshUI(homeItem);
                break;
            case SPECIAL:
                if(convertView==null){
                    convertView = inflater.inflate(R.layout.view_home_special,null);
                    specialHolder = new SpecialHolder(convertView);
                    convertView.setTag(specialHolder);
                }else{
                    specialHolder = (SpecialHolder)convertView.getTag();
                }
                specialHolder.refreshUI(homeItem);
            break;
            case AD:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_ad,null);
                    adHolder=new AdHolder(context,convertView);
                    convertView.setTag(adHolder);
                }else{
                    adHolder=(AdHolder)convertView.getTag();
                }
                adHolder.setViewPager(homeItem);
            break;
            case MENU:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_menu,null);
                    menuHolder=new MenuHolder(convertView);
                    convertView.setTag(menuHolder);
                }else{
                    menuHolder=(MenuHolder)convertView.getTag();
                }
                menuHolder.refreshUI(homeItem);
            break;
            case MEAL_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_meal_show,null);
                    mealShowHolder=new MealShowHolder(context,convertView);
                    convertView.setTag(mealShowHolder);
                }else{
                    mealShowHolder=(MealShowHolder)convertView.getTag();
                }
                mealShowHolder.setViewPager(homeItem);
            break;
            case TALENT_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_talent,null);
                    talentShowHolder=new TalentShowHolder(context,convertView);
                    convertView.setTag(talentShowHolder);
                }else{
                    talentShowHolder=(TalentShowHolder)convertView.getTag();
                }
                talentShowHolder.initView(homeItem);
            break;
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position){
        if (homeItemList!= null && position < homeItemList.size()) {
            return homeItemList.get(position).getItemType().getValue();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount(){
        return 7;
    }
}
