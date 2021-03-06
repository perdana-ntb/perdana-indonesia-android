package app.perdana.indonesia.ui.applicant.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import app.perdana.indonesia.core.base.ApiResponseError
import app.perdana.indonesia.core.base.ApiResponseModel
import app.perdana.indonesia.data.repository.MemberApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive

/**
 * Created by ebysofyan on 12/24/19.
 */
class ApplicantDetailViewModel : ViewModel() {
    private val repository = MemberApiRepository.getInstance()

    private var job = SupervisorJob() + Dispatchers.IO

    private val loading = MutableLiveData<Boolean>()
    private val progressLoading = MutableLiveData<Pair<Boolean, String>>()

    fun setLoading(show: Boolean) {
        this.loading.value = show
    }

    fun getProgressLoading() = this.progressLoading

    fun showProgressLoading(state: Pair<Boolean, String>) {
        progressLoading.value = state
    }

    fun hideProgressLoading() {
        progressLoading.value = false to ""
    }

    fun getLoading() = loading


    fun cancel() {
        if (job.isActive) job.cancel()
    }
}