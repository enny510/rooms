package com.example.rooms

inline fun saveInvoke(action: () -> Unit){
    try {
        action.invoke()
    }
    catch (e: Throwable){
        e.printStackTrace()
    }
}