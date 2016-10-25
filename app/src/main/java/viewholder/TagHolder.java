package viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.lzq.near.R;

import bean.HomeItem;

/**
 * Created by LYF on 2016/10/22.
 */
public class TagHolder {

    public  TextView tvType;

    public TagHolder(View convertView) {
        if(convertView!=null){
            tvType=(TextView)convertView.findViewById(R.id.tv_tag_txt);
        }
    }

    public void refreshUI(HomeItem homeItem){
        tvType.setText(homeItem.getTagName());
    }
}
