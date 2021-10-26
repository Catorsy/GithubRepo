package com.example.githubrepo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepo.App
import com.example.githubrepo.Contract
import com.example.githubrepo.databinding.FragmentUsersListBinding
import com.example.githubrepo.interfaces.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(), Contract.View, BackButtonListener {
    companion object {
        fun newInstance() = UsersListFragment()
    }

    private var binding: FragmentUsersListBinding? = null
    private val presenter by moxyPresenter { UsersPresenter(App.userRepo, App.router) }
    private lateinit var adapter: UsersRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root


    override fun init() {
        adapter = UsersRecyclerViewAdapter(presenter.usersListPresenter)
        binding?.usersListRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.usersListRecyclerView?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}