package com.hisaki.retrofitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hisaki.retrofitexample.ui.theme.RetrofitExampleTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitExampleTheme {
        Greeting("Android")
    }
}

fun main() {
    // Membuat instance Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/") // Ganti dengan URL basis API yang sesuai
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Membuat instance dari ApiService
    val service = retrofit.create(ApiService::class.java)

    // Melakukan permintaan GET
    val call = service.getData()
    call.enqueue(object : Callback<MyData> {
        override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
            if (response.isSuccessful) {
                val data = response.body()
                println("Data: $data")
            } else {
                println("Failed to get data. Response code: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<MyData>, t: Throwable) {
            println("Error: ${t.message}")
        }
    })
}