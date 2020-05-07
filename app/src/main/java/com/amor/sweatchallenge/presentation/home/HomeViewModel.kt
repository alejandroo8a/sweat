package com.amor.sweatchallenge.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.data.home.HomeRepository
import com.amor.sweatchallenge.util.NoOpDisposable
import com.amor.sweatchallenge.util.subscribeOnIoAndObserveOnMainThread
import io.reactivex.disposables.Disposable

class HomeViewModel constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private var profilePicturesDisposable: Disposable = NoOpDisposable()

    private val _profilePicturesResult = MutableLiveData<GenericData<ArrayList<ProfileData>>>()
    val profilePicturesResult: LiveData<GenericData<ArrayList<ProfileData>>>
        get() = _profilePicturesResult

    fun showProfilesPictures(page: Int) {
        profilePicturesDisposable.dispose()
        profilePicturesDisposable = homeRepository.getProfilesPictures(page)
            .subscribeOnIoAndObserveOnMainThread()
            .subscribe { result ->
                _profilePicturesResult.value = result
            }
    }

    override fun onCleared() {
        super.onCleared()

        profilePicturesDisposable.dispose()
    }
}