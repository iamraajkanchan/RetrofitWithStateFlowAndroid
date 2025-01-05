package com.chinky.localandroidapplication.utility

object Utility {
    private const val TAG: String = "ApplicationLog"
    fun <T> printLog(klass: Class<T>, element: StackTraceElement?, message: String) {
        element?.let {
            println("$TAG :: ${klass.simpleName} :: ${it.lineNumber} :: ${it.methodName} :: $message")
        }
    }
}