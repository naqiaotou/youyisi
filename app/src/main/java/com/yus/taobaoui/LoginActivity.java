package com.yus.taobaoui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yus.taobaoui.database.DatabaseHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout et_Name;
    TextInputLayout et_Password;
    public static  final String PREFERENCE_NAME="SaveSetting";

    @BindView(R.id.bt_login)
    Button loginBton;


    @BindView(R.id.tv_registe_login)
    Button btRegiste;

    @BindView(R.id.tv_registe_refactor)
    Button btrefactor;

    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_Name = (TextInputLayout) findViewById(R.id.txip_username);
        et_Password = (TextInputLayout) findViewById(R.id.txip_passworld);
        helper=new DatabaseHelper(this);
        ButterKnife.bind(this);
        init();
    }

    public void init(){
        SharedPreferences sharedPreferences2 =
                getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String sv_Name=sharedPreferences2.getString("name","");
        String sv_Password=sharedPreferences2.getString("password","");
        et_Name.setHint("手机号/邮箱/用户名");
        et_Password.setHint("请输入密码");
        loginBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_Name.getEditText().getText().toString();
                String password = et_Password.getEditText().getText().toString();
                //et_Name.setErrorEnabled(false);
                //et_Password.setErrorEnabled(false);
                //验证用户名和密码
                if(validateAccount(account)&&validatePassword(password)){
                    if(check(account,password))
                    {
                        Toast.makeText(LoginActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btRegiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(StringUtils.isEmpty(account)){
            showError(et_Name,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            showError(et_Password,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(et_Password,"密码长度为6-18位");
            return false;
        }

        return true;
    }

    public boolean check(String account,String password)
    {
        String pwd="";
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select password from shiti where ID='" + account + "'", null);
        while (cursor.moveToNext()) {
            pwd=cursor.getString(0);
        }
        if(password.equals(pwd))
        {
            return true;
        }
        return false;
    }
}
