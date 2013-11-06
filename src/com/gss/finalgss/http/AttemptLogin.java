package com.gss.finalgss.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.gss.finalgss.JSONParser;
import com.gss.finalgss.R;
import com.gss.finalgss.ui.MainActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class AttemptLogin extends AsyncTask<String, String, String> {
	Activity mActivity;
	private EditText user, pass;
	// Progress Dialog
	private ProgressDialog pDialog;
	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script location:

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/login.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://localhost:8080/cakephpexam/login";//"http://10.0.2.2:1234/webservice/login.php";

	// testing from a real server:
	// private static final String LOGIN_URL =
	// "http://www.yourdomain.com/webservice/login.php";

	// JSON element ids from repsonse of php script:
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	boolean failure = false;

	public AttemptLogin(Activity activity) {
		super();
		this.mActivity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(mActivity);
		pDialog.setMessage("Attempting login...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		// Check for success tag
		int success;

		// setup input fields
		user = (EditText) mActivity.findViewById(R.id.et_login_username);
		pass = (EditText) mActivity.findViewById(R.id.et_login_password);

		String username = user.getText().toString();
		String password = pass.getText().toString();
		try {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));

			Log.d("request!", "starting");
			// getting product details by making HTTP request
			JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
					params);

			// check your log for json response
			Log.d("Login attempt", json.toString());

			// json success tag
			success = json.getInt(TAG_SUCCESS);
			if (success == 1) {
				Log.d("Login Successful!", json.toString());
				Intent i = new Intent(mActivity, MainActivity.class);
				this.mActivity.finish();
				this.mActivity.startActivity(i);
				return json.getString(TAG_MESSAGE);
			} else {
				Log.d("Login Failure!", json.getString(TAG_MESSAGE));
				return json.getString(TAG_MESSAGE);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	protected void onPostExecute(String file_url) {
		// dismiss the dialog once product deleted
		pDialog.dismiss();
		if (file_url != null) {
			Toast.makeText(mActivity, file_url, Toast.LENGTH_LONG).show();
		}

	}

}