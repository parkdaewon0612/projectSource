package com.example.video

import android.os.Handler
import android.os.Looper
import io.socket.engineio.client.Socket
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.*
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class ClientClass(hostAddress: InetAddress): Thread() {

    var hostAddress: String = hostAddress.hostAddress
    lateinit var inputStream: InputStream
    lateinit var outputStream: OutputStream
    var socket = Socket()
    var server = ServerSocket()
    var ip = ""
    var port = ""
    var mHandler = Handler(Looper.getMainLooper())
    var ServerClosed = true
    fun write(byteArray: ByteArray){
        try {
            outputStream.write(byteArray)
        }catch (ex: IOException){
            ex.printStackTrace()
        }
    }

    override fun run() {
        try {
            socket = Socket()
             socket.connect(InetSocketAddress(hostAddress,8888),500)
            inputStream = socket.getInputStream()
            outputStream = socket.getOutputStream()
        }catch (ex:IOException){
            ex.printStackTrace()
        }
        val executor = Executors.newSingleThreadExecutor()
        var handler = Handler(Looper.getMainLooper())

        executor.execute(kotlinx.coroutines.Runnable {
            kotlin.run {
                val buffer =ByteArray(1024)
                var byte:Int
                while (true){
                    try{
                        byte = inputStream.read(buffer)
                        if(byte>0){
                            val finalBytes = byte
                            handler.post(Runnable{
                                kotlin.run {
                                    val tmpMeassage = String(buffer,0,finalBytes)

                                    Log.i("client class", tmpMeassage)
                                }
                            })
                        }
                    }catch (ex:IOException){
                        ex.printStackTrace()
                    }
                }
            }
        })
    }

}