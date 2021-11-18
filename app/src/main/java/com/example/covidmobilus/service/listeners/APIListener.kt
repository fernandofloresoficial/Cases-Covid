package com.example.covidmobilus.service.listeners

interface APIListener<T> {

    fun onSuccess(model: List<T>)

    fun onFailure(str: String)
}