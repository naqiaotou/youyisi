package com.yus.taobaoui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.yus.taobaoui.util.UIUtils;
import com.yus.taobaoui.view.ButtomBtn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class myActivity extends AppCompatActivity {
    /*@BindView(R.id.buttomBtnScan)
    ButtomBtn buttomBtnScan;

    @BindView(R.id.bomBtnMsg)
    ButtomBtn bomBtnMsg;*/

    @BindView(R.id.bomBtnHome5)
    ButtomBtn bomBtnHome;
    @BindView(R.id.bomBtnAsk5)
    ButtomBtn bomBtnAsk;
    @BindView(R.id.bomBtnShopCar5)
    ButtomBtn bomBtnShopCar;
    @BindView(R.id.bomBtnMy5)
    ButtomBtn bomBtnMy;

    @BindView(R.id.my_personal)
    LinearLayout personal;

   /* @BindView(R.id.rv)
    RecyclerView rv;*/
    private Handler mHandler;
    private List<String> bigPics;
    private List<String> smallPics=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);

        initBomBtn();
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myActivity.this,MydataActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initBomBtn() {
        bomBtnHome.setIvAndTv(R.drawable.home_fill,"有衣购");
        bomBtnHome.setTvColor(Color.parseColor("#d81e06"));

        bomBtnAsk.setIvAndTv(R.drawable.ask,"有衣聊");
        bomBtnAsk.setTvColor(getResources().getColor(R.color.font33));

        bomBtnShopCar.setIvAndTv(R.drawable.cart,"有衣捐");
        bomBtnShopCar.setTvColor(getResources().getColor(R.color.font33));

        bomBtnMy.setIvAndTv(R.drawable.my,"有衣搭");
        bomBtnMy.setTvColor(getResources().getColor(R.color.font33));
    }

    @OnClick(R.id.bomBtnHome5)
    public void bomBtnHome(View v) {
        Intent intenthome=new Intent(myActivity.this,MainActivity.class);
        startActivity(intenthome);
    }
    @OnClick(R.id.bomBtnAsk5)
    public void bomBtnAsk(View v) {
        Intent intenthome=new Intent(myActivity.this,ForumActivity.class);
        startActivity(intenthome);
    }
    @OnClick(R.id.bomBtnShopCar5)
    public void bomBtnShopCar(View v) {
        Intent intent=new Intent(myActivity.this,commonwealActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.bomBtnMy5)
    public void bomBtnMy(View v) { UIUtils.showToast("有衣穿");}



}
