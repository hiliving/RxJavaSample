package com.practice.dev.rxjavasample;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;


/**
 * Created by Helloworld on 2017/10/14.
 */

public class Demo extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建
        Create();

    }

    /**
     * create操作符
     */
    public void Create(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("这个是Rxjava学习的Demo");

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("ttttt",s);
            }
        });
    }

    /**
     * just操作符其实是对创建Observable的一种快捷方式
     */
    public void Just(){
        Observable.just("这个是Rxjava学习的Demo").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("ttttt",s);
            }
        });
    }

    /**
     * from，从一种数据类型转为observable
     * 例如数组
     *     列表
     */
    public void From(){
        Observable.from(new Integer[]{1,2,3,4,5,6}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d("ttttt",s+"");
            }
        });
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Observable.from(list).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d("ttttt",s+"");
            }
        });
    }
    private String value;//初始值为null
    public void Defer(){
        final Observable observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable call() {
                return Observable.just(value);
            }
        });
        value = "这个是Defer的演示例子";
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                Log.d("ttttt",o);
            }
        });
    }

    /**
     * interval 定时器，可以间隔时间发射数据
     */
    public void interval(){

    }

    /**
     * range 从一个范围内依次发射
     */
    public void range(){
        Observable observable = Observable.range(1,6);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.d("ttttt",o+"");
            }
        });
    }

    /**
     * repeat 重复发射操作
     */
    public void repeat(){
        Observable observable = Observable.range(1,6).repeat(2);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("ttttt","onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.d("ttttt",o+"");
            }
        });
    }
    /**
     * 转换的操作符 map   flatMap   GroupBy   Buffer  Scan累记  Window
     * map 将一个对象转换为另一个对象
     */
    public void map(){
        Observable.just(123).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return integer+"";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("ttttt",s+"");
            }
        });
    }

    /**
     * flatMap 非常实用的转换操作，应用场景，开发中一个网络请求依赖另一个网络请求的结果
     * 将一个列表转为多个Observable
     */
    public void flatMap(){
        Observable.just(1,2,3,4,5).flatMap(new Func1<Integer, Observable<? extends  String>>() {
            @Override
            public Observable<? extends  String> call(Integer integer) {
                return Observable.just(integer+"");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("ttttt",s+"");
            }
        });
    }

    /**
     * 凑够2个数据就发射，buffer类似缓存
     */
    public void buffer(){
        Observable.range(1,5).buffer(2).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                Log.d("ttttt",integers+"");
            }
        });
    }
    /**
     * Debounce暂停一段时间后发射 Distinct去重
     * ElementAt 取指定位置的数据 Filter过滤
     * First 去列表第一个数据
     * IgnoreElements忽略数据，只回调onComplete或者Error函数，不回调onNext
     * Last去列表最后个数据
     * Sample在元数据里采样后生成一组集合再发射到观察者，然后清空缓存再次采样。
     * Skip跳过几项
     * SkipLast跳过最后几项
     * Take取其中几项
     * TakeLast取数据列表最后几项
     */

    /**
     * Zip
     * Merge
     * StartWith
     * CombineLatest
     * Join
     * SwitchOnNext
     */
}
