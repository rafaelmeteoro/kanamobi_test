package com.meteoro.kanamobitest.ui.pullrequests.presentation

import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData

interface PullRequestsContract {
    interface View {
        fun showLoading()
    }

    interface Presenter {
        fun initializeContents(data: CallData)
    }
}