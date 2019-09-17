package br.ufpe.cin.android.podcast.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Podcast(
    @PrimaryKey val title: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "pubdate") val pubDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "downlink") val downloadLink: String
)
