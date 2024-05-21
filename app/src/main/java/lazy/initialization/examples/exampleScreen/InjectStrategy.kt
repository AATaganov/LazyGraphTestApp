package lazy.initialization.examples.exampleScreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author a.taganov
 */
@Parcelize
internal enum class InjectStrategy : Parcelable {
    SYNC,
    LAZY_VM,
    LAZY_GRAPH,
}
