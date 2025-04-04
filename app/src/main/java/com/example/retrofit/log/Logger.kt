package com.example.retrofit.log

import android.util.Log

object Logger {
    private const val TAG = "MyAppLogger"

    private fun getCallerInfo(): String {
        val stackTrace = Thread.currentThread().stackTrace
        for (element in stackTrace) {
            if (!element.className.contains("Logger") && !element.className.contains("java.lang.Thread")) {
                return "(${element.fileName}:${element.lineNumber})"
            }
        }
        return "(Unknown Source)"
    }

    fun d(message: String) {
        val logMessage = "${getCallerInfo()} ➜ $message"
        Log.d(TAG, logMessage)
    }

    fun e(message: String) {
        val logMessage = "${getCallerInfo()} ➜ $message"
        Log.e(TAG, logMessage)
    }

    fun i(message: String) {
        val logMessage = "${getCallerInfo()} ➜ $message"
        Log.i(TAG, logMessage)
    }

    fun w(message: String) {
        val logMessage = "${getCallerInfo()} ➜ $message"
        Log.w(TAG, logMessage)
    }
}

