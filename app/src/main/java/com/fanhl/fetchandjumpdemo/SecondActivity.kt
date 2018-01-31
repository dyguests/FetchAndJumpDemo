package com.fanhl.fetchandjumpdemo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private lateinit var viewModel: LiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)

        viewModel.text.observe(this, Observer {
            tv_text.text = it
        })
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
