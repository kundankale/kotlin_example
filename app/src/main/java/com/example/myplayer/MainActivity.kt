package com.example.myplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myplayer.MediaItem.Type
import com.example.myplayer.MediaItem.Type.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import kotlin.coroutines.coroutineContext


class MainActivity : AppCompatActivity() {


    val adapter =  MediaAdapter{ nevigateDetail(it) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recycler.adapter = adapter

        MediaProvider.dataSync { adapter.items=it }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

      /* val filter:Filter= when(item.itemId ) {


           R.id.filter_all->Filter.None()

           R.id.filter_photos -> Filter.ByType(MediaItem.Type.PHOTO)

           R.id.filter_videos -> Filter.ByType(MediaItem.Type.VIDEO)
           else -> null
       }
*/



      //  loadFilterData(filter)



        return true
    }

    private fun loadFilterData(filter: Filter) {


        MediaProvider.dataSync { media->

            adapter.items = when(filter ){
                is Filter.None -> media
                is Filter.ByType ->media.filter { it.type == filter.type }
            }
        }
    }

    private fun nevigateDetail(it: MediaItem) {

        startActivity<DetailActivity>(DetailActivity.ID to it.id)
    }


}
sealed class Filter{

     class None() : Filter()

    class ByType(val type:MediaItem.Type):Filter()



}