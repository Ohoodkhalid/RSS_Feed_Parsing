package com.example.rssfeedparsing

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter (var  details: List<Details>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    val TAG = "Adapter"
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val details = details[position]


        holder.itemView.apply {
            tvTitle.text = details.title
            Log.d("TAG","SUCC $details.title")

        }
    }

    override fun getItemCount() = details.size

    fun update(details: List<Details>){
        this.details = details
        notifyDataSetChanged()
    }

}


