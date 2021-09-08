package com.mutualmobile.praxis.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.framework.RxApiService
import com.mutualmobile.praxis.usecases.GetJokesUseCase
import com.mutualmobile.praxis.utils.IRxSchedulers
import com.mutualmobile.praxis.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getJokesUseCase: GetJokesUseCase): ViewModel() {

    private var _jokesData = MutableLiveData<ResponseWrapper<Boolean>>(ResponseWrapper.Waiting())
    val jokesData : LiveData<ResponseWrapper<Boolean>>
        get() = _jokesData

    fun resetResponse(){ _jokesData.postValue(ResponseWrapper.Waiting()) }

    fun loadCoroutineData(){
        _jokesData.postValue(ResponseWrapper.Loading())
        viewModelScope.launch {
            try {

            }
            catch (e: Exception){ _jokesData.postValue(ResponseWrapper.Error("Something Went Wrong")) }
        }
    }



    @Inject lateinit var schedulers: IRxSchedulers
    @Inject lateinit var rxApiService: RxApiService
    private var compositeDisposable: CompositeDisposable? = null

    fun loadDataRx() {
        _jokesData.postValue(ResponseWrapper.Loading())
        addDisposable(rxApiService.getFiveRandomJokes()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.main())
            .subscribe({ response ->

            }, { Timber.e(it) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable != null) {
            compositeDisposable!!.dispose()
            compositeDisposable!!.clear()
            compositeDisposable = null
        }
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) { compositeDisposable = CompositeDisposable() }
        compositeDisposable!!.add(disposable)
    }

}