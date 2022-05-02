package com.example.lab1.presentation.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.Adapter
import com.example.lab1.R
import com.example.lab1.presentation.UI.models.Item
import com.example.lab1.presentation.UI.models.ItemInfo
import com.example.lab1.presentation.UI.models.ItemTarif
import com.example.lab1.presentation.UI.models.ItemTitle
import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.network.retrofit.RetrofitClient
import com.example.lab1.presentation.App
import com.example.lab1.presentation.viewModels.AbstractViewModel
import com.example.lab1.presentation.viewModels.ViewModel
import com.example.lab1.presentation.viewModels.ViewModelFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<AbstractViewModel>{factory}

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
        inject()
        load()
        setAdapter()
        adapter.submitList(con)
    }

    private fun inject(){
        App.appComponent.inject(this)
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