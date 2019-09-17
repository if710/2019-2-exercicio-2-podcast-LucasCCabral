package br.ufpe.cin.android.podcast.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    @Query("SELECT * FROM podcast")
    fun getAll(): List<Podcast>

    @Insert
    fun insertAll(vararg podcasts: Podcast)

}
