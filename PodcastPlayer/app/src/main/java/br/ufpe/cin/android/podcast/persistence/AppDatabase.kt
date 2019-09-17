package br.ufpe .cin.android.podcast.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Podcast::class), version = 1)
abstract class AppDatabase : RoomDatabase (){
    abstract fun getDao(): DAO
}
