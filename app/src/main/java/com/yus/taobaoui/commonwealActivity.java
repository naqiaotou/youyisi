package com.yus.taobaoui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yus.taobaoui.util.UIUtils;
import com.yus.taobaoui.view.ButtomBtn;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class commonwealActivity extends AppCompatActivity {

    @BindView(R.id.bomBtnHome4)
    ButtomBtn bomBtnHome;

    @BindView(R.id.bomBtnAsk4)
    ButtomBtn bomBtnAsk;
    @BindView(R.id.bomBtnShopCar4)
    ButtomBtn bomBtnShopCar;
    @BindView(R.id.bomBtnMy4)
    ButtomBtn bomBtnMy;

    WebView web;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonweal);
        ButterKnife.bind(this);
        web=(WebView) findViewById(R.id.comeeonweal_webview);
        initBomBtn();
        loadWeb();
    }

    public void loadWeb(){
        String url = "http://10.10.30.248:8080/gongyi/";
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if(keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()){
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initBomBtn() {
        bomBtnHome.setIvAndTv(R.drawable.home_fill,"有衣购");
        bomBtnHome.setTvColor(Color.parseColor("#d81e06"));



        bomBtnAsk.setIvAndTv(R.drawable.ask,"有衣聊");
        bomBtnAsk.setTvColor(getResources().getColor(R.color.font33));

        bomBtnShopCar.setIvAndTv(R.drawable.cart,"有衣捐菜单");
        bomBtnShopCar.setTvColor(getResources().getColor(R.color.font33));

        bomBtnMy.setIvAndTv(R.drawable.my,"有衣搭");
        bomBtnMy.setTvColor(getResources().getColor(R.color.font33));
    }

    @OnClick(R.id.bomBtnHome4)
    public void bomBtnHome(View v) {
        Intent intenthome=new Intent(commonwealActivity.this,MainActivity.class);
        startActivity(intenthome);
    }

    @OnClick(R.id.bomBtnAsk4)
    public void bomBtnAsk(View v) {
        Intent intenthome=new Intent(commonwealActivity.this,ForumActivity.class);
        startActivity(intenthome);
    }
    @OnClick(R.id.bomBtnShopCar4)
    public void bomBtnShopCar(View v) {
        UIUtils.showToast("有衣捐");
    }
    @OnClick(R.id.bomBtnMy4)
    public void bomBtnMy(View v) {
        Intent intenthome=new Intent(commonwealActivity.this,myActivity.class);
        startActivity(intenthome);
    }
}
