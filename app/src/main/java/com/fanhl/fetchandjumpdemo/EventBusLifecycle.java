package com.fanhl.fetchandjumpdemo;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

/**
 * desc: 将EventBus绑定Lifecycle抽成共通放在这里
 * date: 2018/1/5
 *
 * @author fanhl
 */
public class EventBusLifecycle implements LifecycleObserver {
    /** TAG */
    private static final String TAG = EventBusLifecycle.class.getSimpleName();
    /** subscriber */
    private final Object subscriber;

    /**
     * 绑定
     *
     * @param activity
     *         activity
     */
    public static void bind(FragmentActivity activity) {
        new EventBusLifecycle(activity, activity.getLifecycle());
    }

    /**
     * 绑定
     *
     * @param fragment
     *         fragment
     */
    public static void bind(@NotNull Fragment fragment) {
        new EventBusLifecycle(fragment, fragment.getLifecycle());
    }

    /**
     * 构造器
     *
     * @param subscriber
     *         subscriber
     * @param lifecycle
     *         生命周期
     */
    private EventBusLifecycle(Object subscriber, Lifecycle lifecycle) {
        this.subscriber = subscriber;
        lifecycle.addObserver(this);
    }


    /**
     * onCreate
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.d(TAG, "onCreate ");
        EventBus.getDefault().register(subscriber);
    }

//    /**
//     * onResume
//     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void onResume() {
//        Log.d(TAG, "onResume ");
//    }
//
//    /**
//     * onPause
//     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    public void onPause() {
//        Log.d(TAG, "onPause ");
//    }

    /**
     * onDestroy
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
        EventBus.getDefault().unregister(subscriber);
    }
}
