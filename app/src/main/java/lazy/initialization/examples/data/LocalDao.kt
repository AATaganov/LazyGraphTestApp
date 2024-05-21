package lazy.initialization.examples.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import lazy.initialization.examples.data.entity.DBO

@Dao
internal interface LocalDao {

    @Query("SELECT * FROM dbo")
    suspend fun getAll(): List<DBO>

    @Query("DELETE FROM dbo")
    suspend fun deleteAll()

    @Update
    suspend fun update(dbo: DBO)
}
