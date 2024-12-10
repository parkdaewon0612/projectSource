package com.example.video

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.media.Image
import android.widget.MediaController
import com.example.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var alert: ImageButton
    private lateinit var police_call: Button
    private lateinit var back : ImageButton
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var mWebView: WebView? = null
    private var mWebSettings: WebSettings? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alert=findViewById(R.id.alert1)
        police_call=findViewById(R.id.police)
        back=findViewById(R.id.back)
        mWebView = findViewById<View>(R.id.videoView) as WebView

        mWebView!!.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebSettings = mWebView!!.settings //세부 세팅 등록
        mWebSettings!!.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        mWebSettings!!.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings!!.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings!!.loadWithOverviewMode = true // 메타태그 허용 여부
        mWebSettings!!.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        mWebSettings!!.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings!!.builtInZoomControls = false // 화면 확대 축소 허용 여부
        mWebSettings!!.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        mWebSettings!!.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings!!.domStorageEnabled = true // 로컬저장소 허용 여부

        mWebView!!.loadUrl("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        //동영상으로 받고 라즈베리에 고정 ip부여 가능할때 loadData형식으로 바꾸기 loadData mWebView!!.loadData(myHtmlString, "text/html; charset=UTF-8", null);


        fun moveToAnotherPage(){
            val intent = Intent(this,MainActivity2::class.java)//다른 페이지 입력하면 클래스 주소 넣기
            startActivity(intent)
        }

        alert.setOnClickListener{
            val phoneNumber = "112" //경찰 신고
        }
        police_call.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.119.go.kr/Center119/regist.do")) //주소를 119에 가기
            startActivity(intent)
        }
        back.setOnClickListener{
            moveToAnotherPage()
        }


    }



}