package com.meteoro.kanamobitest.new_core.behaviours.erros

class ContentNotFoundError : RuntimeException() {

    override val message: String?
        get() = "No content available"
}