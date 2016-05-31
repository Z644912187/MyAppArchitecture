package com.sjtu.charles.core.cache;

import android.content.Context;

import com.corelib.utils.cache.PreferencesUtils;
import com.sjtu.charles.core.MyApplication;
import com.sjtu.charles.login.bean.User;

/**
 * Created by charles on 2016/3/29.
 *
 * 操作sp中保存的信息
 */
public class GlobalCache {
    private static final String TAG = "GlobalCache";
    // sp 文件名
    private static final String SP_USER = "user";
    private static final String SP_APPINFO = "appInfo";
    // Key
    private static final String KEY_USER = "key_user";
    private static final String KEY_FIRST_ENTER = "key_first_enter_str";

    private static Context context = MyApplication.getInstance();
    private static User mUser = null;

    /**
     * 保存用户信息
     * @param user
     */
    public static void setUser(User user)
    {
        mUser = user;
        PreferencesUtils.pointSpName(SP_USER);
        PreferencesUtils.putObject(context, KEY_USER, user);
    }

    /**
     * 获取缓存的用户信息
     * Tip: 注意缓存为空的场景
     * @return
     */
    public static User getUser()
    {
        if (mUser != null)
        {
            return mUser;
        }

        PreferencesUtils.pointSpName(SP_USER);
        Object o = PreferencesUtils.getObject(context, KEY_USER, User.class);
        if(o != null)
        {
            mUser = (User) o;
        }
        return mUser;
    }

    /**
     * 清除本地登录信息
     */
    public static void clearToken()
    {
        User user = getUser();
        user.setToken("");
        PreferencesUtils.pointSpName(SP_USER);
        PreferencesUtils.putObject(context, KEY_USER, user);
    }

    /**
     * 记录第一次登录标识, 以当前的版本名称为标识
     */
    public static void setFirstEnter()
    {
        PreferencesUtils.pointSpName(SP_APPINFO);
        PreferencesUtils.putString(context, KEY_FIRST_ENTER, AppInfo.getVersionName());
    }

    /**
     * 判断是否第一次使用app
     */
    public static boolean isFirstEnter()
    {
        PreferencesUtils.pointSpName(SP_APPINFO);
        String lastVersionName = PreferencesUtils.getString(context,KEY_FIRST_ENTER, "");
        return !lastVersionName.equals(AppInfo.getVersionName());
    }
}
