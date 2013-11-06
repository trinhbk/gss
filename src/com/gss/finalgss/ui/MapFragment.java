package com.gss.finalgss.ui;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.gss.finalgss.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MapFragment extends FragmentActivity {
	private ArrayList<LatLng> listPosition = new ArrayList<LatLng>();
	private LatLng position;
	private GoogleMap gMap;
	private Button btn_load, btn_save, btn_picture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_map);
		gMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		btn_load = (Button) findViewById(R.id.btn_load);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_picture = (Button) findViewById(R.id.btn_picture);
		gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		gMap.setMyLocationEnabled(true);
		gMap.setIndoorEnabled(false);
		// final Circle circle = gMap.addCircle(new CircleOptions().radius(0));
		gMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
			@Override
			public boolean onMyLocationButtonClick() {
				gMap.moveCamera(CameraUpdateFactory.zoomTo(24));
				// circle.remove();
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MapFragment.this);
				// Add the buttons
				builder.setTitle("Do you want to add point in map ?");
				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								position = new LatLng(gMap.getMyLocation()
										.getLatitude(), gMap.getMyLocation()
										.getLongitude());
								// gMap.addMarker(new MarkerOptions().position(
								// position));
								listPosition.add(position);
								if (listPosition.size() != 1) {
									for (int i = 0; i < listPosition.size() - 1; i++) {
										gMap.addPolyline(new PolylineOptions()
												.add(listPosition.get(i),
														listPosition.get(i + 1))
												.width(2).color(Color.RED));
									}
								}
								// TO DO SAVE IMAGE
							}
						});
				builder.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

							}
						});
				builder.show();
				return false;
			}
		});
		gMap.setOnMapLongClickListener(new OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng point) {
				gMap.clear();
				listPosition.clear();
			}
		});
		btn_load.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});
		btn_picture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("ONCLICK", "SCREEN CAPTURE");
				try {
					CaptureMapScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void CaptureMapScreen() {
		SnapshotReadyCallback callback = new SnapshotReadyCallback() {
			Bitmap bitmap;

			@Override
			public void onSnapshotReady(Bitmap snapshot) {
				bitmap = snapshot;
				try {
					FileOutputStream out = new FileOutputStream("/mnt/sdcard/"
							+ "MyMapScreen" + System.currentTimeMillis()
							+ ".png");

					// above "/mnt ..... png" => is a storage path (where image
					// will be stored) + name of image you can customize as per
					// your Requirement

					bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
					Toast.makeText(getApplicationContext(),
							"Save image succesfull", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		gMap.snapshot(callback);

		// myMap is object of GoogleMap +> GoogleMap myMap;
		// which is initialized in onCreate() =>
		// myMap = ((SupportMapFragment)
		// getSupportFragmentManager().findFragmentById(R.id.map_pass_home_call)).getMap();
	}
}
