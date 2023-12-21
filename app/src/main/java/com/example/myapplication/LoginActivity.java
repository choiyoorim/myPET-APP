package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    DBHelper myDBHelper;
    EditText loginuserId, loginuserPassword;
    Button btnLogin;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("로그인");

        myDBHelper = new DBHelper(this);
        loginuserId = (EditText) findViewById(R.id.loginuserID);
        loginuserPassword = (EditText) findViewById(R.id.loginuserPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM userTable WHERE userID='"+loginuserId.getText().toString()+"';",null);
                cursor.moveToFirst();
                if(cursor.getString(1).equals(loginuserPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),"로그인에 성공했습니다.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainPageActivity.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userID",loginuserId.getText().toString());
                    startActivity(intent);
                }
                else if(cursor.getCount() <=0){
                    Toast.makeText(getApplicationContext(),"가입된 아이디가 없습니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"로그인에 실패했습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context){
            super(context, "userDb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE userTable (userID CHAR(20) PRIMARY KEY, userPassword CHAR(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS userTable");
            onCreate(db);
        }
    }
}