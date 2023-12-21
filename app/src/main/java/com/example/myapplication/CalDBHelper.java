//package com.example.myapplication;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//public class CalDBHelper extends SQLiteOpenHelper{
//    private static final String DATABASE_NAME = "petDB"; //DB 이름
//    private static final int DATABASE_VERSION = 1;
//
//    // User 테이블 정보
//    public static final String TABLE_USER = "userTable";
//    public static final String COLUMN_USER_ID = "userID";
//
//    // scheduleTBL 테이블 정보
//    public static final String TABLE_SCHEDULE = "scheduleTBL";
//    public static final String COLUMN_SCHEDULE_ID = "schedule_id";
//    public static final String COLUMN_SCHEDULE_USER_ID = "user_id";  // user_id가 user 테이블의 user_id를 참조
//    public static final String COLUMN_SCHEDULE_DATE = "date";
//    public static final String COLUMN_SCHEDULE_DESCRIPTION = "description";
//
//    // User 테이블 생성 SQL 문
//    private static final String CREATE_USER_TABLE =
//            "CREATE TABLE " + TABLE_USER + " (" +
//                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";
//
//    // scheduleTBL 테이블 생성 SQL 문
//    private static final String CREATE_SCHEDULE_TABLE =
//            "CREATE TABLE " + TABLE_SCHEDULE + " (" +
//                    COLUMN_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    COLUMN_SCHEDULE_USER_ID + " INTEGER NOT NULL, " +
//                    COLUMN_SCHEDULE_DATE + " TEXT NOT NULL, " +
//                    COLUMN_SCHEDULE_DESCRIPTION + " TEXT COLLATE UTF16CI, " +
//                    "FOREIGN KEY(" + COLUMN_SCHEDULE_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "));";
//
//    // 생성자
//    public CalDBHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database) {
//        // User 테이블 생성
//        database.execSQL(CREATE_USER_TABLE);
//
//        // scheduleTBL 테이블 생성
//        database.execSQL(CREATE_SCHEDULE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // 업그레이드 로직
//    }
//}
