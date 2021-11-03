package com.example.githubrepo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubrepo.App
import com.example.githubrepo.App.Companion.userRepo
import com.example.githubrepo.Contract
import com.example.githubrepo.databinding.UserProfileBinding
import com.example.githubrepo.interfaces.BackButtonListener
import com.example.githubrepo.interfaces.ProfileUserView
import com.example.githubrepo.loadImages.GlideImageLoader
import com.example.githubrepo.loadImages.ImageLoaderGlide
import com.example.githubrepo.reposRecycler.RepoPresenter
import com.example.githubrepo.reposRecycler.RetrofitGithubRepoRepo
import com.example.githubrepo.reposRecycler.UserRepoAdapter
import com.example.githubrepo.reposRecycler.UserRepoPresenter
import com.example.githubrepo.retrofit.IGithubUsersRepo
import com.example.githubrepo.retrofit.RetrofitGithubUserRepo
import com.example.githubrepo.retrofit.UserRetro
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ProfileUserFragment: MvpAppCompatFragment(), ProfileUserView, Contract.View,
    BackButtonListener {
    companion object {
        private const val MY_USER = "MY_USER_ID"
        private const val DEFAULT_INT = 0
        private const val DEFAULT_STRING = "0"

        fun newInstance(userId: Int) =
            ProfileUserFragment().apply { arguments = bundleOf(MY_USER to userId) }
        fun newInstance(login: String?) = ProfileUserFragment()
            .apply { arguments = bundleOf(MY_USER to login) }
    }

//    private val userId: Int? by lazy {
//        arguments?.getInt(MY_USER, DEFAULT_INT)
//    }

    private lateinit var adapter: UserRepoAdapter

    private val userString: String? by lazy {
        arguments?.getString(MY_USER, DEFAULT_STRING)
    }

    private var binding: UserProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = UserProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    val presenter: UserProfilePresenter by moxyPresenter {
        UserProfilePresenter(RetrofitGithubUserRepo(App.api), userString, App.router)
    }
    val presenterRepo: RepoPresenter by moxyPresenter {
        RepoPresenter(RetrofitGithubRepoRepo(App.api, userString!!), App.router)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun setProfileData(user: UserRetro) = with(binding!!) {
        Glide.with(requireContext())
            .load(user.avatarUrl)
            .into(userProfileImageView)

        loginProfileTextView.text = user.login
        idProfileTextView.text = user.id.toString()
        typeProfileTextView.text = user.type
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun init() {
        adapter = UserRepoAdapter(presenterRepo.repoListPresenter)
        binding?.usersRepoListRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.usersRepoListRecyclerView?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}

