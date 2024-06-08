package org.company.app.utils

import platform.Network.*
import platform.Foundation.*

actual class NetworkUtils {
    actual fun isNetworkAvailable(): Boolean {
        /*val monitor = NWPathMonitor()
        val queue = dispatch_queue_create("NetworkMonitor", null)
        var isAvailable = false

        monitor.setPathUpdateHandler { path ->
            isAvailable = when {
                path.status == NWPathStatusSatisfied -> true
                else -> false
            }
        }

        monitor.start(queue)
        // Give some time for the monitor to start and check the status
        NSThread.sleepForTimeInterval(1.0)
        monitor.cancel()

        return isAvailable*/
        return true
    }
}