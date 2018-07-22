package com.meteoro.kanamobitest.ui.repositories.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.kennyc.view.MultiStateView
import com.meteoro.kanamobitest.R
import com.meteoro.kanamobitest.application.MyApplication
import com.meteoro.kanamobitest.core.listeners.EndlessRecyclerViewScrollListener
import com.meteoro.kanamobitest.ui.repositories.di.DaggerRepositoriesComponent
import com.meteoro.kanamobitest.ui.repositories.di.RepositoriesModule
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryData
import com.meteoro.kanamobitest.ui.repositories.presentation.adapter.RepositoriesAdapter
import kotlinx.android.synthetic.main.activity_repositories.*
import javax.inject.Inject

class RepositoriesActivity : AppCompatActivity(), RepositoriesContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val repositoriesList by lazy {
        repositories_view
    }

    private val stateView by lazy {
        state_view
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Dependency Injection
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    lateinit var presenter: RepositoriesPresenter

    private var repositoriesListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        initializeToolbar()
        initializeInjection()
        initializeViews()
        initializeAdapter()
        initializeContents()
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun initializeInjection() {
        DaggerRepositoriesComponent
                .builder()
                .libraryComponent(MyApplication.component)
                .repositoriesModule(RepositoriesModule(this))
                .build()
                .inject(this)
    }

    private fun initializeViews() {
        val linearLayout = LinearLayoutManager(this)
        repositoriesListener = getRepositoriesListener(linearLayout)
        repositoriesList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
            addOnScrollListener(repositoriesListener)
        }
    }

    private fun initializeAdapter() {
        if (repositoriesList.adapter == null) {
            repositoriesList.adapter = RepositoriesAdapter()
        }
    }

    private fun initializeContents() {
        presenter.initializeContents()
    }

    private fun getRepositoriesListener(layoutManager: LinearLayoutManager): EndlessRecyclerViewScrollListener {
        return object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onRequestNextPage(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.d("RepositoryActivity", "Page ${page}")
            }
        }
    }

    override fun showLoading() {
        stateView.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun showData(data: RepositoryData) {
        stateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        (repositoriesList.adapter as RepositoriesAdapter).addRepositories(data.repositories)
    }

    override fun showError() {
        stateView.viewState = MultiStateView.VIEW_STATE_ERROR
    }
}