package com.gss.finalgss.ui;

import com.gss.finalgss.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	DrawerLayout mDrawerLayout;
	//ListView mDrawerList;
	ScrollView scrollView;
	ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	String mTitle = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("");
		getActionBar().setDisplayUseLogoEnabled(false);
		ArrayAdapter<String> adapter;
		mTitle = (String) getTitle();
		// final Bundle bundle = getIntent().getExtras();
		// Log.d("STATUS", bundle.getInt("status") + " ");
		// Log.d("ROLE", bundle.getInt("role") + " ");
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//mDrawerList = (ListView) findViewById(R.id.drawer_list);
		scrollView = (ScrollView) findViewById(R.id.scrollView);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		adapter = new ArrayAdapter<String>(getBaseContext(),
				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.menu_staff));
		RelativeLayout rl_manageUser = (RelativeLayout) findViewById(R.id.rl_manageUser);
		rl_manageUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProfileFragment pf_service = new ProfileFragment();

				// Getting reference to the FragmentManager
				FragmentManager pf_fragmentManager = getFragmentManager();

				// Creating a fragment transaction
				FragmentTransaction ft = pf_fragmentManager.beginTransaction();

				// Adding a fragment to the fragment transaction
				ft.replace(R.id.content_frame, pf_service);

				// Committing the transaction
				ft.commit();

				// Closing the drawer
				mDrawerLayout.closeDrawer(scrollView);	
			}
		});
		// Setting the adapter on mDrawerList
		//mDrawerList.setAdapter(adapter);

//		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View view,
//					int position, long id) {
//				switch (position) {
//				case 0:
//					// NEWS
//					break;
//				case 1:
//					// PROFILE
//					Log.d("POSITON", position + ": GRAVE");
//					ProfileFragment pf_service = new ProfileFragment();
//
//					// Getting reference to the FragmentManager
//					FragmentManager pf_fragmentManager = getFragmentManager();
//
//					// Creating a fragment transaction
//					FragmentTransaction ft = pf_fragmentManager
//							.beginTransaction();
//
//					// Adding a fragment to the fragment transaction
//					ft.replace(R.id.content_frame, pf_service);
//
//					// Committing the transaction
//					ft.commit();
//
//					// Closing the drawer
//					mDrawerLayout.closeDrawer(mDrawerList);
//					break;
//				case 2:
//					// GRAVE MANAGEMENT
//					Log.d("POSITON", position + ": GRAVE");
//					GraveFragment gr_service = new GraveFragment();
//
//					// Getting reference to the FragmentManager
//					FragmentManager gr_fragmentManager = getFragmentManager();
//
//					// Creating a fragment transaction
//					ft = gr_fragmentManager.beginTransaction();
//
//					// Adding a fragment to the fragment transaction
//					ft.replace(R.id.content_frame, gr_service);
//
//					// Committing the transaction
//					ft.commit();
//
//					// Closing the drawer
//					mDrawerLayout.closeDrawer(mDrawerList);
//					break;
//				case 3:
//					// REQUEST MANAGEMENT
//					Log.d("POSITON", position + ": REQUEST");
//					RequestFragment rq_service = new RequestFragment();
//
//					// Getting reference to the FragmentManager
//					FragmentManager rq_fragmentManager = getFragmentManager();
//
//					// Creating a fragment transaction
//					ft = rq_fragmentManager.beginTransaction();
//
//					// Adding a fragment to the fragment transaction
//					ft.replace(R.id.content_frame, rq_service);
//
//					// Committing the transaction
//					ft.commit();
//
//					// Closing the drawer
//					mDrawerLayout.closeDrawer(mDrawerList);
//					break;
//				case 4:
//					// SERVICE MANAGEMENT
//					Log.d("POSITON", position + ": SERVICE");
//					ServiceFragment service = new ServiceFragment();
//
//					// Getting reference to the FragmentManager
//					FragmentManager sv_fragmentManager = getFragmentManager();
//
//					// Creating a fragment transaction
//					ft = sv_fragmentManager.beginTransaction();
//
//					// Adding a fragment to the fragment transaction
//					ft.replace(R.id.content_frame, service);
//
//					// Committing the transaction
//					ft.commit();
//
//					// Closing the drawer
//					mDrawerLayout.closeDrawer(mDrawerList);
//					break;
//				case 5:
//					Log.d("POSITON", position + ": MAP");
//					Intent intent = new Intent(getApplicationContext(),
//							MapFragment.class);
//					startActivity(intent);
//					break;
//				default:
//					break;
//				}
//			}
//		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(R.id.ll_test);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
