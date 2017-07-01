package com.example.financialturnover.activities;

import com.example.financialturnover.R;
import com.example.financialturnover.entities.Balance;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RenewBalanceActivity extends CommonActivity {
	private EditText newBalance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_renew_balance);
		initialize();
	}
	
	protected void initialize() {
		super.initialize();
		newBalance = (EditText) findViewById(R.id.editTextNewBalance);
	}
	
	public void onClickApplyBalance(View v) {
		try {
			double newValue = getNewValue();
			Log.d("New value:", String.valueOf(newValue));
			db.updateBalance(new Balance(newValue));
		} catch(NumberFormatException ex) {
			Log.d("Exception: ", "NumberFormatException: String to double");
		} finally {
			clearView();
		}
	}

	private Double getNewValue() {
		return Double.valueOf(newBalance.getText().toString());
	}
	
	private void clearView() {
		newBalance.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.renew_balance, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
