package com.meteoro.kanamobitest.ui.pullrequests.domain.model

data class PullRequestData(
        val pullRequests: List<PullRequestItem>
)

data class PullRequestItem(
        val user: String?,
        val avatarUrl: String?,
        val title: String?,
        val body: String?,
        val createdAt: String?,
        val htmlUrl: String?
)