package com.kimberly;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Window;

public class UserRegistration extends SherlockActivity {

	EditText etPhoneNumber, etUserName;
	String phoneNumber, userName;
	Button bRegisterUser;
	boolean valid = false;
	boolean haveConnectedWifi = false;
	boolean haveConnectedMobile = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.user_registration);
		setSupportProgressBarIndeterminateVisibility(false);

		setUp();
	}

	private void getData() {
		// TODO Auto-generated method stub
		phoneNumber = etPhoneNumber.getText().toString().trim();
		if (phoneNumber.length() < 5) {
			etPhoneNumber.setError("Please enter a valid number");
			valid = false;
		} else {
			etPhoneNumber.setError(null);
			valid = true;
		}
		userName = etUserName.getText().toString().trim();
		if (userName.length() < 3) {
			etUserName
					.setError("Please enter a username with more than 3 characters");
			valid = false;
		} else {
			etPhoneNumber.setError(null);
			valid = true;
		}
	}

	private boolean checkNetworkConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	private void setUp() {
		// TODO Auto-generated method stub
		etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		etUserName = (EditText) findViewById(R.id.etUserName);
		bRegisterUser = (Button) findViewById(R.id.bRegisterUser);
		bRegisterUser.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData();
				if (valid == true) {
					checkNetworkConnection();
					if (haveConnectedMobile == true
							|| haveConnectedWifi == true) {
						if (valid == true) {
							new AlertDialog.Builder(UserRegistration.this)
									.setTitle("Confirmation")
									.setIcon(android.R.drawable.ic_dialog_alert)
									.setMessage(
											"Kimberly will now send a confirmation text message which may be charged by your service provider")
									.setPositiveButton(
											"Got it",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int which) {
													registerUser();
												}
											}).show();

						}
					} else {
						new AlertDialog.Builder(UserRegistration.this)
								.setTitle("No Internet")
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setMessage(
										"You'll need an internet connection to register")
								.setPositiveButton("Alright",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {
												dialog.dismiss();
											}
										}).show();
					}
				}
			}
		});
	}

	private void registerUser() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toast_layout_root));
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Registering....");
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();

		
		SharedPreferences sp = getSharedPreferences(getPackageName(),
				MODE_PRIVATE);
		sp.edit().putBoolean("registered", true).commit();
		Intent i = new Intent(getApplicationContext(), SetProfilePicture.class);
		startActivity(i);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
