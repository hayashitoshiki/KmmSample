package com.myapp.test

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}