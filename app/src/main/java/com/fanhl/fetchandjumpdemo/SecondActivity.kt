package com.fanhl.fetchandjumpdemo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        prepareData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        //用于模拟初始化UI缓慢
        Thread.sleep(1000)


        viewModel.text.observe(this, Observer {
            tv_text.text = it
        })
    }

    private fun prepareData() {
        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String {
                Thread.sleep(200)
                return "${System.currentTimeMillis()}"
            }

            override fun onPostExecute(result: String?) {
                viewModel.text.value = result
            }
        }.execute()
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SecondActivity::class.java))
        }

        fun launch(fragment: Fragment) {
            fragment.startActivity(Intent(fragment.activity, SecondActivity::class.java))
        }
    }

    class LiveDataViewModel : ViewModel() {
        val text = MutableLiveData<String>()
    }
}
