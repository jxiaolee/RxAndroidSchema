package com.kashuo.architecture.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.kashuo.architecture.bean.JustTestObj;
import com.kashuo.architecture.bean.User;
import com.kashuo.architecture.util.DataCreateUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jack on 2017/4/24.
 */

public class MainPresenter {

    //自定义MainPresenter TAG name.
    public static final String TAG_NAME = "MainPresenter";

    /**
     * RxJava三步走策略
     */

    //第一步 : 创建一个被观察者或者叫做发布者
    public Observable mObservable = Observable.create(new Observable.OnSubscribe<Integer>(){

        @Override
        public void call(Subscriber<? super Integer> subscriber) {
            subscriber.onNext(10001);
            subscriber.onNext(10002);
            subscriber.onNext(10003);
            subscriber.onNext(10004);
            subscriber.onNext(10005);

            subscriber.onCompleted();
        }
    });

    //第二步 : 创建一个观察者或者叫做订阅者
    public Subscriber mSubscriber = new Subscriber<Integer>() {
        //这边需要注意一点 : onCompleted和onError只会执行一个
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Integer integer) {
            Log.d(TAG_NAME, "display number : " + integer);
        }
    };

    /**
     * 其他几种创建Observable对象的方法
     */
    //通过from创建Observable
    public Observable mFromObservable = Observable.from(DataCreateUtil.careteMutiUsersList());

    //通过just创建Observable
    public Observable mJustObservable = Observable.just(DataCreateUtil.createJustParams());

    //通过timer创建Observable
    public Observable mTimerObservable = Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread()); //延迟5s执行

    //通过range创建Observable,此处叠加使用一个repeat方法
    public Observable mRangeObservable = Observable.range(2, 7).repeat(2);

    //第三步 : 让订阅者订阅发布者
    public void subscribeT() {
        mObservable.subscribe(mSubscriber);

        //自定义线程
        /**
         * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
         * Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
         * Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
         * Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。
         * AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
         */
        mObservable.subscribeOn(Schedulers.immediate()) //订阅关系消费的线程
                .observeOn(Schedulers.io()) //订阅者或者观察者消费的线程
                .subscribe(mSubscriber);

        mFromObservable.subscribe(new Subscriber<User>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                Log.d(TAG_NAME, "User : " + user.toString());
            }
        });

        mJustObservable.subscribe(new Subscriber<JustTestObj>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JustTestObj justTestObj) {
                Log.d(TAG_NAME, "JustTestObj : " + justTestObj.toString());
            }
        });

        mTimerObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG_NAME, "I am a operation delay 5 seconds......");
            }
        });

        //以下代码模块可以替代Handler的作用
        Observable.timer(3, TimeUnit.SECONDS, Schedulers.io()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                //Todo...延迟三秒执行其他方法
                Log.d(TAG_NAME, "I am a operation delay 3 seconds......");
            }
        });

        //Action1<T>可以代替实现onNext();
        //Action1<Throwable>可以代替实现onError();
        //Action0可以代替实现onComplete();
        mRangeObservable.subscribe(
                new Action1() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG_NAME, "Range onNext() : " + o.toString());
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                    }
                }
        );
    }

    //map的变换操作
    public void changeOperation() {
        final String imgFilePath = "../nice.jpg"; //自定义图片的存储路径

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Observable observable = Observable.just(imgFilePath);
                observable.map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String imgFilePath) {
                        //return getBitmapFromAssets(imgFilePath);
                        return null;
                    }
                }).subscribeOn(Schedulers.immediate())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Bitmap>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                //iv.setImageBitmap(bitmap);//为控件设置图片
                            }
                        });
            }
        };
    }

}
