package com.example.githubrepo.reposRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepo.databinding.RepoItemBinding

class UserRepoAdapter (private  val presenter: UserRepoPresenterInterface):
    RecyclerView.Adapter<UserRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoAdapter.ViewHolder {
        return ViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(val vb: RepoItemBinding) : RecyclerView.ViewHolder(vb.root),
        RepoView {
        override fun setRepo(repo: Repo) {
            vb.repoNameTextView.text = repo.name
            vb.repoLanguageTextView.text = repo.language
        }

        override var pos: Int = -1
    }
}