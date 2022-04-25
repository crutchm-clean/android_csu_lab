package com.example.lab1.UI

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.Adapter
import com.example.lab1.R
import com.example.lab1.UI.models.Item
import com.example.lab1.UI.models.ItemInfo
import com.example.lab1.UI.models.ItemTarif
import com.example.lab1.UI.models.ItemTitle
import com.example.lab1.network.models.Balance
import com.example.lab1.network.models.Tariff
import com.example.lab1.network.models.UserInfo
import com.example.lab1.network.retrofit.ApiProvider
import com.example.lab1.network.retrofit.RetrofitClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private var con = mutableListOf<Item>(
        ItemTitle(
            "Тариф"
        )
    )

    private val api = ApiProvider(RetrofitClient()).getApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
        setAdapter()
        adapter.submitList(con)
    }

    private fun load(){
        MainScope().launch {
            setTariffs(api.getTariffs())
        }
        MainScope().launch {
            setBalance(api.getBalance()[0] )
        }
        MainScope().launch {
            setUserInfo(api.getUserInfo()[0])
        }
    }

//    private fun load(){
//        MainScope().launch {
//            val tariffsCallback = object: Callback<List<Tariff>>{
//                override fun onResponse(call: Call<List<Tariff>>, response: Response<List<Tariff>>) {
//                    val tariffs = response.body() ?: onFailure(call, Exception())
//                    val items = (tariffs as List<Tariff>).map(::mapTariffToItemTariff)
//                    setTariffs(items)
//                }
//
//                override fun onFailure(call: Call<List<Tariff>>, t: Throwable) {
//                    makeToast()
//                }
//            }
//
//            val balanceCallback = object : Callback<List<Balance>>{
//                override fun onResponse(call: Call<List<Balance>>, response: Response<List<Balance>>) {
//                    val balance = response.body()?.get(0) ?: onFailure(call, Exception())
//                    val casted = balance as Balance
//                    setBalance(casted)
//                }
//
//                override fun onFailure(call: Call<List<Balance>>, t: Throwable) {
//                    makeToast()
//                }
//            }
//
//            val userCallback = object : Callback<List<UserInfo>>{
//                override fun onResponse( call: Call<List<UserInfo>>,  response: Response<List<UserInfo>> ) {
//                    val user = response.body()?.get(0) ?: onFailure(call, Exception())
//                    val cated = user as UserInfo
//                }
//
//                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
//                    makeToast()
//                }
//            }
//            api.getTariffs().enqueue(tariffsCallback)
//            api.getBalance().enqueue(balanceCallback)
//            api.getUserInfo().enqueue(userCallback)
//
//
//        }
//    }

    private fun setUserInfo(userInfo: UserInfo){
        con.add(
            ItemInfo(userInfo.firstName + ' ' + userInfo.lastName,
                AppCompatResources.getDrawable(applicationContext, R.drawable.account_circle)
            )
        )
        con.add(
            ItemInfo(
                userInfo.address,
                AppCompatResources.getDrawable(applicationContext, R.drawable.home_icon)
            )
        )
        con.add( ItemInfo(
            "Доступные услуги",
            AppCompatResources.getDrawable(applicationContext, R.drawable.widgets_icon)
        ))
    }

    private fun setTariffs(tariffs : List<Tariff>)=
        tariffs.forEach{
            con.add(mapTariffToItemTariff(it))
        }

    private fun setBalance(balance: Balance){
        findViewById<TextView>(R.id.bill_money).text = balance.balance.toString()
    }

    private fun makeToast(){
        Toast.makeText(this@MainActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
    }


    private fun mapTariffToItemTariff(tariff: Tariff) =
        ItemTarif(
            tariff.title,
            tariff.desc,
            tariff.cost.toString()
        )


    @SuppressLint("CutPasteId")
    private fun setAdapter() {
        adapter = Adapter()
        findViewById<RecyclerView>(R.id.RV).adapter = adapter
        findViewById<RecyclerView>(R.id.RV).layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}