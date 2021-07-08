    package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

    class NewsListAdapter(private val listener:NewsItemClicked):RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items [viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.src.text = currentItem.source_id
        Glide.with(holder.itemView.context).load(currentItem.image_url).into(holder.image)
    }
    fun updateNews(updatedNews : ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.title)
    val src: TextView = itemView.findViewById(R.id.sourceView)
    val image : ImageView = itemView.findViewById(R.id.newsImage)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}