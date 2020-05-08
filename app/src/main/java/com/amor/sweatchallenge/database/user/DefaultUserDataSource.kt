package com.amor.sweatchallenge.database.user

import android.service.autofill.UserData
import com.amor.sweatchallenge.util.subscribeOnDbAndObserveOnMainThread
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


class DefaultUserDataSource internal constructor(
    private val userDao: UserDao
) : UserDataSource{

    override fun observeUser(): Flowable<List<UserEntity>> {
        return userDao.observerUsers()
    }

    override fun saveUser(user: UserEntity): Completable {
        return Single.fromCallable { user }
            .subscribeOnDbAndObserveOnMainThread()
            .flatMapCompletable {
                Completable.fromAction { userDao.insertTask(it) }
            }
            .doOnError { error -> error.printStackTrace() }
    }

    override fun deleteUser(userId: String): Completable {
        return Single.fromCallable { userId }
            .subscribeOnDbAndObserveOnMainThread()
            .flatMapCompletable {
                Completable.fromAction { userDao.deleteUserById(it) }
            }
            .doOnError { error -> error.printStackTrace() }
    }

}