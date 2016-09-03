package app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DPro on 1/18/16.
 */
public class AppConfig {

    private static AppConfig appConfig;

    private Context mContext;


    public static AppConfig getAppConfig(Context context){
        if (appConfig ==  null){
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }

        return appConfig;
    }

    public SharedPreferences getSp(){
        return mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(){
        return getSp().edit();
    }

    public SharedPreferences getMainPageSp(){
        return mContext.getSharedPreferences("mainPage", Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getMainPageEditor(){
        return getMainPageSp().edit();
    }
}
