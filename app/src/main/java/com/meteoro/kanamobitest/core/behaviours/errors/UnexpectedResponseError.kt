package com.meteoro.kanamobitest.core.behaviours.errors

class UnexpectedResponseError(override val message: String) : RuntimeException(message)