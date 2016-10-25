package viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lzq.near.R;

import java.util.List;

import adapter.TalentShowAdapter;
import app.AbsSuperApplication;
import bean.HomeItem;
import bean.TalentShow;
import util.ToastUtils;

/**
 * Created by LYF on 2016/10/23.
 */
public class TalentShowHolder {
    Context context;
    RecyclerView mRecyclerView;
    TalentShowAdapter mAdapter;

    public TalentShowHolder(Context context,View convertView){
        this.context=context;
        if(convertView!=null){
            mRecyclerView=(RecyclerView)convertView.findViewById(R.id.recycler_view_talent);
        }
    }

    public void initView(HomeItem homeItem){
        List<TalentShow> talentShowList=homeItem.getTalentShowList();
        mAdapter = new TalentShowAdapter(context,talentShowList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new TalentShowAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                ToastUtils.showToast(AbsSuperApplication.getContext(),"i am talent "+position);
            }
        });

    }
}
