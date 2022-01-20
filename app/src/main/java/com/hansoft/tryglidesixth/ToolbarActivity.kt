package com.hansoft.tryglidesixth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife

class ToolbarActivity : AppCompatActivity() {
    @BindView(R.id.toolbar)
    var toolbar: Toolbar? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        bindview()

    }

    fun bindview()
    {
        toolbar = findViewById(R.id.toolbar)
        toolbar!!.setTitle(R.string.hello_espresso)
    }
}