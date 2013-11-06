package com.gss.finalgss.ui;

import com.gss.finalgss.R;
import com.gss.finalgss.http.AttemptLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity implements OnClickListener {
	private Button mSubmit, mRegister;
	AttemptLogin asynTask = new AttemptLogin(LoginActivity.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// setup buttons
		mSubmit = (Button) findViewById(R.id.btn_login_login);
		mRegister = (Button) findViewById(R.id.btn_login_register);

		// register listeners
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// determine which button was pressed:
		switch (v.getId()) {
		case R.id.btn_login_login:
			asynTask.execute();
			break;
		case R.id.btn_login_register:
			Intent i = new Intent(this, RegisterActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
	}
}
