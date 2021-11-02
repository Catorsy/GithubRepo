package com.example.githubrepo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.githubrepo.App
import com.example.githubrepo.App.Companion.userRepo
import com.example.githubrepo.databinding.UserProfileBinding
import com.example.githubrepo.interfaces.BackButtonListener
import com.example.githubrepo.interfaces.ProfileUserView
import com.example.githubrepo.retrofit.IGithubUsersRepo
import com.example.githubrepo.retrofit.RetrofitGithubUserRepo
import com.example.githubrepo.retrofit.UserRetro
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ProfileUserFragment : MvpAppCompatFragment(), ProfileUserView, BackButtonListener {
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

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun setProfileData(user: UserRetro) = with(binding!!) {
        loginProfileTextView.text = user.login
        //urlProfileTextView.text = user.url.toString()
        idProfileTextView.text = user.id.toString()
        //likeCounterTextView.text = user.likes.toString()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

