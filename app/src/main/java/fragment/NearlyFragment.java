package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.lzq.near.R;
import com.example.lzq.near.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeAdapter;
import base.NearBaseFragment;
import bean.Ad;
import bean.AdBanner;
import bean.HomeItem;
import bean.ItemType;
import bean.MealShow;
import bean.MenuPo;
import bean.Special;
import bean.TalentShow;
import viewholder.AdBannerHeader;

/**
 * Created by LYF on 2016/9/2.
 */
public class NearlyFragment extends NearBaseFragment {

    private ListView mListView;
    private RelativeLayout rlSearchBar;
    private HomeAdapter mAdapter;
    private List<HomeItem> homeItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near_layout, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_home_list);
        rlSearchBar = (RelativeLayout) view.findViewById(R.id.rl_home_search_bar);
        //在這裏模擬不同的數據,在item中去識別
        HomeItem signMall=new HomeItem();
        signMall.setItemType(ItemType.SIGN_MALL);
        homeItemList.add(signMall);

        HomeItem tag1=new HomeItem();
        tag1.setItemType(ItemType.TAG);
        tag1.setTagName("专辑推荐");
        homeItemList.add(tag1);

        initSpecialData();

        HomeItem ad1=new HomeItem();
        ad1.setItemType(ItemType.AD);
        Ad ad11=new Ad();
        int[] imgIds1={R.mipmap.ad3,R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad2};
        ad11.setImgIds(imgIds1);
        ad1.setAd(ad11);
        homeItemList.add(ad1);

        HomeItem tag2=new HomeItem();
        tag2.setItemType(ItemType.TAG);
        tag2.setTagName("精选菜谱");
        homeItemList.add(tag2);

        initMenuPoData();

        HomeItem ad2=new HomeItem();
        ad2.setItemType(ItemType.AD);
        Ad ad22=new Ad();
        int[] imgIds2={R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad3,R.mipmap.ad2};
        ad22.setImgIds(imgIds2);
        ad2.setAd(ad22);
        homeItemList.add(ad2);

        initMealShowData();

        HomeItem tag3=new HomeItem();
        tag3.setItemType(ItemType.TAG);
        tag3.setTagName("美食达人");
        homeItemList.add(tag3);

        initTalentShowData();

        HomeItem ad3=new HomeItem();
        ad3.setItemType(ItemType.AD);
        Ad ad33=new Ad();
        int[] imgIds3={R.mipmap.ad4,R.mipmap.ad1,R.mipmap.ad3,R.mipmap.ad2};
        ad33.setImgIds(imgIds3);
        ad3.setAd(ad33);
        homeItemList.add(ad3);

        addAdBannerHeader();
        addSearchView();

        mAdapter=new HomeAdapter(this.getActivity(),homeItemList);
        mListView.setAdapter(mAdapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i){

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    rlSearchBar.setVisibility(View.VISIBLE);
                } else {
                    rlSearchBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initSpecialData(){
        for(int i=0;i<5;i++){
            HomeItem homeItem=new HomeItem();
            Special special=new Special();
            special.setTitle("this is a title!");
            special.setContent("i'm a cute content");
            special.setCommentNum("34");
            special.setLikeNum("999");
            homeItem.setItemType(ItemType.SPECIAL);         //注意不要漏了
            homeItem.setSpecial(special);
            homeItemList.add(homeItem);
        }
    }

    private void initMenuPoData(){
        for(int i=0;i<5;i++){
            HomeItem homeItem=new HomeItem();
            MenuPo menuPo1=new MenuPo();
            menuPo1.setDishName("this is a dish name1");
            menuPo1.setDishIntro("i'm a dish intro1");
            menuPo1.setLikeNum("988");
            menuPo1.setCommentNum("45");

            MenuPo menuPo2=new MenuPo();
            menuPo2.setDishName("this is a dish name2");
            menuPo2.setDishIntro("i'm a dish intro2");
            menuPo2.setLikeNum("988");
            menuPo2.setCommentNum("45");

            MenuPo[] menuPos=new MenuPo[2];//={menuPo1,menuPo2};
            menuPos[0]=menuPo1;
            if(i!=4){
                menuPos[1]=menuPo2;
            }

            homeItem.setItemType(ItemType.MENU);
            homeItem.setMenuPos(menuPos);
            homeItemList.add(homeItem);
        }
    }

    private void initMealShowData(){
        HomeItem mealShowItem=new HomeItem();
        mealShowItem.setItemType(ItemType.MEAL_SHOW);
        int[] imgIds={R.mipmap.ad1,R.mipmap.ad2,R.mipmap.ad3,R.mipmap.ad4};
        String[] titles={"this is title 1111111111111111","this is title 2222222222222222222","this is title 333333333333333333","this is title 44444444444444444"};
        List<MealShow> mealShowList=new ArrayList<MealShow>();
        for(int i=0;i<4;i++){
            MealShow mealShow=new MealShow();
            mealShow.setCommentNum(11+i+"");
            mealShow.setLikeNum(22+i+"");
            mealShow.setUserName("纳兹咩"+i);
            mealShow.setImgId(imgIds[i]);
            mealShow.setTitle(titles[i]);
            mealShowList.add(mealShow);
        }
        mealShowItem.setMealShowList(mealShowList);
        homeItemList.add(mealShowItem);
    }

    private void initTalentShowData(){
        HomeItem talentShowItem=new HomeItem();
        talentShowItem.setItemType(ItemType.TALENT_SHOW);
        List<TalentShow> talentShowList=new ArrayList<>();
        for(int i=0;i<7;i++){
            TalentShow talentShow=new TalentShow();
            talentShow.setNickName("娘口三三"+i);
            talentShowList.add(talentShow);
        }
        talentShowItem.setTalentShowList(talentShowList);
        homeItemList.add(talentShowItem);
    }

    private void addAdBannerHeader(){
        final AdBanner adbanner=new AdBanner();
        int[] imgIds={R.mipmap.ad1,R.mipmap.ad2,R.mipmap.ad3,R.mipmap.ad4};
        String[] titles={"this is a cute ad","you are so beautify","handsome boy","lovey girl"};
        adbanner.setImgIds(imgIds);
        adbanner.setTitle(titles);
        AdBannerHeader adBannerHeader=new AdBannerHeader(this.getActivity(),adbanner);
        mListView.addHeaderView(adBannerHeader.view);
    }

    private void addSearchView(){
        View searchView = View.inflate(this.getActivity(), R.layout.view_home_search, null);
        RelativeLayout seach = (RelativeLayout) searchView.findViewById(R.id.rl_home_search);
        mListView.addHeaderView(searchView);
        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    @Override
    protected void initFragment() {

    }
}
