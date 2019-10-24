package com.example.myplayer

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.MediaItem.Type
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*
import kotlin.properties.Delegates


class MediaAdapter(items: List<MediaItem> = emptyList(),val listner: (MediaItem)->Unit)
    : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {


    var items : List<MediaItem> by Delegates.observable(emptyList()){ _, _, _ ->

        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    //    val inflate = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)

        val v = parent.inflate(R.layout.row_item)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{listner(item)}


    }


    class ViewHolder (view: View): RecyclerView.ViewHolder(view){



        fun bind (item:MediaItem){

            itemView.media_title.text = item.title
           // Picasso.with(image.context).load(item.thumbUrl).into(image)

            itemView.media_thumb.loadUrl(item.thumbUrl)

            itemView.media_video_indicator.visibility = when(item.type){

                Type.VIDEO -> View.VISIBLE
                Type.PHOTO->View.INVISIBLE

                else -> View.GONE
            }

            toast("hello")
        }
    }

}


