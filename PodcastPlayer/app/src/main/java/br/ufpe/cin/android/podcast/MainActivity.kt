package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    val XML_FILE = "https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val parser = Parser
        var items : List<ItemFeed>
        //parser.downloadXML(XML_FILE)

        doAsync {
            Log.d("DEV-LOG", "Async Started")
            items = parser.parse(XML_FILE)
            Log.d("DEV-LOG", "Async Ended")
            uiThread {
                    Log.d("DEV-LOG", "UI Thread Started")
                    items.forEach {
                        Log.d("DEV-LOG", it.toString())
                    }
                    Log.d("DEV-LOG", "UI Thread Ended")
                }
            }
        }
}
