package com.krayapp.githubpj.ui

import com.github.terrakok.cicerone.Screen
import com.krayapp.githubpj.model.gituserinfo.GitUserRepos
import com.krayapp.githubpj.model.gituserinfo.GithubUser

interface IScreens {
    fun users(): Screen
    fun openedUsers(user: GithubUser): Screen
    fun aboutUserRepo(userLogin:String, repo: GitUserRepos): Screen

}