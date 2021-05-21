package eu.tutorials.tugasakhiradc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.tugasakhiradc.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var wallpaperAdapter: WallpaperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView(){
        wallpaperAdapter = WallpaperAdapter(arrayListOf(), object : WallpaperAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
                startActivity(
                    Intent(applicationContext, DetailActivity::class.java)
                        .putExtra("title", result.title)
                        .putExtra("image", result.image)
                )
            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = wallpaperAdapter
        }
    }

    private fun getDataFromApi(){

        progressBar.visibility = View.VISIBLE

        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel>{
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    progressBar.visibility = View.GONE
                    if(response.isSuccessful){
                        showData(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    printLog("onFailure: $t")
                }

            })

    }


    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel) {
        val results = data.result
        wallpaperAdapter.setData(results)
//        for (result in results){
//            printLog("title : ${result.title}")
//        }
    }

}