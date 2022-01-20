package com.hansoft.tryglidesixth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlin.math.log

class IndexActivity : AppCompatActivity() {

    var firstButton : Button? = null
    var asyncButton : Button? = null
    var recyclerViewButton : Button? = null
    var rotateButton: Button? = null
    var toolbarButton : Button? = null
    var async2Button : Button? = null
    var okhttpButton : Button? = null
    var daggerButton : Button? = null


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        ButterKnife.bind(this)
        bindview();
    }

    fun bindview()
    {
        firstButton = findViewById(R.id.firstButton)
        asyncButton = findViewById(R.id.asyncButton)
        recyclerViewButton = findViewById(R.id.recyclerViewButton)
        rotateButton = findViewById(R.id.rotateButton)
        toolbarButton = findViewById(R.id.toolbarButton)
        async2Button = findViewById(R.id.async2Button)
        okhttpButton = findViewById(R.id.okhttpButton)
        daggerButton = findViewById(R.id.daggerButton)
        firstButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SimpleActivity::class.java))
        })

        asyncButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AsyncActivity::class.java))
        })

        recyclerViewButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ListActivity::class.java))

        })
        rotateButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RotateActivity::class.java))
        })

        toolbarButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ToolbarActivity::class.java))
        })
        async2Button!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, AsyncActivity2::class.java))
        })
        okhttpButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, OkhttpActivity::class.java))
        })
        daggerButton!!.setOnClickListener(View.OnClickListener {

        })
    }


}