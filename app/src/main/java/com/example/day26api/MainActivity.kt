package com.example.day26api

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var myAdapter :  ProductAdapter
    lateinit var shimmerFrameLayout: ShimmerFrameLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        shimmerFrameLayout=findViewById(R.id.shimmer)


        // made an builder
        val retroFitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())  // we are using GSON here
            .build()
            .create(ApiInterface::class.java) // we are adding interface here

        // we are getting data  // which is present in interface data present in -> (products)
        val retrofitData = retroFitBuilder.getProductData()


        retrofitData.enqueue(object : Callback<MyData?> {
        // CTRL + SHIFT + SPACE

            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // If API Call is a Success , Then Use The Data of API and Show Your Data in your App (Do whatever With Data)

                val responseBody = response.body()
                val productList = responseBody?.products!!         // ?. if aage ki body empty hain toh aage ka code run nhi hoga
                // !! this is used for if product list is not empty (not null)

                myAdapter = ProductAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.setVisibility(View.GONE)
                recyclerView.setVisibility(View.VISIBLE)

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //If API Call is a Failed ,
                Log.d("Main Activity ", "onFailure" + t.message)

            }
        })

//


    }
}