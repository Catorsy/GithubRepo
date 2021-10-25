package com.example.githubrepo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepo.databinding.UserItemBinding
import com.example.githubrepo.interfaces.UserItemView
import com.example.githubrepo.interfaces.UserListPresenter

//адаптер делаем пассивным, а не активным
//он не имеет ссылок на данные и делегирует процесс наполнения View в Presenter
class UsersRecyclerViewAdapter(private val presenter: UserListPresenter) :
    RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this) //invoke вызывет лямбду. Еще он может быть null
                //presenter.itemClickListener?.invoke(holder) вызовет itemClickListener, если он не равен null.
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    inner class ViewHolder(val vb: UserItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override fun setLogin(text: String) {
            vb.userLoginTextView.text = text
        }

        override fun setUrl(text: String) {
            vb.userUrlTextView.text = text
        }

        override var pos: Int = -1
    }
}