package com.meteoro.kanamobitest.ui.repositories.presentation

interface RepositoriesContract {
    interface View
    interface Presenter {
        fun initializeContents();
    }
}