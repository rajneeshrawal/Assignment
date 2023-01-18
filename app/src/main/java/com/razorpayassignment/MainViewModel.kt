package com.razorpayassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()
    val successLiveData = MutableLiveData<CustomUI?>()
    val logoLiveData = MutableLiveData<ByteArray?>()
    fun fetchCustomUI() {
        viewModelScope.launch(Dispatchers.IO) {
            val jsonObjectResp =
                mainRepository.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
            jsonObjectResp.let {
                if (it.length() != 0) {
                    val customUI = Gson().fromJson(it.toString(), CustomUI::class.java)
                    customUI.logo_url?.let { it1 -> fetchLogo(it1) }
                    successLiveData.postValue(customUI)
                }
            }
        }
    }

    private fun fetchLogo(logoUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            logoLiveData.postValue(mainRepository.fetchLogo(logoUrl))
        }
    }
}