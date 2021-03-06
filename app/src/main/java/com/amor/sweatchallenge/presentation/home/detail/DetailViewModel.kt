package com.amor.sweatchallenge.presentation.home.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.data.home.HomeRepository
import com.amor.sweatchallenge.util.NoOpDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class DetailViewModel internal constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private var userDisposable: Disposable = NoOpDisposable()
    private val tag = DetailViewModel::class.java.simpleName

    fun addFavoriteUser(profileData: ProfileData) {
        userDisposable.dispose()
        userDisposable = homeRepository.saveUser(profileData)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(tag, "User saved")
            }
    }

    fun deleteFavoriteUser(userId: String) {
        userDisposable.dispose()
        userDisposable = homeRepository.deleteUser(userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(tag, "User delete")
            }
    }

    override fun onCleared() {
        super.onCleared()

        userDisposable.dispose()
    }
}