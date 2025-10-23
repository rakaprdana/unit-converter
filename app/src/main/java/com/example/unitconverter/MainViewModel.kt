package com.example.unitconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _input = MutableLiveData("0")
    val input: LiveData<String> =_input

    fun setInput(value: String){
        if (_input.value == "0"){
            _input.value = value
        } else{
            _input.value += value
        }
    }

    fun clearInput(){
        _input.value = if (_input.value?.length == 1){
            "0"
        } else{
            _input.value?.dropLast(1)
        }
    }
}