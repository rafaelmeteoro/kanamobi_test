package com.meteoro.kanamobitest.ui.pullrequests.presentation.listener

import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.PullRequestClickData

interface OnPullRequestClickListener {
    fun onClick(data: PullRequestClickData)
}