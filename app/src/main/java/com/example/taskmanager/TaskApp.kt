package com.example.taskmanager

import android.app.Application

class TaskApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}