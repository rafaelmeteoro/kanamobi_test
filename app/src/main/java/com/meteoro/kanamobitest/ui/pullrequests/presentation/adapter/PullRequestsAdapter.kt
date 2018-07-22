package com.meteoro.kanamobitest.ui.pullrequests.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.meteoro.kanamobitest.R
import com.meteoro.kanamobitest.core.extensions.inflate
import com.meteoro.kanamobitest.core.extensions.loadImage
import com.meteoro.kanamobitest.ui.pullrequests.domain.model.PullRequestItem
import kotlinx.android.synthetic.main.pull_request_item.view.*

class PullRequestsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<PullRequestItem> = ArrayList()

    fun addPullRequests(pullRequests: List<PullRequestItem>) {
        val initPosition = items.size - 1

        items.addAll(pullRequests)
        notifyItemRangeChanged(initPosition, items.size)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PullRequestsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as PullRequestsViewHolder
        holder.bind(items[position])
    }

    class PullRequestsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.pull_request_item)) {

        fun bind(item: PullRequestItem) = with(itemView) {
            iv_avatar.loadImage(item.avatarUrl)
            tv_username.text = item.user
            tv_title.text = item.title
            tv_body.text = item.body
            tv_date.text = item.createdAt
        }
    }
}