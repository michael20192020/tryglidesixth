package com.hansoft.tryglidesixth


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
   // var textView: TextView?
    var mTitle: TextView? = null
    var mDescription: TextView? = null
    var imageView: ImageView? = null

    init {
       // textView = itemView!!.findViewById<View>(R.id.item_title) as TextView
        mTitle = itemView!!.findViewById(R.id.item_title)
        mDescription = itemView!!.findViewById<View>(R.id.item_description) as TextView
        imageView = itemView!!.findViewById(R.id.item_imageHref)

    }


}