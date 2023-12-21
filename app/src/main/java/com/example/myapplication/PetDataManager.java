package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PetDataManager {
    private SQLiteDatabase database;
    private CalDBHelper dbHelper;

    public PetDataManager(Context context) {
        dbHelper = new CalDBHelper(context);
    }

    // 데이터베이스 열기
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // 데이터베이스 닫기
    public void close() {
        dbHelper.close();
    }

    public long addSchedule(String user, String date, String schedule) {
        // 사용자가 존재하는지 확인
        // (실제로는 사용자 인증 과정이 필요할 수 있습니다)
        if (isUserExists(user)) {
            ContentValues values = new ContentValues();
            values.put(CalDBHelper.COLUMN_SCHEDULE_USER_ID, user);
            values.put(CalDBHelper.COLUMN_SCHEDULE_DATE, date);
            values.put(CalDBHelper.COLUMN_SCHEDULE_DESCRIPTION, schedule);

            long result = database.insert(CalDBHelper.TABLE_SCHEDULE, null, values);

            if (result == -1) {
                Log.e("PetDataManager", "일정 추가 실패");
            }

            return result;
        } else {
            Log.e("PetDataManager", "사용자가 존재하지 않습니다.");
            return -1; // 사용자가 존재하지 않는 경우 -1 반환
        }
    }

    // 날짜로 일정 조회
    public List<String> getSchedulesByDate(String user, String date) {
        List<String> schedules = new ArrayList<>();

        Cursor cursor = database.query(
                CalDBHelper.TABLE_SCHEDULE,
                new String[]{CalDBHelper.COLUMN_SCHEDULE_DESCRIPTION},
                CalDBHelper.COLUMN_SCHEDULE_USER_ID + "=? AND " + CalDBHelper.COLUMN_SCHEDULE_DATE + "=?",
                new String[]{user, date},
                null, null, null
        );

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                schedules.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return schedules;
    }

    // 전체 일정 조회
    public List<String> getAllSchedules(String user) {
        List<String> schedules = new ArrayList<>();

        Cursor cursor = database.query(
                CalDBHelper.TABLE_SCHEDULE,
                new String[]{CalDBHelper.COLUMN_SCHEDULE_DESCRIPTION},
                CalDBHelper.COLUMN_SCHEDULE_USER_ID + "=?",
                new String[]{user},
                null, null, null
        );

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                schedules.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return schedules;
    }

    public boolean isUserExists(String user) {
        Cursor cursor = database.query(
                CalDBHelper.TABLE_USER,
                new String[]{CalDBHelper.COLUMN_USER_ID},
                CalDBHelper.COLUMN_USER_ID + "=?",
                new String[]{user},
                null, null, null
        );

        int count = cursor.getCount();
        cursor.close();

        // 사용자가 존재하면 true, 존재하지 않으면 false 반환
        return count > 0;
    }
}
