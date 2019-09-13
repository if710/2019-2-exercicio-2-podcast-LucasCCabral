package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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

    private fun povoatePodCastView() {
        if(items.isNotEmpty()) {
            viewManager = LinearLayoutManager(this)
            viewAdapter = PodcastAdapter(items)

            recyclerView = findViewById<RecyclerView>(R.id.podcastList).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)
                // use a linear layout manager
                layoutManager = viewManager
                // specify an viewAdapter (see also next example)
                adapter = viewAdapter
            }
        }
    }

    private fun getAndLoadPodCasts() {
        val parser = Parser
        doAsync {
            Log.d("DEV-LOG", "Async Started")
            items = parser.parse(XML_FILE)
            Log.d("DEV-LOG", "Async Ended")
            uiThread {
                Log.d("DEV-LOG", "UI Thread Started")
                povoatePodCastView()
                Log.d("DEV-LOG", "UI Thread Ended")
            }
        }
    }

}
