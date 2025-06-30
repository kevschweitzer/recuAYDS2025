package com.example.recuayds

class MiddleClass {

    val view = MainClass()
    val model = Model()

    init {
        view.observer.subscribe {
            when(it) {
                is UIEvent.QueryAll -> model.getAllPosts()
                is UIEvent.QueryUserId ->  model.getPostsByUserId(it.userId)
            }
        }
        view.setDependency(this)
        initView()
    }

    fun initView() {
        view.initView()
    }
}