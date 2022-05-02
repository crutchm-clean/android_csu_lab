package com.example.lab1.data.network.retrofit

class ApiProvider(private val client: RetrofitClient) {
    private val baseUrl = "https://61f5894b62f1e300173c41ba.mockapi.io/"

    fun getApi(): Api =
        client.getClient(baseUrl)
            .create(Api::class.java)
}