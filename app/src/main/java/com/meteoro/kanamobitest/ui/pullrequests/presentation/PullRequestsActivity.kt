package com.meteoro.kanamobitest.ui.pullrequests.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.meteoro.kanamobitest.R
import com.meteoro.kanamobitest.application.MyApplication
import com.meteoro.kanamobitest.ui.pullrequests.di.DaggerPullRequestsComponent
import com.meteoro.kanamobitest.ui.pullrequests.di.PullRequestsModule
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestData
import com.meteoro.kanamobitest.ui.pullrequests.presentation.adapter.PullRequestsAdapter
import com.meteoro.kanamobitest.ui.pullrequests.presentation.data.CallData
import kotlinx.android.synthetic.main.activity_pull_requests.*
import javax.inject.Inject

class PullRequestsActivity : AppCompatActivity(), PullRequestsContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val pullRequestsList by lazy {
        pullrequests_view
    }

    private val stateView by lazy {
        state_view
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Dependency Injection
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    lateinit var presenter: PullRequestsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

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
        DaggerPullRequestsComponent
                .builder()
                .libraryComponent(MyApplication.component)
                .pullRequestsModule(PullRequestsModule(this))
                .build()
                .inject(this)
    }

    private fun initializeViews() {
        val linearLayout = LinearLayoutManager(this)
        pullRequestsList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayout
        }

    }

    private fun initializeAdapter() {
        if (pullRequestsList.adapter == null) {
            pullRequestsList.adapter = PullRequestsAdapter()
        }
    }

    private fun initializeContents() {
        val user = intent.getStringExtra(KEY_USER)
        val repo = intent.getStringExtra(KEY_REPO)

        presenter.initializeContents(CallData(user, repo))
    }

    override fun showLoading() {
        stateView.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun showEmpty() {
        stateView.viewState = MultiStateView.VIEW_STATE_EMPTY
    }

    override fun showData(data: PullRequestData) {
        stateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        (pullRequestsList.adapter as PullRequestsAdapter).addPullRequests(data.pullRequests)
    }

    override fun showError() {
        stateView.viewState = MultiStateView.VIEW_STATE_ERROR
    }

    companion object {
        private val KEY_USER = "key_user"
        private val KEY_REPO = "key_repo"

        fun newIntent(context: Context, user: String, repository: String): Intent {
            val intent = Intent(context, PullRequestsActivity::class.java)
            intent.putExtra(KEY_USER, user)
            intent.putExtra(KEY_REPO, repository)
            return intent
        }
    }
}