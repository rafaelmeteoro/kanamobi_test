package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData

interface RepositoriesContract {
    interface View {
        fun showLoading()
        fun showData(data: RepositoryData)
        fun showError()
    }

    interface Presenter {
        fun initializeContents()
    }
}