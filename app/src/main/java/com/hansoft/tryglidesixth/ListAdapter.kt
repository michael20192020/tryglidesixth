package com.hansoft.tryglidesixth

import android.R
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class ListAdapter(activity : ListActivity,data: ArrayList<Canada>, listener: OnItemClickListener?) : RecyclerView.Adapter<ListViewHolder?>() {

    private val listener: OnItemClickListener?

    //private val labels: ArrayList<String>
    private var myActivity = activity
    private var canadas = ArrayList<Canada>()
    init {
        /*
        labels = ArrayList(count)
        for (i in 0 until count) {
            labels.add(i.toString())
        }

         */
        canadas = data
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            com.hansoft.tryglidesixth.R.layout.list_item_new, parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val canada = canadas[position]

        holder.mTitle!!.setText(canada.title)
        holder.mDescription!!.setText(canada.description)
        holder.imageView!!.tag = position
        var url: String = canadas[position % canadas.size].imageHref!!
        Log.d("aaa ", "onBindViewHolder: url = $url")
        if (url === "null") {
            Log.d("aaa", "onBindViewHolder: aaaddd url = $url")
            // Glide.with(MainActivity.this).load(url)
            //        .placeholder(R.drawable.pagenotfound)
            //        .into(holder.imageView);
            holder.imageView!!.setImageDrawable(null)
            Log.d(
                "aaa",
                "onBindViewHolder: canadas.get(position).getTitle() = " + canadas[position].title
            )
        } else if (url.indexOf("https") >= 0) {
            Glide.with(myActivity).load(url)
                .placeholder(com.hansoft.tryglidesixth.R.drawable.pagenotfound)
                .into(holder.imageView!!)
            Log.d("aaa", "onBindViewHolder: aaabbb")
        } else if (url.indexOf("http") >= 0) {
            Log.d("aaa", "onBindViewHolder: aaaccc")
            url = url.replace("http", "https")
            Log.d("aaa", "onBindViewHolder: aaaccc  url = $url")
            Glide.with(myActivity).load(url)
                .placeholder(com.hansoft.tryglidesixth.R.drawable.pagenotfound)
                .into(holder.imageView!!)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val outValue = TypedValue()
            holder.mTitle!!.getContext().getTheme().resolveAttribute(
                R.attr.selectableItemBackground, outValue, true
            )
            holder.mTitle!!.setBackgroundResource(outValue.resourceId)
        }
        if (listener != null) {
            holder.mTitle!!.setOnClickListener(View.OnClickListener { listener.onItemClick(position) })
        }
    }

    override fun getItemCount(): Int {
        return if (canadas == null) 0 else canadas!!.size
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}