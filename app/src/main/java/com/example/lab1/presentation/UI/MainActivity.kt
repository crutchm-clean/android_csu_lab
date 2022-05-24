package com.example.lab1.presentation.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo
import com.example.lab1.presentation.App
import com.example.lab1.presentation.viewModels.AbstractViewModel
import com.example.lab1.presentation.viewModels.ViewModel
import com.example.lab1.presentation.viewModels.ViewModelFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private var firstIndex: Int = 1

    private val viewModel by viewModels<AbstractViewModel> { factory }
    private lateinit var adapter: Adapter


    private var con = mutableListOf<Item>(
        ItemTitle(
            "Тариф"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()

        load()
    }

    override fun onResume() {
        super.onResume()
        if (con.count() <= 1) {
            viewModel.refreshData()

        } else {
            var index = 1
            val count = con.count()
            while (index != count - 1) {
                con.removeAt(index)
                index++
            }
            viewModel.refreshData()
        }
    }

    private fun inject() {
        App.appComponent.inject(this)
    }

    private fun load() {
        controlProgressBar(true)
        viewModel.balance.observe(this) {
            setBalance(it)
        }
        viewModel.tariffs.observe(this) {
            setTariffs(it)
        }
        viewModel.userInfo.observe(this) {
            setUserInfo(it)
            controlProgressBar(false)
            setAdapter()
            adapter.submitList(con)
        }
    }


    private fun setUserInfo(userInfo: UserInfo) {
        con.add(
            ItemTitle("информация о пользователе")
        )
        con.add(
            ItemInfo(
                userInfo.firstName + ' ' + userInfo.lastName,
                AppCompatResources.getDrawable(applicationContext, R.drawable.account_circle)
            )
        )
        con.add(
            ItemInfo(
                userInfo.address,
                AppCompatResources.getDrawable(applicationContext, R.drawable.home_icon)
            )
        )
        con.add(
            ItemInfo(
                "Доступные услуги",
                AppCompatResources.getDrawable(applicationContext, R.drawable.widgets_icon)
            )
        )
    }

    private fun setTariffs(tariffs: List<Tariff>) {
        var index = 1
        val count = con.size
        if(count != 1) {
            while (index != count - 2) {
                con.removeAt(index)
                index++
            }
        }
        tariffs.forEach {
            con.add(mapTariffToItemTariff(it))
        }
    }

    private fun setBalance(balance: Balance) {
        findViewById<TextView>(R.id.bill_money).text = balance.balance.toString()
    }

    private fun makeToast() {
        Toast.makeText(this@MainActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
    }


    private fun mapTariffToItemTariff(tariff: Tariff): ItemTarif =
        ItemTarif(
            tariff.title,
            tariff.desc,
            tariff.cost.toString(),
            onClick = {
                Toast.makeText(this, "dgsdg", Toast.LENGTH_LONG).show()
                viewModel.delete(tariff.id)
            }
        )

    private fun controlProgressBar(isVisible: Boolean) {
        val pb = findViewById<ProgressBar>(R.id.pb)
        if (isVisible) {
            pb.visibility = View.VISIBLE
        } else {
            pb.visibility = View.INVISIBLE
        }
    }

    private fun setAdapter() {
        val rv = findViewById<RecyclerView>(R.id.RV)
        adapter = Adapter()
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}