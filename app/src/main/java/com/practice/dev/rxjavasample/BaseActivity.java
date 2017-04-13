package com.practice.dev.rxjavasample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

/**
 * Created by HY on 2017/4/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Subscription subscription;

    protected void unsubscribe(){
        if (subscription!=null&&!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
