package com.beibeishow.nimoreport.activity;

import com.beibeishow.nimoreport.R;
import com.beibeishow.nimoreport.utils.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

/**
 * @author sushuai 
 * 基类
 *
 */
public class NimoBaseActivity extends FragmentActivity {
	private long exitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			// 点击返回键两次退出程序
			if (this instanceof NimoActivity) {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					ToastUtil.showToast(this, R.string.nimo_exit_tips, 1);
					exitTime = System.currentTimeMillis();
				} else {
					finish();
				}
				return true;
			}

			// 退出Activity时动画
			finish();
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_right_out);
		}
		return super.onKeyDown(keyCode, event);
	}

}
