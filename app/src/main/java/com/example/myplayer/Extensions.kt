package com.example.myplayer


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

fun Context.toast(message:String){

    Toast.makeText(this,message,Toast.LENGTH_LONG).show()

}

fun RecyclerView.ViewHolder.toast(message :String){

    itemView.context.toast(message)
}

fun ViewGroup.inflate (layout :Int ): View {

    return LayoutInflater.from(context).inflate(layout,this,false)
}

fun ImageView.loadUrl (url:String){

    Picasso.with(context).load(url).into(this)
}

inline fun <reified T:View>View.find (idRes:Int):T{


    return findViewById(idRes)
}