package com.gdcp.zgd.newsclient03.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(getLayoutRes());
            initView();
            initListener();
            initData();
        }

        protected abstract void initData();

        protected abstract void initListener();

        protected abstract void initView();

        protected abstract int getLayoutRes();
       private  Toast mToast;
        public void showToast(String content){
            if (mToast == null) {
                mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            }
            mToast.setText(content);
            mToast.show();
     }
}
