package viewholder;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.lzq.near.R;

/**
 * Created by LYF on 2016/10/22.
 */
public class SignMallHolder {

    RelativeLayout rlSign;
    RelativeLayout rlMall;

    public SignMallHolder(View convertView) {
        if(convertView!=null){
            rlSign=(RelativeLayout)convertView.findViewById(R.id.rl_home_sign);
            rlMall=(RelativeLayout)convertView.findViewById(R.id.rl_home_mall);
        }

    }
}
