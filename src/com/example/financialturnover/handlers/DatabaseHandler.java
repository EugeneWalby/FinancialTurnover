package com.example.financialturnover.handlers;

import com.example.financialturnover.entities.Balance;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "financial_turnover";
	private static final String TABLE_BALANCE = "balance";
	private static String KEY_ID = "id";
	private static String KEY_VALUE = "value";
	private int valueID = 1;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS " 
				+ TABLE_BALANCE + "(" + KEY_ID + " INTEGER PRIMARY KEY, " 
				+ KEY_VALUE + " DOUBLE" + ")";
		db.execSQL(createTableQuery);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

	public void addBalance(Balance balance) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_ID, balance.getId());
		values.put(KEY_VALUE, balance.getValue());
		
		db.insert(TABLE_BALANCE, null, values);
		db.close();
	}
	
	public Balance getBalance() {
		Balance balance = null;
		SQLiteDatabase db = this.getWritableDatabase();
		String selectValueQuery =  "SELECT " + KEY_VALUE + " FROM " 
				+ TABLE_BALANCE + " WHERE " + KEY_ID + " = '" 
				+ valueID + "';";
		Cursor cursor = db.rawQuery(selectValueQuery, null);
		
		if (cursor.moveToFirst()) {
			balance = new Balance();
			balance.setValue(Double.parseDouble(cursor.getString(0)));
		}
		
		return balance;
	}
	
	public int updateBalance(Balance balance) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_VALUE, balance.getValue());
		
		return db.update(TABLE_BALANCE, values, KEY_ID + "= ?", 
				new String[] {String.valueOf(balance.getId())});
	}
	
	public void deleteBalance(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_BALANCE, KEY_ID + "= ?", 
				new String[] {String.valueOf(id)});
		db.close();
	}
}
