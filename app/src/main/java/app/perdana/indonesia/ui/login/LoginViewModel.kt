package app.perdana.indonesia.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import app.perdana.indonesia.core.base.ApiResponseModel
import app.perdana.indonesia.core.extension.getErrorDetail
import app.perdana.indonesia.data.remote.model.LoginRequest
import app.perdana.indonesia.data.repository.ArcherApiRepository
import com.google.gson.Gson

/**
 * Created by ebysofyan on 12/13/19.
 */
class LoginViewModel : ViewModel() {
    private val userApiRepository = ArcherApiRepository.getInstance()

    private val loading = MutableLiveData<Boolean>()

    fun showLoading(value: Boolean) {
        loading.value = value
    }

    fun getLoading() = this.loading

    fun login(loginRequest: LoginRequest) = liveData {
        try {
            val response = userApiRepository?.login(loginRequest)
            when (response?.isSuccessful) {
                true -> emit(ApiResponseModel.Success(response.body()))
                else -> emit(
                    ApiResponseModel.Failure(
                        response?.code(),
                        response?.errorBody()?.getErrorDetail().toString()
                    )
                )
            }
        } catch (e: Exception) {
            emit(ApiResponseModel.Error(e))
        }
    }
}