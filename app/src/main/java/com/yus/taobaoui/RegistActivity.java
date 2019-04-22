package com.yus.taobaoui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.yus.taobaoui.database.DatabaseHelper;
import com.yus.taobaoui.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity  extends AppCompatActivity {

    @BindView(R.id.til_username_register)
    TextInputLayout mUserName ;
    @BindView(R.id.til_pwd_register)
    TextInputLayout mPwd ;
    @BindView(R.id.til_repwd_register)
    TextInputLayout mRepwd ;
    DatabaseHelper helper;
    String[] string=new String[11];

    @OnClick(R.id.bt_registClick) void registClick(){
        String userName = mUserName.getEditText().getText().toString();
        String pwd = mPwd.getEditText().getText().toString();
        String rePwd = mRepwd.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName)|| TextUtils.isEmpty(pwd)|| TextUtils.isEmpty(rePwd)){
            UIUtils.showToast("请输入完整信息");
            return;
        }
        if (!pwd.equals(rePwd)){
            UIUtils.showToast("两次密码不相同");
            return;
        }
        string[0]=userName;
        string[1]=pwd;
        String strinsert="insert into shiti values(";
        int i;
        for(i=2;i<11;i++)
        {
            string[i]="";
        }
        for(i=0;i<11;i++)
        {
            strinsert=strinsert+"\""+string[i]+"\"";
            if(i<10)
            {
                strinsert=strinsert+",";
            }
        }
        strinsert=strinsert+")";
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL(strinsert);
        Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        helper=new DatabaseHelper(this);
    }
}
