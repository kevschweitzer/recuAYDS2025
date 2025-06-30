package com.example.recuayds

import ayds.observer.Subject

class Model {
    val observer = Subject<List<Post>>()
    val repository = Repository()

    //Method to get the posts
    fun getAllPosts() {
        print("Estoy en all posts")
        val result = repository.getPosts()

        observer.notify(result)




    }

    fun getPostsByUserId(userId: Int) {
        val r = repository.getPosts()
        //Filter the posts by user id before returning
        val r2 = r.filter { it.userId == userId }
        observer.notify(r2)
    }
}