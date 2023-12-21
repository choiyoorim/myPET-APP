package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    DBHelper myDBHelper;
    TextView btnLogin;
    EditText userId, userPassword, passwordCheck;
    Button btnJoin;
    SQLiteDatabase sqlDB;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        setTitle("회원가입");

        myDBHelper = new DBHelper(this);
        userId = (EditText) findViewById(R.id.userID);
        userPassword = (EditText) findViewById(R.id.userPassword);
        passwordCheck = (EditText) findViewById(R.id.userPasswordCheck);
        btnJoin = (Button) findViewById(R.id.JoinButton);
        btnLogin = (TextView) findViewById(R.id.LoginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getWritableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM userTable WHERE userID='"+userId.getText().toString()+"';",null);
                cursor.moveToFirst();
                if(cursor.getCount() > 0 && cursor.getString(0).equals(userId.getText().toString())){
                    finish();//인텐트 종료
                    overridePendingTransition(0, 0);
                    Intent intent = getIntent(); //인텐트
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다.",Toast.LENGTH_LONG).show();
                }
                if(userPassword.getText().toString().equals(passwordCheck.getText().toString())){
                    sqlDB.execSQL("INSERT INTO userTable VALUES ( '" + userId.getText().toString() + "', '" + userPassword.getText().toString() + "');");
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"비밀번호가 서로 일치하지 않습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

//    public class myDBHelper extends SQLiteOpenHelper {
//        public myDBHelper(Context context){
//            super(context, "userDb", null, 1);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db){
//            db.execSQL("CREATE TABLE userTable (userID CHAR(20) PRIMARY KEY, userPassword CHAR(20))");
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//            db.execSQL("DROP TABLE IF EXISTS userTable");
//            onCreate(db);
//        }
//    }

//    public static class petDBHelper extends SQLiteOpenHelper {
//        public petDBHelper(Context context)
//        {
//            super(context, "petDB", null, 1);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL("CREATE TABLE petTable  (petID INTEGER, userID INTEGER, animal TEXT, petName TEXT, petSex TEXT, petKind TEXT, petBDay TEXT, petAllergy TEXT, uri TEXT);");
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
//            db.execSQL("DROP TABLE IF EXISTS petDB");
//            onCreate(db);
//        }
//
//    }
}