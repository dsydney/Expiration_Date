package com.revature.expiration_date.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.expiration_date.network.Items
import com.revature.expiration_date.network.Login
import com.revature.expiration_date.network.Product
import com.revature.expiration_date.network.RequestProduct
import com.revature.expiration_date.repository.RetrofitHelper
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
    private val productsRequestLiveData = MutableLiveData<Boolean>()
    private val removeRequestLiveData = MutableLiveData<Boolean>()
//    private val expiredRequestLiveData = MutableLiveData<Boolean>()
    private val addItemRequestLiveData = MutableLiveData<Boolean>()

    private lateinit var items: Items

    fun listOfAllProducts(location: String, choices: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.getAllProducts(RequestProduct(location, choices))

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        items = it
                        Log.d("All Products","API Call Successful")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("All Products","Response Error $it")
                        it.close()
                    }
                }
                productsRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("All Products", "Exception in Network: $ex")
            }
        }
    }

    fun removeProduct(item: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.removeItem(item)

                if (responseService.isSuccessful) {
                    responseService.body()?.let {
                        Log.d("Remove Product","API Call Successful")
                    }
                } else {
                    responseService.errorBody()?.let {
                        Log.d("Remove Product","Response Error $it")
                        it.close()
                    }
                }
                removeRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Remove Product", "Exception in Network: $ex")
            }
        }
    }

//    fun getExpiredProducts() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val authService = RetrofitHelper.getAPIService()
//                val responseService = authService.getExpiredProducts()
//
//                if (responseService.isSuccessful) {
//                    responseService.body()?.let {
//                        Log.d("Expired Products","API Call Successful")
//                        TODO("do something with incoming expired items")
//                    }
//                } else {
//                    responseService.errorBody()?.let {
//                        Log.d("Expired Products","Response Error $it")
//                        it.close()
//                    }
//                }
//                expiredRequestLiveData.postValue(responseService.isSuccessful)
//            } catch(ex: Exception) {
//                Log.d("Expired Products", "Exception in Network: $ex")
//            }
//        }
//    }

    fun product(item: String, expiration: String, category: String, location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val authService = RetrofitHelper.getAPIService()
                val responseService = authService.addProduct(Product(
                    item = item,
                    expiration = expiration,
                    category = category,
                    location = location
                ))

                if (responseService.isSuccessful) {
                    responseService.body()?.let { itemPosted ->
                        Log.d("Add Product", "Response sent $itemPosted")
                    }
                } else {
                    responseService.errorBody()?.let {error ->
                        Log.d("Add Product", "Response sent $error")

                        error.close()
                    }
                }
                addItemRequestLiveData.postValue(responseService.isSuccessful)
            } catch(ex: Exception) {
                Log.d("Add Product", "Exception in Networking $ex")
            }
        }
    }
}