package com.fanhl.fetchandjumpdemo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)
        //用于模拟初始化UI缓慢
        Thread.sleep(1000)

        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)

        viewModel.text.observe(this, Observer {
            tv_text.text = it
        })

        EventBusLifecycle.bind(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(data: String) {
        viewModel.text.value = data
    }

    companion object {
        fun launch(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, SecondActivity::class.java))

            // load async data
            object : AsyncTask<Void, Void, String>() {
                override fun doInBackground(vararg params: Void?): String {
                    Thread.sleep(200)
                    return "${System.currentTimeMillis()}"
                }

                override fun onPostExecute(result: String?) {
                    EventBus.getDefault().postSticky(result)
                }
            }.execute()
        }

        fun launch(fragment: Fragment) {
            fragment.startActivity(Intent(fragment.activity, SecondActivity::class.java))
        }
    }

    class LiveDataViewModel : ViewModel() {
        val text = MutableLiveData<String>()
    }
}
