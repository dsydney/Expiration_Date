package com.revature.expiration_date.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.expiration_date.network.Login
import com.revature.expiration_date.network.Product
import com.revature.expiration_date.network.RequestProduct
import com.revature.expiration_date.network.repository.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel: ViewModel() {
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.getLogin(Login(email, password))

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        Log.d("LogIn","Response Token ${it.accessToken}")
                        TODO("Do something with response (eg receive token -> successful login")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("LogIn","Response Error $it")
                        it.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Login", "Exception in Network: $ex")
            }
        }
    }
}

class ProductsViewModel: ViewModel() {
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun listOfAllProducts(location: String, choices: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.getAllProducts(RequestProduct(location, choices))

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        Log.d("All Products","API Call Successful")
                        TODO("Take incoming items and do something")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("LogIn","Response Error $it")
                        it.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Login", "Exception in Network: $ex")
            }
        }
    }
}

class RemoveViewModel: ViewModel() {
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun removeProduct(item: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.removeItem(item)

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        Log.d("All Products","API Call Successful")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("LogIn","Response Error $it")
                        it.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Login", "Exception in Network: $ex")
            }
        }
    }
}

class ExpiredViewModel: ViewModel() {
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun getExpiredProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.getExpiredProducts()

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        Log.d("All Products","API Call Successful")
                        TODO("do something with incoming expired items")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("LogIn","Response Error $it")
                        it.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Login", "Exception in Network: $ex")
            }
        }
    }
}