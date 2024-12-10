package com.example.video

import android.os.Handler
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket


class tcpThread(var handler: Handler) : Thread() {
    var dataInputStream: InputStream? = null
    var dataOutputStream: OutputStream? = null
    private var socket: Socket? = null
    var ip: String = "121.153.150.157"
    private val port = 9999
    val TAG: String = "TAG+Thread"

    override fun run() {
        try {
            Log.d(TAG, "접속")
            socket = Socket(ip, port)
            dataOutputStream = socket!!.getOutputStream()
            dataInputStream = socket!!.getInputStream()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer = ByteArray(1024)
        var bytes: Int
        var tmp = ""
        Log.d(TAG, "수신 시작")
        while (true) {
            try {
                Log.d(TAG, "수신 대기")
                bytes = dataInputStream!!.read(buffer)
                Log.d(TAG, "byte = $bytes")
                if (bytes > 0) {
                    tmp = String(buffer, 0, bytes)
                } else {
                    Log.d(TAG, "재접속")
                    socket = Socket(ip, port)
                    dataOutputStream = socket!!.getOutputStream()
                    dataInputStream = socket!!.getInputStream()
                }
                Log.d(TAG, tmp)
                handler.obtainMessage(0, tmp).sendToTarget()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(IOException::class)
    fun cctv_center() {
        val inst = "center".toByteArray()
        dataOutputStream!!.write(inst)
    } // cctv 중앙으로 명령

    @Throws(IOException::class)
    fun cctv_right() {
        val inst = "right".toByteArray()
        dataOutputStream!!.write(inst)
    } // cctv 오른쪽으로 명령

    @Throws(IOException::class)
    fun cctv_left() {
        val inst = "left".toByteArray()
        dataOutputStream!!.write(inst)
    } // cctv 왼쪽으로 명령

    @Throws(IOException::class)
    fun cctvOn() {
        val inst = "cctvOn".toByteArray()
        dataOutputStream!!.write(inst)
    } // CCTV On 명령

    @Throws(IOException::class)
    fun cctvOff() {
        val inst = "cctvOff".toByteArray()
        dataOutputStream!!.write(inst)
    } // CCTV Off 명령
} // Tcp 소켓통신을 담당하는 클래스

