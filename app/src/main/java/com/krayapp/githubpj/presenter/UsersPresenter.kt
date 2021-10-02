package com.krayapp.githubpj.presenter

import com.github.terrakok.cicerone.Router
import com.krayapp.githubpj.model.gituserinfo.GitLocalRepo
import com.krayapp.githubpj.model.gituserinfo.GithubUser
import com.krayapp.githubpj.ui.IScreens
import com.krayapp.githubpj.ui.userList.UsersListView
import com.krayapp.gitproject.data.retrofit2.IGithubUsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersListView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        disposables.add(
            users
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ usersList -> viewState.showUsers(usersList) },
                    { println(Throwable("Error in Data Stream")) })
        )
    }

    fun displayUser(user: GithubUser) {
        router.navigateTo(screens.openedUsers(user))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}