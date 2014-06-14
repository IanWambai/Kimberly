package com.kimberly;

import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class Forum extends SherlockListActivity {

	ArrayList<Message> messages;
	CreationContentAdapter adapter;
	EditText text;
	static Random rand = new Random();
	static String sender;
	Intent i;
	String id, title, content;
	String totalContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_editor_layout);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setUp();
		populateChatView();
	}

	private void populateChatView() {
		// TODO Auto-generated method stub
		sender = Utility.sender[rand.nextInt(Utility.sender.length - 1)];
		this.setTitle(title);
		messages = new ArrayList<Message>();

		messages.add(new Message(content, false));

		adapter = new CreationContentAdapter(this, messages);
		setListAdapter(adapter);
	}

	private void setUp() {
		// TODO Auto-generated method stub
		text = (EditText) this.findViewById(R.id.text);
	}

	public void sendMessage(View v) {
		String newMessage = text.getText().toString().trim();
		if (newMessage.length() > 0) {
			totalContent += text;
			addNewMessage(new Message(newMessage, true));
			new SendMessage().execute();
		}
	}

	private class SendMessage extends AsyncTask<Void, String, String> {
		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(2000); // simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.publishProgress(String.format("%s is typing...", sender));
			try {
				Thread.sleep(2000); // simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.publishProgress(String
					.format("%s has entered text...", sender));
			try {
				Thread.sleep(3000);// simulate a network call
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String received = Utility.messages[rand
					.nextInt(Utility.messages.length - 1)];
			totalContent += received;
			return received;

		}

		@Override
		public void onProgressUpdate(String... v) {

			if (messages.get(messages.size() - 1).isStatusMessage)
			// check wether we have already added a status message
			{
				messages.get(messages.size() - 1).setMessage(v[0]);
				// update the status for that
				adapter.notifyDataSetChanged();
				getListView().setSelection(messages.size() - 1);
			} else {
				addNewMessage(new Message(true, v[0]));
				// add new message, if there is no existing status message
			}
		}

		@Override
		protected void onPostExecute(String text) {

			// Upload totalContent
			if (messages.get(messages.size() - 1).isStatusMessage)
			// check if there is any status message, now removeit.
			{
				messages.remove(messages.size() - 1);
			}
			addNewMessage(new Message(text, false));
			// add the orignal message from server.
		}

	}

	void addNewMessage(Message m) {
		messages.add(m);
		adapter.notifyDataSetChanged();
		getListView().setSelection(messages.size() - 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		SubMenu subMenu = menu.addSubMenu("Options");
		subMenu.add(0, 576585, 0, "Save");
		subMenu.add(1, 334345, 1, "Info");

		MenuItem subMenuItem = subMenu.getItem();
		subMenuItem.setIcon(android.R.drawable.ic_menu_more);
		subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case 576585:
			// Save to SharedPreferences of something
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast,
					(ViewGroup) findViewById(R.id.toast_layout_root));
			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("Saved");
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(layout);
			toast.show();
			break;
		case 334345:
			i = new Intent(getApplicationContext(), ForumInformation.class);
			startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}