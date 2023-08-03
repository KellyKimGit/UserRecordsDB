package com.example.userrecordsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.userrecordsdb.MyHelper.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DBHelper 인스턴스 생성 및 사용
        dbHelper = new DBHelper(MainActivity.this);
    }

    // "비긴 어게인" 영화의 "영화 시청 완료" 버튼 클릭 이벤트 처리 메소드
    public void onWatchedMovieClick(View view) {
        updateMovieHistory("비긴 어게인");
    }

    // "어바웃 타임" 영화의 "영화 시청 완료" 버튼 클릭 이벤트 처리 메소드
    public void onAboutTimeClick(View view) {
        updateMovieHistory("어바웃 타임");
    }

    private void updateMovieHistory(String movieTitle) {
        long result = dbHelper.insertMovieTitle(movieTitle);

        // 디버깅용 로그
        Log.d("DatabaseInsert", "Movie title inserted: " + movieTitle);
        Log.d("DatabaseInsert", "Insert result: " + result);


        // 업데이트 성공 여부 확인
        if (result != -1) {
            Toast.makeText(this, "영화 시청 내역 업데이트: " + movieTitle, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "데이터베이스 업데이트 실패.", Toast.LENGTH_SHORT).show();
        }

    }

}
