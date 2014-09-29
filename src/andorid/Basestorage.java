package com.equipnet.basestorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.json.JSONArray;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.equipnet.start.R.string;
import com.equipnet.storage.Storage;


public  class Basestorage extends SQLiteOpenHelper implements Storage{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "udmDB.db";
	public static final String TABLE_PACKAGES = "packages";

	public static final String COLUMN_UDM = "udm";
	public static final String COLUMN_TIMESTAMP = "timestamp";


	public Basestorage (Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PACKAGES_TABLE = "CREATE TABLE " + TABLE_PACKAGES + "("
				+ COLUMN_UDM + " TEXT PRIMARY KEY," + COLUMN_TIMESTAMP + " TIMESTAMP(6)" + ")";
		db.execSQL(CREATE_PACKAGES_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGES);
		onCreate(db);

	}

	@Override
	public void acquire(String acquired) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_UDM, acquired);
		values.put(COLUMN_TIMESTAMP,getDateTime());
		SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PACKAGES, null, values);
        db.close();

	}

	@Override
	public JSONArray read() {
	String query = "Select * FROM " + COLUMN_UDM ;
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(query, null);
	ArrayList<String> risultato = new ArrayList<String>();
	while(cursor.moveToNext()){
		risultato.add(cursor.getString(0));
	}
	JSONArray jsonArray = new JSONArray(risultato);
	return jsonArray;

	}

	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		java.util.Date date= new java.util.Date();
		return dateFormat.format(date);
	}
}
