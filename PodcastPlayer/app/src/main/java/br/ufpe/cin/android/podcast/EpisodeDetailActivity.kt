package br.ufpe.cin.android.podcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_episode_detail.view.*

class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var link : TextView
    private lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)
        val podcast = intent.getSerializableExtra("itemfeed") as? ItemFeed
        title = findViewById(R.id.title)
        link = findViewById(R.id.link)
        description = findViewById(R.id.description)

        if (podcast != null) {
            title.text = podcast.title
            link.text = podcast.link
            description.text = podcast.description
        }

    }
}
