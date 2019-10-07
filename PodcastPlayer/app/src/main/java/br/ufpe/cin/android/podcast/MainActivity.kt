package br.ufpe.cin.android.podcast

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import br.ufpe.cin.android.podcast.persistence.AppDatabase
import br.ufpe.cin.android.podcast.persistence.Podcast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.net.ConnectivityManager


class MainActivity : AppCompatActivity() {
    val XML_FILE = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"

    private lateinit var items : List<ItemFeed>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAndLoadPodCasts()

     }

    private fun persistPodcasts(){
        //val podList = ArrayList<Podcast>()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Podcast"
        ).build()

        doAsync {
            items.forEach {
                var podcast = Podcast(
                    it.title,
                    it.link,
                    it.pubDate,
                    it.description,
                    it.downloadLink
                )
                //podList.add(podcast)
                db.getDao().insertAll(podcast)
            }
        }
    }

    private fun populatePodCastView() {
        if(items.isNotEmpty()) {
            viewManager = LinearLayoutManager(this)
            viewAdapter = PodcastAdapter(items)
            recyclerView = findViewById<RecyclerView>(R.id.podcastList).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun getAndLoadPodCasts() {
        val parser = Parser
        if(isNetworkAvailable()){
            Log.d("DEV-LOG", "Connected to internet.")
            doAsync {
                Log.d("DEV-LOG", "Async Started")
                items = parser.parse(XML_FILE)
                Log.d("DEV-LOG", "Async Ended")
                uiThread {
                    Log.d("DEV-LOG", "UI Thread Started")
                    persistPodcasts()
                    populatePodCastView()
                    Log.d("DEV-LOG", "UI Thread Ended")
                }
            }
        } else {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "Podcast"
            ).build()
            Log.d("DEV-LOG", "No internet connection.")
            doAsync {
                var podcastFeed = db.getDao().getAll()
                var podcastAux = ArrayList<ItemFeed>()
                podcastFeed.forEach {
                    var podcastItem = ItemFeed(
                        it.title,
                        it.link,
                        it.pubDate,
                        it.description,
                        it.downloadLink
                    )
                    podcastAux.add(podcastItem)
                }
                Log.d("DEV-LOG", "Not connected")
                items = podcastAux
                uiThread {
                    Log.d("DEV-LOG", "UI Thread Started")
                    populatePodCastView()
                    Log.d("DEV-LOG", "UI Thread Ended")

                }
            }
        }
    }

}
