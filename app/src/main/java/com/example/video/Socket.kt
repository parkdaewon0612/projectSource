package com.example.video

import android.content.Context
import android.os.Handler
import android.net.ConnectivityManager
import android.net.VpnManager
import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.*
import java.net.Socket
import kotlin.properties.Delegates


class Socket : AppCompatActivity() {

    companion object {
        var socket = Socket()
        var server = ServerSocket()
        lateinit var writerSocket: DataOutputStream
        lateinit var writerStream: DataInputStream
        lateinit var cManager: ConnectivityManager
        lateinit var myIp: String

        var ip = "여기에 ip를 입력"
        var port = "  "//port 입력시 ""지우기
        var mHandler = Handler(Looper.getMainLooper())
        var ServerClose = true

        var cList = mutableListOf<Client>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}