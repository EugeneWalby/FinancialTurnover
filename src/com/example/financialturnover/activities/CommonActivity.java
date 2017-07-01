package com.example.financialturnover.activities;

import com.example.financialturnover.handlers.DatabaseHandler;
import android.app.Activity;

public class CommonActivity extends Activity {
	protected DatabaseHandler db;
	
	protected void initialize() {
		db = new DatabaseHandler(this);
	}
	
	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}
}
