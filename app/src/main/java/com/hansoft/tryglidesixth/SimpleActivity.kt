package com.hansoft.tryglidesixth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick


class SimpleActivity : AppCompatActivity() {
    @BindView(R.id.modifyButton)
    var btn01: Button? = null

    @BindView(R.id.tv_content)
    var tvContent: TextView? = null

    @BindView(R.id.nameEditText)
    var nameEditText: EditText? = null

    @BindView(R.id.loginButton)
    var loginButton: Button? = null

    var modifyButton : Button? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        ButterKnife.bind(this)
        bindview()
    }

    fun bindview()
    {
        nameEditText = findViewById(R.id.nameEditText)
        tvContent = findViewById(R.id.tv_content)
        modifyButton = findViewById(R.id.modifyButton)
        loginButton = findViewById(R.id.loginButton)
        modifyButton!!.setOnClickListener(View.OnClickListener {
            onViewClicked(it)
        })
        loginButton!!.setOnClickListener(View.OnClickListener {
            onViewClicked(it)
        })

    }

    @OnClick(R.id.modifyButton, R.id.loginButton)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.modifyButton -> {
                tvContent!!.setVisibility(View.VISIBLE)
                tvContent!!.setText("hello espresso!")
            }
            R.id.loginButton -> {
                tvContent!!.setVisibility(View.VISIBLE)
                tvContent!!.setText("success")
                nameEditText!!.setText("")
            }
        }
    }
}