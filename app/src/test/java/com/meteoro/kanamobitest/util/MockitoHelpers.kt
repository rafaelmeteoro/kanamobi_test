package com.meteoro.kanamobitest.util

import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.verification.VerificationMode

class MockitoHelpers {
    companion object {
        fun oneTimeOnly(): VerificationMode {
            return VerificationModeFactory.times(1)
        }
    }
}