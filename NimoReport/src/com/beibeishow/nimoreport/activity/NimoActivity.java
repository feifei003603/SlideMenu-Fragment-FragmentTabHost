package com.beibeishow.nimoreport.activity;



import java.util.ArrayList;

import com.beibeishow.nimoreport.R;
import com.beibeishow.nimoreport.fragment.test.HomeFragment;
import com.beibeishow.nimoreport.slidemenu.SlideMenu;
import com.beibeishow.nimoreport.slidemenu.SlideMenu.OnOpenOrCloseListener;
import com.beibeishow.nimoreport.utils.LogUtil;
import com.beibeishow.nimoreport.view.SlideMenuContentView;
import com.beibeishow.nimoreport.view.SlideMenuView;
import com.beibeishow.nimoreport_listener.SlideMenuSelectListener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;

/**
 * @author sushuai
 * 
 * 主界面
 *
 */
public class NimoActivity extends NimoBaseActivity implements OnOpenOrCloseListener,
SlideMenuContentView.ContentViewListener,
SlideMenuSelectListener {

    private static final String TAG = "NimoActivity";

    private SlideMenu slidingMenu;

    // 右侧View宽度
    private int mRightWidth;

    // 左侧View宽度
    private int mLeftWidth = 292;

    private SlideMenuView mSlideMenuView;

    private SlideMenuContentView mSlideMenuContentView;


    private boolean isFirstIn = false;// 是否为第一次进入

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nimo_activity_main);
        initSlideMenu();

    }


    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "whb NimoActivity onResume");
    }

    @Override
    public void onDestroy() {
        if(mSlideMenuView != null){
            mSlideMenuView.release();
            mSlideMenuView = null;
        }
        super.onDestroy();
        LogUtil.d(TAG, "NimoActivity onDestroy");

    }

    /**
     * 初始化SlideMenu
     */
    private void initSlideMenu() {
        mSlideMenuView = new SlideMenuView(this);
        mSlideMenuContentView = new SlideMenuContentView(this);
        mSlideMenuContentView.setOnContentViewListener(this);
        slidingMenu = (SlideMenu) findViewById(R.id.slide_menu_main);
        slidingMenu.setMode(SlideMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlideMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setOnOpenOrCloseListener(this);

        // 设置滑到右边后右边的宽度
        setWebActivityViewWight();
        slidingMenu.setFadeDegree(0.95f);
        slidingMenu.setContent(mSlideMenuContentView);
        slidingMenu.setMenu(mSlideMenuView);
    }

    /**
     * 设置显示menu时右边View宽度
     */
    private void setWebActivityViewWight() {
        if (slidingMenu != null) {
            mRightWidth = getResources().getDisplayMetrics().widthPixels
                    - (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mLeftWidth,
                            getResources().getDisplayMetrics());
            slidingMenu.setBehindOffset(mRightWidth);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mSlideMenuContentView != null) {
                if (mSlideMenuContentView.getCurrentTab() != 0) {
                    mSlideMenuContentView.setCurrentTab(0);
                    return true;
                }
            }

            // 退出删除模式
            HomeFragment homeFragment = mSlideMenuContentView.getHomeFragment();
            //homeFragment.cancelDelMode();

            if (slidingMenu != null && slidingMenu.isMenuShowing()) {
                slidingMenu.toggle();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void slideToLeft(boolean showGuide) {
        slidingMenu.slideToLeft(showGuide);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        LogUtil.d(TAG, "whb onWindowFocusChanged start");
        if (!isFirstIn) {
            isFirstIn = true;
            mSlideMenuContentView.setOnOpenOrCloseListener(this);
        }
        mSlideMenuContentView.onFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
        LogUtil.d(TAG, "whb onWindowFocusChanged end");
    }

    @Override
    public void slideToRight(boolean showGuide) {
        slidingMenu.slideToRight(showGuide);
    }

    @Override
    public boolean isMenuShowing() {
        if (slidingMenu != null) {
            return slidingMenu.isMenuShowing();
        }
        return true;
    }


    @Override
    public void setCurrentTab(int position) {
        mSlideMenuContentView.setCurrentTab(position);
    }

	@Override
	public void onSyncSuccess() {
		// TODO Auto-generated method stub
		
	}
}
