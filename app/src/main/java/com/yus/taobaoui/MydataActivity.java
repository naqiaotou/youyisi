package com.yus.taobaoui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yus.taobaoui.database.DatabaseHelper;

public class MydataActivity extends AppCompatActivity {
    String[] string=new String[7];
    EditText[] editText=new EditText[7];
    String ID="123";
    Button shanchu;
    Button update;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydata);
        /*Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        ID = bundle.getString("sqlID");*/
        editText[0]=(EditText) findViewById(R.id.editText10);
        editText[1]=(EditText)findViewById(R.id.editText11);
        editText[2]=(EditText)findViewById(R.id.editText12);
        editText[3]=(EditText)findViewById(R.id.et_xiongwei);
        editText[4]=(EditText)findViewById(R.id.editText13);
        editText[5]=(EditText)findViewById(R.id.editText14);
        editText[6]=(EditText)findViewById(R.id.editText15);
        helper=new DatabaseHelper(this);
        update=(Button) findViewById(R.id.button20);
        show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strinsert="update shiti SET height=\"";
                getmsg();
                SQLiteDatabase db=helper.getWritableDatabase();
                db.execSQL(strinsert+string[0]+"\",weight=\""+string[1]+"\",jiankuan=\""+string[2]+"\",xiongwei=\""
                        +string[3]+"\",yaowei=\""+string[4]+"\",tunwei=\""+string[5]+"\",tuiwei=\""+string[6]+"\" WHERE ID=\""+ID+"\"");
                Toast.makeText(MydataActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getmsg(){
        int i;
        for (i=0;i<6;i++)
        {
            string[i]=editText[i].getText().toString();
        }
    }

    public void show(){
        int i;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select height,weight,jiankuan,xiongwei,yaowei,tunwei,tuiwei from shiti where ID='" + ID + "'", null);
        while (cursor.moveToNext()) {
            for (i = 0; i < 7; i++) {
                editText[i].setText(cursor.getString(i));
            }
        }
    }
}
