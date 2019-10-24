package com.example.myplayer

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random


object MediaProvider{
    private val thumbBase = "http://lorempixel.com/400/400/cats/"

    private val rnd = Random(1)


    private var data = emptyList<MediaItem>()

    private fun randomType () = rnd.nextInt(2).let { if(it==0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO }


    fun dataSync ( type : String  = "cats",callBack: (List<MediaItem>) -> Unit) {


        doAsync {

            if(data.isEmpty()) {

                Thread.sleep(2000)
                data = dataSync(type)
            }

            uiThread {
                callBack(data)
            }

        }


    }

    fun dataSync(type:String):List<MediaItem>{

        Thread.sleep(2000)
        return  (1..10).map {
            MediaItem(it, "Title $it", "$thumbBase$type/$it",if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO)
        }
    }


}






