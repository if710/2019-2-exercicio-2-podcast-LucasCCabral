package br.ufpe.cin.android.podcast

import java.io.Serializable

data class ItemFeed(val title: String, val link: String, val pubDate: String,
                    val description: String, val downloadLink: String) : Serializable{

    override fun toString(): String {
        return title
    }
}
