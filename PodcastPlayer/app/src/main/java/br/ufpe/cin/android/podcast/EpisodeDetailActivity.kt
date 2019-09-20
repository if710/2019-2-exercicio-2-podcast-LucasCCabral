package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var link : TextView
    private lateinit var description : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        title = findViewById(R.id.title)
        link = findViewById(R.id.link)
        description = findViewById(R.id.description)

    }
}
