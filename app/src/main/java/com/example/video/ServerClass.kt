package com.example.video

import io.socket.engineio.client.Socket
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.util.logging.Handler

class ServerClass() : Thread() {
    lateinit var serverSocket: ServerSocket
    lateinit var inputStream: InputStream
    lateinit var  outputStream: OutputStream
    lateinit var socket: Socket
    lateinit var IOException : IOException
    lateinit var handler: Handler

    override fun run() {
        try {
            serverSocket = ServerSocket(8888)
            socket = serverSocket.accept()
            inputStream = socket.getInputStream()
            outputStream = socket.getOutputStream()
        }catch (ex:IOException){
            ex.printStackTrace()
        }

        val executors = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executors.execute(Runnable{
            kotlin.run {
                val buffer = ByteArray(1024)
                var byte:Int
                while (true){
                    try {
                        byte =  inputStream.read(buffer)
                        if(byte > 0){
                            var finalByte = byte
                            handler.post(Runnable{
                                kotlin.run {
                                    var tmpMeassage = String(buffer,0,finalByte)

                                    Log.i("Server class","$tmpMeassage")
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

    fun write(byteArray: ByteArray){
        try {
            Log.i("Server write","$byteArray sending")
            outputStream.write(byteArray)
        }catch (ex:IOException){
            ex.printStackTrace()
        }
    }
}