package lazy.initialization.examples.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lazy.initialization.examples.data.entity.DBO

/**
 * @author a.taganov
 */
@Database(entities = [DBO::class], version = 1)
internal abstract class LocalDatabase : RoomDatabase() {
    abstract fun dao(): LocalDao
}

internal fun Context.createDao(dbName: String): LocalDao {
    return Room.databaseBuilder(
        this,
        LocalDatabase::class.java,
        dbName
    )
        .fallbackToDestructiveMigration()
        .build()
        .dao()
}
