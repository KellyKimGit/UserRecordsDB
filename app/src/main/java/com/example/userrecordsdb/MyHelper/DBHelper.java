package com.example.userrecordsdb.MyHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // 테이블 이름 및 컬럼 이름 상수
    public static final String TABLE_NAME = "user_movie_records";
    public static final String COLUMN_TITLE = "title";

    // 데이터베이스 이름 및 버전 상수
    private static final String DATABASE_NAME = "user_movie_records.db";
    private static final int DATABASE_VERSION = 1;

    // 테이블 생성 쿼리문
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_TITLE + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 이전 테이블 삭제하고 새로운 테이블 생성
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 새 영화 제목을 데이터베이스에 추가
    public long insertMovieTitle(String movieTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movieTitle);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    // 사용자의 시청 목록을 데이터베이스에서 가져옴
    public List<String> getWatchlist() {
        List<String> watchlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_TITLE}, null, null, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(COLUMN_TITLE);
            while (cursor.moveToNext()) {
                String movieTitle = cursor.getString(columnIndex);
                watchlist.add(movieTitle);
            }
            cursor.close();
        }
        db.close();
        return watchlist;
    }


}
