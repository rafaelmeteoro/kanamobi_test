package com.meteoro.kanamobitest.ui.repositories.presentation

import com.meteoro.kanamobitest.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.kanamobitest.ui.repositories.presentation.coordinator.GetRepositoriesCoordinator
import javax.inject.Inject

class RepositoriesPresenter @Inject constructor(
        private val getRepositoriesCoordinator: GetRepositoriesCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber
) : RepositoriesContract.Presenter {

    override fun initializeContents() {

    }
}