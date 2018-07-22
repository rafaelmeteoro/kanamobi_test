package com.meteoro.kanamobitest.ui.repositories.presentation

interface RepositoriesContract {
    interface View {
        fun showLoading()
    }

    interface Presenter {
        fun initializeContents();
    }
}