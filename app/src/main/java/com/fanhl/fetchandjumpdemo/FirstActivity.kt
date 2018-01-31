package com.fanhl.fetchandjumpdemo

import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            SecondActivity.launch(this@FirstActivity)
            val viewModel = ViewModelProviders.of(this).get(SecondActivity.LiveDataViewModel::class.java)
            object : AsyncTask<Void, Void, String>() {
                override fun doInBackground(vararg params: Void?): String {
                    Thread.sleep(2000)
                    return "${System.currentTimeMillis()}"
                }

                override fun onPostExecute(result: String?) {
                    viewModel.text.value = result
                }
            }.execute()
        }
    }
}
