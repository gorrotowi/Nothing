package com.gorro.nothing;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NothingActivity extends Activity {

	// No, you clearly don't know who you're talking to, so let me clue you in.
	// I am not in danger, Skyler.
	// I AM the danger!
	// A guy opens his door and gets shot and you think that of me? No. I am the
	// one who knocks!

	TextView txtNothing;
	int normal = 0, largo = 0;
	int androidVersion;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nothing);

		Log.d("This is a simple console log",
				"well...this is the log whit nothing ;) now go to be happy to another place");
		androidVersion = android.os.Build.VERSION.SDK_INT;
		if (androidVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}

		Typeface tp = Typeface.createFromAsset(getAssets(),
				"fonts/Questrial-Regular.otf");

		txtNothing = (TextView) findViewById(R.id.txtNothing);

		txtNothing.setTypeface(tp);

		txtNothing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				normal = normal + 1;
				Log.e("CLick corto", normal + "");
				if (largo == 4 && normal == 2) {
					Toast.makeText(NothingActivity.this,
							R.string.NothingToastStringOne, Toast.LENGTH_SHORT)
							.show();
					Log.e("primer cheat", "primer cheat");
					largo = 0;
					normal = 0;
				}
			}
		});

		txtNothing.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				largo = largo + 1;
				Log.e("Click largo", largo + "");
				if (largo == 3 && normal == 5) {
					Log.e("segundo cheat", "segundo cheat");
					Toast.makeText(NothingActivity.this,
							R.string.NothingToastStringTwo, Toast.LENGTH_SHORT)
							.show();
					largo = 0;
					normal = 0;
				}

				return false;
			}
		});

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (!hasFocus) {
			Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
			sendBroadcast(closeDialog);
		}
	}
}
