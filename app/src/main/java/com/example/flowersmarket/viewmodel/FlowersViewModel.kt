package com.example.flowersmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flowersmarket.model.FlowersResponseItem
import com.example.flowersmarket.repository.FlowerRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


class FlowersViewModel : ViewModel() { //MUST EXTEND ViewModel to use viewModelScope.laucnh

    //1.Access Repository object
    //2. Use Livedata
    //3. Coroutines

    //App can be in 3 states: Loading, Sucess, Failure
    //----------------------------------------------------------------------------

    //TODO: HILT DI
    private val repository = FlowerRepository()


    //----------------------------------------------------------------------------
    //SUCCESS
    private val _flowers = MutableLiveData<List<FlowersResponseItem>>() //mutable
    val flowers: LiveData<List<FlowersResponseItem>> = _flowers //immutable

    //LOADING...
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    //ERROR
    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    //----------------------------------------------------------------------------

    fun fetchFlowers(){
        viewModelScope.launch {
            _loading.value = true //Loading dialog is displayed
            repository.getFlowers()
                .onSuccess { flowers ->
                    _flowers.value = flowers
                    _loading.value = false //Dismiss loading dialog
                }
                .onFailure { exception ->
                    _error.value = exception.message
                    _loading.value = false
                }

        }
    }

}