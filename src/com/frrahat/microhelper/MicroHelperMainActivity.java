package com.frrahat.microhelper;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MicroHelperMainActivity extends Activity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	ArrayList<String> fragmentClassNames;

	private final int settingsRequestCode=101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_micro_helper_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		final ActionBar actionBar = getActionBar();
	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
		// Create a tab listener that is called when the user changes tabs.
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // When the tab is selected, switch to the
	            // corresponding page in the ViewPager.
	            mViewPager.setCurrentItem(tab.getPosition());
	        }

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			}
	    };
	    
	    // Add tabs, specifying the tab's text and TabListener
	    String TabTexts[]={"Bin To HEX","Control Bits","Some Basics"};
		for (int i = 0; i < TabTexts.length; i++) {
			actionBar.addTab(actionBar.newTab().setText(TabTexts[i])
					.setTabListener(tabListener));
		}
		
		mViewPager.setOnPageChangeListener(
	            new ViewPager.SimpleOnPageChangeListener() {
	                @Override
	                public void onPageSelected(int position) {
	                    // When swiping between pages, select the
	                    // corresponding tab.
	                    getActionBar().setSelectedNavigationItem(position);
	                }
	            });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.micro_helper_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			this.startActivityForResult(SettingsActivity.start(this),
					 settingsRequestCode);			
			return true;
		}
		if (id == R.id.action_about) {
			Intent intent=new Intent(MicroHelperMainActivity.this,AboutActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==settingsRequestCode){
			BasicInfosFragment.updateTextSize(getBaseContext());
		}
	}
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);			
			fragmentClassNames=new ArrayList<>();
			fragmentClassNames.add(ConverterFragment.class.getName());
			fragmentClassNames.add(ValueSetterFragment.class.getName());
			fragmentClassNames.add(BasicInfosFragment.class.getName());
		}

		@Override
		public Fragment getItem(int position) {
			//return MyFragment.newInstance();
			return Fragment.instantiate(getBaseContext(), fragmentClassNames.get(position));
		}

		@Override
		public int getCount() {
			return fragmentClassNames.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

}
