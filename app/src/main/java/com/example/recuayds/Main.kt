package com.example.recuayds

import ayds.observer.Subject

sealed class UIEvent {
    class QueryUserId(val userId: Int): UIEvent()
    data object QueryAll : UIEvent()
}

class MainClass {

    lateinit var middleClass: MiddleClass
    val observer = Subject<UIEvent>()

    fun setDependency(middleClass: MiddleClass) {
        this.middleClass = middleClass
        middleClass.model.observer.subscribe { posts ->
            posts.forEach { println(it) }
        }
    }

    fun initView() {
        println("Ingresa 0 para consultar todos los posts y cualquier otro numero para consultar por userId (del 1 al 10)")
        val option = readln().toInt()

        if(option == 0) {
            observer.notify(UIEvent.QueryAll)
        } else {
            println("Ingresa el id de usuario para consultar sus posts")
            val userId = readln().toInt()

            observer.notify(UIEvent.QueryUserId(userId))
        }
        initView()
    }
}

//Tip: Para el cambio de MVC a MVP solo tienen que cambiar este main para que inicialice MainClass en vez de MiddleClass.
//El resto va en las clases correspondientes
fun main() {
   MiddleClass()
}