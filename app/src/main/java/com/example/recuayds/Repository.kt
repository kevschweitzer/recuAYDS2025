package com.example.recuayds

import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.concurrent.thread

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"


class Repository {

    var savedResult: List<Post> = listOf()

    fun getPosts(): List<Post> {
        CoroutineScope(Dispatchers.IO).launch { //Esto es como un thread nuevo
            delay(20000) //guardo el dato por 20 segundos
            savedResult = listOf()
        }

        if(savedResult.isNotEmpty()) {
            println(ANSI_RED + "Devuelvo de savedResult" + ANSI_RESET)
            return savedResult
        } else {
            println(ANSI_RED + "Busco en API" + ANSI_RESET)
            val url = "https://jsonplaceholder.typicode.com/posts"


            val text = URL(url).readText()


            val jsonArray = Gson().fromJson(text, JsonArray::class.java)


            val result = jsonArray.map { item ->
                val jobj = item.asJsonObject
                val id = jobj["id"].asInt
                val userId = jobj["userId"].asInt
                val title = jobj["title"].asString
                val body = jobj["body"].asString
                Post(id, userId, title, body)
            }
            savedResult = result
            return result
        }
    }
}