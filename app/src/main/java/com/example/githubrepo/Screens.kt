package com.example.githubrepo

import com.example.githubrepo.ui.ProfileUserFragment
import com.example.githubrepo.ui.UsersListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun usersScreen() = FragmentScreen { UsersListFragment.newInstance() }
    fun userProfileScreen(userId: Int) = FragmentScreen { ProfileUserFragment.newInstance(userId) }
}