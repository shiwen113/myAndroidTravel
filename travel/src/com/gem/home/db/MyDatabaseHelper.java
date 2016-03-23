package com.gem.home.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private Context mContext;

	// 约游表
	public static final String CREATE_TRAVEL = "create table Travel(" + "td integer primary key autoincrement,"
			+ "teamName text," + "teamZt text," + "allDay integer," + "startPoint text," + "destination text,"
			+ "sex text," + "teamNumber integer," + "age integer," + "city text," + "startTime text,"
			+ "arriveTime text," + "picture text," + "view text," + "introduce text)";

	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TRAVEL);
	
		Log.i("xxy", "db+oncreate+"+CREATE_TRAVEL);
		Toast.makeText(mContext, "Create success", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists Travel");

		onCreate(db);
	}
}
