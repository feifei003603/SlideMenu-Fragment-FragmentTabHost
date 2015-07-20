
package com.beibeishow.nimoreport.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * 
 * @author sushuai
 * 
 */
public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, int stringId, int duration) {
        if (context == null) {
            return;
        }
        String msg = context.getResources().getString(stringId);
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
