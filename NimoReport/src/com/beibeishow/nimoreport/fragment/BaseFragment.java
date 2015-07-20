
package com.beibeishow.nimoreport.fragment;

/*import com.nimo.beibeishow.utils.CommonUtil;
import com.nimo.beibeishow.utils.Constant;*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ImageView;

public class BaseFragment extends Fragment {
    //private LoginReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // receiver = new LoginReceiver();
/*        getActivity().registerReceiver(receiver, new IntentFilter(Constant.Broadcast.ON_LOGIN_SUCCESS));
        getActivity().registerReceiver(receiver, new IntentFilter(Constant.Broadcast.ON_LOGIN_OUT));*/
    }
    
    @Override
    public void onDestroy() {
        //getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }
    
    public void updateView() {

    }

    protected void unbindDrawables(View view) {
       // CommonUtil.unbindDrawables(view);
    }
    
    protected void onReceiveLoginSuccess(){
        
    }
    
    protected void onReceiveLoginOut(){
        
    }
    /**
     *登陆状态
     */
   /* private class LoginReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == Constant.Broadcast.ON_LOGIN_SUCCESS) {                
                onReceiveLoginSuccess();
            } else if (intent.getAction() == Constant.Broadcast.ON_LOGIN_OUT) {
                onReceiveLoginOut();
            }
        }
    }*/
}
