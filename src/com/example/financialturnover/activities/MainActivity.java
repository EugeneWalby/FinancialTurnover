package com.example.financialturnover.activities;

import com.example.financialturnover.R;
import com.example.financialturnover.entities.Balance;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends CommonActivity {
	private TextView balanceView;
	private EditText expenseView;
	private Dialog dialogWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	protected void initialize() {
		super.initialize();
		balanceView = (TextView) findViewById(R.id.textViewCurrentBalance);
		expenseView = (EditText) findViewById(R.id.editTextRecentExpenses);
		createBalance();
	}
	
	private void createBalance() {
		if (db.getBalance() == null) {
			db.addBalance(new Balance(0));
			Log.d("Check: ", "null");
		} else {
			Log.d("Check: ", "not null");
		}

		balanceView.setText(db.getBalance().toString());
	}
	
	public void onClickSaveExpenses(View v) {
		try {
			double currentBalance = getCurrentBalance();
			double expenseValue = getExpenseValue();
			if (expenseValue > currentBalance) {
				Log.d("Error: ", "Negative result value");
				createDialog();
			} else {
				updateBalance(currentBalance, expenseValue);
			}
		} catch(NumberFormatException ex) {
			Log.d("Exception: ", "NumberFormatException - String to double");
		} finally {
			clearView();
		}
	}

	public void onClickRenewBalance(View v) {
		Intent intent = new Intent(MainActivity.this, 
									RenewBalanceActivity.class);
		startActivity(intent);
	}
	
	public void onClickOk(View v) {
		dialogWindow.dismiss();
	}
	
	private void createDialog() {
		dialogWindow = new Dialog(this);
		dialogWindow.setContentView(R.layout.dialog_negative_result);
		dialogWindow.setTitle("Error message");
		createDialogButton();
		dialogWindow.show();
	}

	private void createDialogButton() {
		Button dialogButton = (Button) dialogWindow.findViewById(R.id.buttonOk);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogWindow.dismiss();
			}
		});
	}

	private void updateBalance(double currentBalance, double expenseValue) {
		double newBalance = currentBalance - expenseValue;
		db.updateBalance(new Balance(newBalance));
		balanceView.setText(String.valueOf(newBalance));
	}
	
	private double getCurrentBalance() {
		return Double.parseDouble(db.getBalance().toString());
	}
	
	private Double getExpenseValue() {
		return Double.valueOf(expenseView.getText().toString());
	}
	
	private void clearView() {
		expenseView.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@Override
	protected void onRestart() {
		super.onRestart();
		finish();
		startActivity(getIntent());
	}
}
