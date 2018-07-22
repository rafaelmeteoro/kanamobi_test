package com.meteoro.kanamobitest.ui.repositories.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.meteoro.kanamobitest.R
import com.meteoro.kanamobitest.core.extensions.inflate
import com.meteoro.kanamobitest.core.extensions.loadImage
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryItem
import com.meteoro.kanamobitest.ui.repositories.presentation.data.RepositoryClickData
import com.meteoro.kanamobitest.ui.repositories.presentation.listener.OnRepositoryClickListener
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<RepositoryItem> = ArrayList()

    private var listener: OnRepositoryClickListener? = null

    fun addRepositories(repositories: List<RepositoryItem>) {

        val initPosition = items.size - 1

        items.addAll(repositories)
        notifyItemRangeChanged(initPosition, items.size)
    }

    fun setListener(listener: OnRepositoryClickListener) {
        this.listener = listener
    }

    private fun callOnRepositoryClickListenerIfNotNull(item: RepositoryItem) {
        listener?.onClick(RepositoryClickData(item.user ?: "", item.nameRepository ?: ""))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepositoriesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RepositoriesViewHolder
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            callOnRepositoryClickListenerIfNotNull(items[position])
        }
    }

    class RepositoriesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.repository_item)) {

        fun bind(item: RepositoryItem) = with(itemView) {
            iv_avatar.loadImage(item.avatarUrl)
            tv_username.text = item.user
            tv_repository_name.text = item.nameRepository
            tv_description.text = item.description
            tv_fork_count.text = item.forksCount.toString()
            tv_star_count.text = item.starCount.toString()
        }
    }
}