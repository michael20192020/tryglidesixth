package com.hansoft.tryglidesixth

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MyAdapter : RecyclerView.Adapter<TextViewHolder>() {
    private var mData: ArrayList<String>? = null
    private var onItemClickListener: OnItemClickListener? = null
    fun initData(data: ArrayList<String>) {
        mData = data
    }


    fun addNewItem() {
        if (mData == null) {
            mData = ArrayList()
        }
        mData!!.add(0, "new Item")
        notifyItemInserted(0)
    }

    fun deleteItem() {
        if (mData == null || mData!!.isEmpty()) {
            return
        }
        mData!!.removeAt(0)
        notifyItemRemoved(0)
    }

    fun updateData(data: ArrayList<String>?) {
        mData = data
        notifyDataSetChanged()
    }



    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onItemLongClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TextViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_rv_item, parent, false)
        return TextViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: TextViewHolder,
        position: Int
    ) {
        holder.mTv.setText(mData!![position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (onItemClickListener != null) {
                val pos: Int = holder.getLayoutPosition()
                onItemClickListener!!.onItemClick(holder.itemView, pos)
            }
        })
        holder.itemView.setOnLongClickListener(OnLongClickListener {
            if (onItemClickListener != null) {
                val pos: Int = holder.getLayoutPosition()
                onItemClickListener!!.onItemLongClick(holder.itemView, pos)
            }
            true
        })
    }

    override fun getItemCount(): Int {
        return if (mData == null) 0 else mData!!.size
    }

}