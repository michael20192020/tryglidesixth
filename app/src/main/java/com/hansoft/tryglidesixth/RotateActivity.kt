package com.hansoft.tryglidesixth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife


class RotateActivity : AppCompatActivity() {
    @BindView(R.id.countTextView)
    var countTextView: TextView? = null
    var incrementButton : Button? =null
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)
        ButterKnife.bind(this)
        if (savedInstanceState != null) {
            Log.d("aaa", "onCreate: aaa")
            count = savedInstanceState.getInt(KEY_COUNT, 0)
        }else
        {
            Log.d("aaa", "onCreate: bbb")
        }
        bindview()
        updateCount()

    }

    fun bindview()
    {
        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)
        incrementButton!!.setOnClickListener(View.OnClickListener {
            count += 1
            updateCount()
        })
    }


    override fun onStart() {
        super.onStart()
        Log.e("aaa", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("aaa", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("aaa", "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.e("aaa", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("aaa", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("aaa", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, count)
        Log.d("aaa", "onSaveInstanceState: aaa")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle)
    {
        Log.d("aaa", "onRestoreInstanceState: aaa")
    }


    private fun updateCount() {
        countTextView!!.setText(count!!.toString())
    }

    companion object {
        private const val KEY_COUNT = "count"
    }
}