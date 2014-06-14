package com.kimberly;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.tjeannin.apprate.AppRate;

public class ForumInformation extends SherlockActivity {

	ListView listView;
	AlertDialog.Builder builder;
	AppRate rate;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_history);
		getSupportActionBar().setHomeButtonEnabled(true);

		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		ArrayList<ListItem> userFriend = new ArrayList<ListItem>();
		userFriend.add(new ListItem("Cersei Lannister", "Has 345 posts"));
		userFriend.add(new ListItem("Gwen Stacey", "Has 56 posts"));
		userFriend.add(new ListItem("Mary Jane", "Has 89 posts"));

		ListItemAdapter adapter = new ListItemAdapter(getApplicationContext(),
				R.layout.list_item, userFriend);
		listView = (ListView) findViewById(R.id.lvListItems);
		listView.setAdapter(adapter);
	}
}