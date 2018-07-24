package com.meteoro.kanamobitest.ui.repositories.domain.model

import com.meteoro.kanamobitest.core.adapter.AdapterConstants
import com.meteoro.kanamobitest.core.adapter.ViewType

data class RepositoryData(
        val repositories: List<RepositoryItem>
)

data class RepositoryItem(
        val user: String?,
        val avatarUrl: String?,
        val nameRepository: String?,
        val description: String?,
        val starCount: Int?,
        val forksCount: Int?
) : ViewType {
    override fun getViewType() = AdapterConstants.ITEM
}