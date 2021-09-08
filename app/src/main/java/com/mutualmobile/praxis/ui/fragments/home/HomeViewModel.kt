package com.mutualmobile.praxis.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutualmobile.praxis.domain.JokesResponse
import com.mutualmobile.praxis.domain.NetworkResult
import com.mutualmobile.praxis.framework.RxApiService
import com.mutualmobile.praxis.usecases.GetJokesUseCase
import com.mutualmobile.praxis.utils.IRxSchedulers
import com.mutualmobile.praxis.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getJokesUseCase: GetJokesUseCase): ViewModel() {

    private var _jokesData = MutableLiveData<ResponseWrapper<JokesResponse>>(ResponseWrapper.Waiting())
    val jokesData : LiveData<ResponseWrapper<JokesResponse>>
        get() = _jokesData

    fun resetResponse(){ _jokesData.postValue(ResponseWrapper.Waiting()) }

    fun loadCoroutineData() = viewModelScope.launch {
        _jokesData.postValue(ResponseWrapper.Loading())
        try {
            val response = withContext(Dispatchers.IO) { getJokesUseCase() }
            _jokesData.postValue(handleResponse(response))
        } catch (e: Exception) {
            _jokesData.postValue(ResponseWrapper.Error("Something Went Wrong"))
        }
    }

    private fun handleResponse(response: NetworkResult<JokesResponse?>): ResponseWrapper<JokesResponse>{
        return when(response){
            is NetworkResult.Success->{
                if(response.data != null) ResponseWrapper.Success(response.data!!)
                else ResponseWrapper.Error("No Data Found")
            }
            is NetworkResult.Error->{
                ResponseWrapper.Error(response.message?:"Something Went Wrong")
            }
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
                if(response.value.isNullOrEmpty()){ _jokesData.postValue(ResponseWrapper.Error("No Data Found")) }
                else{ _jokesData.postValue(ResponseWrapper.Success(response) ) }
            }, {
                _jokesData.postValue(ResponseWrapper.Error(it.message?:"Something went wrong"))
                Timber.e(it)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.let {
            it.dispose()
            it.clear()
        }
        compositeDisposable = null
    }

    private fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) { compositeDisposable = CompositeDisposable() }
        compositeDisposable!!.add(disposable)
    }

}