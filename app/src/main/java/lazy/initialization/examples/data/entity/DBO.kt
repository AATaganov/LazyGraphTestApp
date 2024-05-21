package lazy.initialization.examples.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dbo")
internal class DBO(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
)
