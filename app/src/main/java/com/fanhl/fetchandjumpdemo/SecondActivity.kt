package com.fanhl.fetchandjumpdemo

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, SecondActivity::class.java))
        }

        fun launch(fragment: Fragment) {
            fragment.startActivity(Intent(fragment.activity, SecondActivity::class.java))
        }
    }

    class LiveDataViewModel : ViewModel()
}
