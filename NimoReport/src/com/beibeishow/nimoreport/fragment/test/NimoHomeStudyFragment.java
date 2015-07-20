package com.beibeishow.nimoreport.fragment.test;


import com.beibeishow.nimoreport.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NimoHomeStudyFragment extends Fragment {
	private String tag = "ContactsFragment";
	//构造方法
	public NimoHomeStudyFragment(){
		Log.i(tag, "构造方法:ContactsFragment()");
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.i(tag, "onAttach");
		super.onAttach(activity);
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(tag, "onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(tag, "onCreateView");
		return inflater.inflate(R.layout.fragment_nimo_home_study, null);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i(tag, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.i(tag, "onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i(tag, "onResume");
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i(tag, "onPause");
		super.onPause();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.i(tag, "onStop");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.i(tag, "onDestroyView");
		super.onDestroyView();
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(tag, "onDestroy");
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.i(tag, "onDetach");
		super.onDetach();
	}
	
}
