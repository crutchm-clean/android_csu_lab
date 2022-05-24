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
import com.example.lab1.presentation.UI.models.ItemInfo
import com.example.lab1.presentation.UI.models.ItemTarif
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo
import com.example.lab1.presentation.App
import com.example.lab1.presentation.UI.models.Item
import com.example.lab1.presentation.viewModels.AbstractViewModel
import com.example.lab1.presentation.viewModels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<AbstractViewModel> { factory }
    private lateinit var adapter: Adapter
    private lateinit var sAdapter: Adapter
    private var tarriffs = mutableListOf<Item>()
    private var users = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        load()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()

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
            adapter.submitList(tarriffs)
            sAdapter.submitList(users)
        }
    }


    private fun setUserInfo(userInfo: UserInfo) {
        users = mutableListOf()
        users.add(
            ItemInfo(
                userInfo.firstName + ' ' + userInfo.lastName,
                AppCompatResources.getDrawable(applicationContext, R.drawable.account_circle)
            )
        )
        users.add(
            ItemInfo(
                userInfo.address,
                AppCompatResources.getDrawable(applicationContext, R.drawable.home_icon)
            )
        )
        users.add(
            ItemInfo(
                "Доступные услуги",
                AppCompatResources.getDrawable(applicationContext, R.drawable.widgets_icon)
            )
        )
    }

    private fun setTariffs(tariffs: List<Tariff>) {
        tarriffs = mutableListOf()
        tariffs.forEach {
            this.tarriffs.add(mapTariffToItemTariff(it))
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
        val srv = findViewById<RecyclerView>(R.id.RV2)
        adapter = Adapter()
        sAdapter = Adapter()
        rv.adapter = adapter
        srv.adapter = sAdapter
        rv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        srv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}