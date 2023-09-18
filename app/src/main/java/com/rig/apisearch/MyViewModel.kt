package com.rig.apisearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rig.apisearch.responseData.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository):ViewModel() {
    var response:MutableLiveData<data?> = MutableLiveData()
    fun searchQuery(s:String){
        viewModelScope.launch(Dispatchers.IO) {
            var async_data = async {
                repository.searchQuery(s)
            }
            var await_data = async_data.await()
            response.postValue(await_data)
        }
    }

    fun searchPagination(s:String,page:Int=1,limit:Int=3){
        viewModelScope.launch(Dispatchers.IO) {
            var asyncData= async{
                repository.searchPagination(s,page,limit)
            }
            var await_data = asyncData.await()
            response.postValue(await_data)
        }
    }
}