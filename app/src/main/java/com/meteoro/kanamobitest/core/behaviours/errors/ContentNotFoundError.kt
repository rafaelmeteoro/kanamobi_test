package com.meteoro.kanamobitest.core.behaviours.errors

class ContentNotFoundError : RuntimeException() {

    override val message: String?
        get() = "No content available"
}