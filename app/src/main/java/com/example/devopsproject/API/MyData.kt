package com.example.devopsproject.API
import com.google.gson.annotations.SerializedName

class MyData {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("password")
    val password: String? = null

    @SerializedName("username")
    val username: String? = null
}

class POST(val name: String, val username: String, val password: String)