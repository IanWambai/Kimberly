package com.kimberly;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tjeannin.apprate.AppRate;
import com.viewpagerindicator.TitlePageIndicator;

public class Dashboard extends BaseActivity {

	AlertDialog.Builder builder;
	AppRate rate;
	Intent i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setAdapter();
		rateApp();
	}

	private void rateApp() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(Dashboard.this);
		rate = new AppRate(Dashboard.this);
		builder.setTitle("Rate Kimberly!")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMessage("Please rate the app. Tell us what you think.")
				.setPositiveButton("Yes", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent i = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://play.google.com/store/apps/details?=com.kimberly"));
						startActivity(i);
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(120);
					}
				}).setNeutralButton("Later", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(3);
					}
				}).setNegativeButton("No", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						AppRate.reset(Dashboard.this);
						rate.setMinDaysUntilPrompt(10);
					}
				});

		rate.setShowIfAppHasCrashed(false).setMinLaunchesUntilPrompt(20)
				.setCustomDialog(builder).init();
	}

	public void initPagerView(int position, View view) {
		ListView listView;
		switch (position) {
		case 0:
			ArrayList<IconListItem> forumItems = new ArrayList<IconListItem>();
			forumItems.add(new IconListItem("Software development",
					"For programmers", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("Mathematics",
					"Learn and discuss various mathematical concepts ",
					getResources().getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("General Physics",
					"Physics in a fun engaging way", getResources()
							.getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("General Chemistry",
					"A forum to learn some chemistry in a fun engaging way",
					getResources().getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("General Biology",
					"Some biology put awesomely", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("Electronics",
					"Electornics that'll shock you", getResources()
							.getDrawable(R.drawable.kimberly_icon)));
			forumItems
					.add(new IconListItem(
							"Government",
							"Government and participation in public policy, engage with experts",
							getResources()
									.getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("Health",
					"About health and sanitation", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			forumItems
					.add(new IconListItem(
							"Agriculture",
							"About agricultural methods and interact with others in the field",
							getResources()
									.getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("E-Commerce",
					"A forum to learn about E-commerce", getResources()
							.getDrawable(R.drawable.kimberly_icon)));
			forumItems.add(new IconListItem("Social Networking",
					"Meet other peeps", getResources().getDrawable(
							R.drawable.kimberly_icon)));

			IconListItemAdapter forumAdapter = new IconListItemAdapter(
					getApplicationContext(), R.layout.icon_list_item,
					forumItems);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(forumAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					i = new Intent(getApplicationContext(), Forum.class);
					startActivity(i);
				}
			});
			break;
		case 1:
			ArrayList<IconListItem> resourceItems = new ArrayList<IconListItem>();
			resourceItems.add(new IconListItem("Arduino",
					"Opensource hardware", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("Instructables",
					"Make anything", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("Stackoverflow",
					"Debug pretty much anytthing", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("Akira chix",
					"Meet others like you", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("DIY Network",
					"Do it yourself", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("Codecademy",
					"Teach yourself how to code", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("Khan Academy",
					"Learn anything under the sun", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			resourceItems.add(new IconListItem("TED",
					"Be inspired", getResources().getDrawable(
							R.drawable.kimberly_icon)));
			IconListItemAdapter resourceAdapter = new IconListItemAdapter(
					getApplicationContext(), R.layout.icon_list_item,
					resourceItems);
			listView = (ListView) findViewById(R.id.lvListItems);
			listView.setAdapter(resourceAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					new AlertDialog.Builder(Dashboard.this)
							.setTitle("Go online?")
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setMessage("You're about to go online")
							.setPositiveButton("Sure",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											Intent link = new Intent(
													Intent.ACTION_VIEW,
													Uri.parse("https://twitter.com/IanWambai"));
											startActivity(link);
										}
									})
							.setNegativeButton("Nope",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									}).show();
				}
			});
			break;
		}
	}

	private void setAdapter() {
		// TODO Auto-generated method stub
		DashboardFragmentAdapter adapter = new DashboardFragmentAdapter(
				Dashboard.this);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);

		mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}