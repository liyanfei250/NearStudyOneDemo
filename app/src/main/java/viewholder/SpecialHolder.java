package viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lzq.near.R;

import app.AbsSuperApplication;
import bean.HomeItem;
import bean.Special;
import util.ToastUtils;

/**
 * Created by LYF on 2016/10/22.
 */
public class SpecialHolder {

    RelativeLayout rlItem;
    ImageView ivImg;
    TextView tvTitle;
    TextView tvContent;
    TextView tvComment;
    TextView tvLike;
    public SpecialHolder(View convertView){
        if(convertView!=null){
            rlItem=(RelativeLayout)convertView.findViewById(R.id.rl_special_item);
            ivImg=(ImageView)convertView.findViewById(R.id.iv_special_img);
            tvTitle=(TextView)convertView.findViewById(R.id.tv_special_title);
            tvContent=(TextView)convertView.findViewById(R.id.tv_special_content);
            tvComment=(TextView)convertView.findViewById(R.id.tv_special_comment);
            tvLike=(TextView)convertView.findViewById(R.id.tv_special_like);

            rlItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    ToastUtils.showToast(AbsSuperApplication.getInstance(),"i am special");
                }
            });
        }
    }

    public void refreshUI(HomeItem homeItem){
        Special special=homeItem.getSpecial();
        tvTitle.setText(special.getTitle());
        tvContent.setText(special.getContent());
        tvComment.setText(special.getCommentNum());
        tvLike.setText(special.getLikeNum());
    }
}
