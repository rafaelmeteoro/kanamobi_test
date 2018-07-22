package com.meteoro.kanamobitest.ui.pullrequests.presentation

import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData

interface PullRequestsContract {
    interface View {
        fun showLoading()
        fun showEmpty()
        fun showData(data: PullRequestData)
        fun showError()
    }

    interface Presenter {
        fun initializeContents(data: CallData)
    }
}