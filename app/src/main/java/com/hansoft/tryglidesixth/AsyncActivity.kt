package com.hansoft.tryglidesixth

import android.graphics.drawable.Drawable
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class AsyncActivity : AppCompatActivity() {
    @BindView(R.id.loadPictureButton)
    var loadPictureButton : Button? = null
    companion object {
        const val IMAGE_LOAD_START = "start"
        const val IMAGE_LOAD_FINISH = "finish"
    }

    @BindView(R.id.imageView)
    var imageView: ImageView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        ButterKnife.bind(this)
        bindview();
    }

    fun bindview()
    {
        imageView = findViewById(R.id.imageView)
        loadPictureButton = findViewById(R.id.loadPictureButton)
        loadPictureButton!!.setOnClickListener(View.OnClickListener {
            onViewClicked(it)
        })
    }

    @OnClick(R.id.loadPictureButton)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.loadPictureButton -> {

                EspressoIdlingResource().increment()
                Glide.with(this)
                        .load("https://imgsrc.baidu.com/imgad/pic/item/caef76094b36acaf0accebde76d98d1001e99ce7.jpg")
                        .listener(object : RequestListener<Drawable?> {
                            override fun onLoadFailed(@Nullable e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                                println(e!!.localizedMessage)
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                                loadPictureButton!!.text = "success!"
                                //图片加载成功，结束异步
                                if (!EspressoIdlingResource().getIdlingResource()!!.isIdleNow()) {
                                    EspressoIdlingResource().decrement()
                                }
                                return false
                            }
                        }).into(imageView!!)
            }
        }
    }


    @get:VisibleForTesting
    val countingIdlingResource: IdlingResource
        get() = EspressoIdlingResource().getIdlingResource()!!


}