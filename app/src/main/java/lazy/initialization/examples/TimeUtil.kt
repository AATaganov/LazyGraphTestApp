package lazy.initialization.examples

import android.os.SystemClock
import kotlin.reflect.KSuspendFunction0

/**
 * @author a.taganov
 */

fun nowNanos(): Long = SystemClock.elapsedRealtimeNanos()

suspend fun measureSuspend(block: KSuspendFunction0<Unit>) {
    val start = nowNanos()
    block()
    val end = nowNanos()
    println("${block.name} executed in: ${end - start}")
}

fun measure(name: String, block: () -> Any) {
    val start = nowNanos()
    block()
    val end = nowNanos()
    println("$name executed in: ${end - start}")
}
