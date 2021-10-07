package com.devartLab.task.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devartLab.task.model.HomeResponseModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class HomeViewModel : ViewModel() {
    val repo = HomeRepo()
    var homeList = MutableLiveData<List<HomeResponseModel>>()

    init {
        requestHomeData()
    }

    fun requestHomeData(){
        viewModelScope.async {
            repo.getHomeData().catch {
                Log.i("FlowError",it.message.toString())
            }.collect {
                homeList.value = it
            }
        }
    }
}