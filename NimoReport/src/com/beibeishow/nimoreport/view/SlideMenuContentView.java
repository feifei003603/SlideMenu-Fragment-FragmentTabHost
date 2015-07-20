package com.beibeishow.nimoreport.view;

import com.beibeishow.nimoreport.R;
import com.beibeishow.nimoreport.fragment.test.HomeFragment;
import com.beibeishow.nimoreport.fragment.test.NimoBabyFragment;
import com.beibeishow.nimoreport.fragment.test.NimoHomeStudyFragment;
import com.beibeishow.nimoreport.fragment.test.NimoHomeTemaiFragment;
import com.beibeishow.nimoreport.fragment.test.NimoVoiceFragment;
import com.beibeishow.nimoreport.slidemenu.SlideMenu.OnOpenOrCloseListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * 主页中右侧内容页
 *
 */
public class SlideMenuContentView extends RelativeLayout implements
		OnTabChangeListener {

	private FragmentActivity mActivity;

	// 定义FragmentTabHost对象
	public FragmentTabHost mTabHost;

	// 定义布局
	private LayoutInflater layoutInflater;

	private OnOpenOrCloseListener mOnOpenListener;

	// 定义数组来存放Fragment界面
	private Class fragmentArray[] = { HomeFragment.class,
			NimoBabyFragment.class, NimoVoiceFragment.class,
			NimoHomeStudyFragment.class, NimoHomeTemaiFragment.class };

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.tab_home_selector,
			R.drawable.tab_baby_show_selector, R.drawable.tab_baby_selector,
			R.drawable.tab_study_selector, R.drawable.tab_mall_selector };

	// Tab选项卡的文字
	private String mTextviewArray[] = null;

	private ContentViewListener contentViewListener;

	public SlideMenuContentView(FragmentActivity activity) {
		super(activity);
		mActivity = activity;
		layoutInflater = LayoutInflater.from(mActivity);
		layoutInflater
				.inflate(R.layout.nimo_view_slidemenu_content, this, true);
		mTextviewArray = activity.getResources().getStringArray(
				R.array.nimo_home_tab_name);
		initView();
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		mTabHost = (FragmentTabHost) findViewById(R.id.nimo_slidemenu_tabhost);
		mTabHost.setOnTabChangedListener(this);
		mTabHost.setup(mActivity, mActivity.getSupportFragmentManager(),
				R.id.nimo_slidemenu_tabcontent);

		// 得到fragment的个数
		int count = fragmentArray.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}
		mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
	}

	/**
	 * Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.nimo_home_tabhost_item,
				null);

		ImageView imageView = (ImageView) view
				.findViewById(R.id.nimo_home_tabhost_item_imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view
				.findViewById(R.id.nimo_home_tabhost_item_textview);
		textView.setText(mTextviewArray[index]);
		return view;
	}

	/**
	 * 获取HomeFragment实例
	 */
	public HomeFragment getHomeFragment() {
		HomeFragment homeFragment = (HomeFragment) mActivity
				.getSupportFragmentManager().findFragmentByTag(
						mTextviewArray[0]);
		if (homeFragment == null) {
			return null;
		}
		// homeFragment.setHomeListener(this);
		return homeFragment;
	}

	/**
	 * 设置homefragment监听
	 * 
	 */
	public void setOnOpenOrCloseListener(OnOpenOrCloseListener onOpenListener) {
		mOnOpenListener = onOpenListener;
		if (getHomeFragment() != null) {
			getHomeFragment().setOnOpenOrCloseListener(onOpenListener);
		}
	}

	public void setCurrentTab(int position) {
		mTabHost.setCurrentTab(position);
	}

	public int getCurrentTab() {
		return mTabHost.getCurrentTab();
	}

	@Override
	public void onTabChanged(String arg0) {

	}

	public void onFocusChanged(boolean hasFocus) {
		if (getHomeFragment() != null) {
		}
	}

	public void setOnContentViewListener(ContentViewListener contentViewListener) {
		this.contentViewListener = contentViewListener;
	}

	public static interface ContentViewListener {
		public void onSyncSuccess();
	}

}
