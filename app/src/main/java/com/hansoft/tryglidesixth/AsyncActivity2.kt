package com.hansoft.tryglidesixth

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource

class AsyncActivity2 : AppCompatActivity() {
    private var textView: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async2)
        textView = findViewById(R.id.text) as TextView?
        textView!!.setVisibility(View.GONE)
        val fragment = LoadingDialogFragment()
        fragment.isCancelable = false
        fragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG)
        EspressoIdlingResource().increment()
    }

    fun onLoadingFinished() {
        if (!EspressoIdlingResource().getIdlingResource()!!.isIdleNow()) {
            EspressoIdlingResource().decrement()
        }
        textView!!.setText("done")
        textView!!.setVisibility(View.VISIBLE)
    }

    @get:VisibleForTesting
    val idlingresource: IdlingResource
        get() = EspressoIdlingResource().getIdlingResource()!!
}