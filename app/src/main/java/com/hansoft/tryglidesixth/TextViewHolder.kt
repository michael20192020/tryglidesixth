package com.hansoft.tryglidesixth

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mTv: TextView

    init {
        mTv = itemView.findViewById<View>(R.id.item_tv) as TextView
    }
}