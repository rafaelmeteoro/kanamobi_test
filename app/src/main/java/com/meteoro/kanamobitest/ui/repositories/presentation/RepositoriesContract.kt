package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData

interface RepositoriesContract {
    interface View {
        fun showLoading()
        fun showLoadingMore()
        fun showEmpty()
        fun showData(data: RepositoryData)
        fun showError()
        fun showErrorToast()
    }

    interface Presenter {
        fun initializeContents()
        fun getMoreItems(page: Int)
    }
}