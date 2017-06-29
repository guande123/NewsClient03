package com.gdcp.zgd.newsclient03.activity;

import android.content.Intent;
import android.os.SystemClock;

import com.gdcp.zgd.newsclient03.R;

/**
 * Created by yls on 2017/6/29.
 */

public class StartActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        new Thread() {
            public void run() {
                SystemClock.sleep(1500);
                enterGuideActivity();
            }
        }.start();
    }

    private void enterGuideActivity() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
