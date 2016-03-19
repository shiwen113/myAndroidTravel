package com.gem.home.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private Context mContext;
	// 约游表
	public static final String CREATE_TRAVEL = "create table travel(" + "td integer primary key autoincrement,"
			+ "teamName text," + "allday integer," + "statPoint text," + "destination text," + "sex integer,"
			+ "city text," + "startTime text," + "arriveTime text," + " viewPoint text," + "intro text)";

	public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version
		) {
		super(context, name, factory, version);
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TRAVEL);
		Toast.makeText(mContext, "create table", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
db.execSQL("drop table if exists travel");
		onCreate(db);
	}
}
