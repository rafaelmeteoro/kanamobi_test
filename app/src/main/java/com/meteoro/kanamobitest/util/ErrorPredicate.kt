package com.meteoro.kanamobitest.util

interface ErrorPredicate {
    fun evaluate(error: Throwable): Boolean
}