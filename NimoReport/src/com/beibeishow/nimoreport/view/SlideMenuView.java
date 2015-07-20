package com.beibeishow.nimoreport.view;

/*import com.nimo.beibeishow.R;
 import com.nimo.beibeishow.activity.NimoRegisterActivity;
 import com.nimo.beibeishow.activity.NimoResetPasswordActivity;
 import com.nimo.beibeishow.activity.NimoSettingActivity;
 import com.nimo.beibeishow.db.NimoPref;
 import com.nimo.beibeishow.db.UserDao;
 import com.nimo.beibeishow.domain.User;
 import com.nimo.beibeishow.fragment.LoginFragment;
 import com.nimo.beibeishow.fragment.OnlineFragment;
 import com.nimo.beibeishow.fragment.RegistFragment;
 import com.nimo.beibeishow.fragment.ResetPasswordFragment;
 import com.nimo.beibeishow.umeng.ConversationActivity;
 import com.nimo.beibeishow.utils.ActivityUtil;
 import com.nimo.beibeishow.utils.ConstantCount;
 import com.nimo.beibeishow.utils.CountUtil;
 import com.nimo.beibeishow.utils.FileUtil;
 import com.nimo.beibeishow.utils.LogUtil;*/

import com.beibeishow.nimoreport.R;
import com.beibeishow.nimoreport.fragment.test.OnlineFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 主页中左侧菜单
 * 
 */
public class SlideMenuView extends RelativeLayout implements OnClickListener {

	private static final String TAG = "SlideMenuView";

	private FragmentActivity mContext;

	private OnlineFragment onlineFragment;

	private RelativeLayout slideBackGroundLayout;

	public SlideMenuView(FragmentActivity context) {
		super(context);
		this.mContext = context;
		View view = LayoutInflater.from(context).inflate(
				R.layout.nimo_view_slidemenu, this, true);
		slideBackGroundLayout = (RelativeLayout) view
				.findViewById(R.id.slide_menu_backgroud);
		initView();
	}

	public SlideMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void initView() {

		createOnlineFragment(true);
	}

	private void createOnlineFragment(boolean needLogin) {
		if (onlineFragment == null) {
			onlineFragment = new OnlineFragment();
		}

		addFragment(onlineFragment);
	}

	private void addFragment(Fragment registFragment) {
		FragmentTransaction transaction = mContext.getSupportFragmentManager()
				.beginTransaction();

		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack so the user can navigate
		// back
		transaction.replace(R.id.slide_menu_fragment, registFragment);
		transaction.addToBackStack(null);
		// Commit the transaction
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	public void release() {
		if (slideBackGroundLayout != null) {
			slideBackGroundLayout.setBackgroundResource(0);
		}
	}
}
