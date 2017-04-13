package com.practice.dev.rxjavasample;

import android.net.Network;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.practice.dev.rxjavasample.model.SampleBean;
import com.practice.dev.rxjavasample.network.NetWork;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.piclist)
    ListView piclist;
    @Bind(R.id.search)
    Button search;
    private Subscription subscription;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        search.setOnClickListener(this);
        adapter = new ListAdapter(this);
        piclist.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                show("可爱");
                break;
        }
    }
    private void show(String key) {
        unsubscribe();
        adapter.setImage(null);
        subscription = NetWork.getZhuangbiApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SampleBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<SampleBean> sampleBean) {
                        adapter.setImage(sampleBean);
                    }
                });
    }
}
