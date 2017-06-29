package com.gdcp.zgd.newsclient03.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gdcp.zgd.newsclient03.R;

/**
 * Created by yls on 2017/6/29.
 */
public class GuideActivity  extends  BaseActivity{
    private ImageView iv01;
    private Button btnGo;
    private int count = 0;
    private int[] imagesArray = new int[] {
            R.drawable.ad_new_version1_img1,
            R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3,
            R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6,
            R.drawable.ad_new_version1_img7,
    };
    private Handler mHandler = new Handler() {
        @Override   // mhandler发消息后，会执行此方法处理消息
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    startAnimation();
                    break;
            }
        }
    };

    private void startAnimation() {
        count ++;
        count = count % imagesArray.length;     // 取余数
        iv01.setBackgroundResource(imagesArray[count]);

        iv01.setScaleX(1.0f);       // 控件恢复为原来的大小，1倍
        iv01.setScaleY(1.0f);

        iv01.animate()
                .scaleX(1.2f)           // 控件放大到原来的1.2倍
                .scaleY(1.2f)
                .setDuration(3500)      // 动画执行时间是3.5秒
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // 延迟1秒后发消息，发消息后，会调用mHandler的handleMessage方法， 此处what为0，handleMessage会根据0作判断。
                        mHandler.sendEmptyMessageDelayed(0, 1000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        iv01 = (ImageView) findViewById(R.id.iv_01);
        btnGo = (Button) findViewById(R.id.btn_go);
    }

    @Override
    public void initListener() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initData() {
        startAnimation();       // 开始显示动画
    }
}
