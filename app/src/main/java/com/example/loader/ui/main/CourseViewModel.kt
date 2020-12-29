package com.example.loader.ui.main

import android.view.View
import androidx.lifecycle.*
import com.example.loader.data.Repository
import com.example.loader.data.network.Api
import com.example.loader.models.CourseList
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse


class CourseViewModel(private val repository: Repository) : ViewModel() {
    var course = MutableLiveData<LinkedHashSet<CourseList>>()
    val dataLoading = MutableLiveData<Int>()

    fun load(){
        val job = viewModelScope.launch {
            dataLoading.value = View.VISIBLE
            loadData()
        }
        android.os.Handler().postDelayed({
            if (dataLoading.value == View.VISIBLE) {
                job.cancel()
                load()
            }
        }, 5000)
    }

    fun loadData(){
        dataLoading.value = View.VISIBLE
        viewModelScope.launch {
            val result = repository.loadData()
            if(result != null) {
                if (result.size > 0) {
                    dataLoading.value = View.GONE
                    course.value = result
                }
            }
            result?.clear()
        }

    }

}


@Suppress("UNCHECKED_CAST")
class CourseViewModelFactory(
        private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CourseViewModel(
                repository
        ) as T
    }
}



