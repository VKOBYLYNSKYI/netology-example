package com.example.loader.data

import com.example.loader.data.network.RetrofitClient
import com.example.loader.models.CourseList
import com.example.loader.models.bodies.CourseInfo
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository(
        private val retrofitClient: RetrofitClient
) {
    var mainList : LinkedHashSet<CourseList>? = linkedSetOf()
    var infoList: MutableList<CourseInfo>? = arrayListOf()

    suspend fun loadData() : LinkedHashSet<CourseList>? {
        return try {
            val result = retrofitClient.api.getData()
                val res = result.enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val stringResponse = response.body()?.string()
                            val data: JSONArray = JSONObject(stringResponse).getJSONArray("data")

                            for (i in 0 until data.length()) {
                                val groups: JSONArray = JSONObject(data.get(i).toString()).getJSONArray("groups")
                                if (groups.length() >= 4) {
                                    for (j in 0 until groups.length()) {
                                        val title = groups.getJSONObject(j)
                                        val items: JSONArray = JSONObject(groups.get(j).toString()).getJSONArray("items")
                                        for (k in 0 until items.length()) {
                                            val obj: JSONObject = items.getJSONObject(k)
                                            infoList?.add(CourseInfo(obj.getString("title")))
                                        }
                                        mainList?.add(CourseList(title.getString("title"), infoList, items.length()))
                                        infoList?.clear()
                                    }
                                }
                            }
                        }
                    }
                })
            val set: LinkedHashSet<CourseList> = LinkedHashSet(mainList)
            set
        }catch (e: Exception){
            null
        }

    }

}