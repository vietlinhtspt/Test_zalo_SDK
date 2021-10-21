package com.example.testzalo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.testzalo.GetHashKey.printKeyHash
import com.zing.zalo.zalosdk.oauth.FeedData
import com.zing.zalo.zalosdk.oauth.OpenAPIService
import com.zing.zalo.zalosdk.oauth.ZaloPluginCallback

class MainActivity : AppCompatActivity() {
    var btnShareFeed: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        printKeyHash(this)
        var onClickShareFeed: ImOnClickListener = ImOnClickListener(this)

        btnShareFeed = findViewById(R.id.btn_share_feed)
        btnShareFeed?.setOnClickListener(onClickShareFeed)
    }
}

class ImZaloPluginCallBack : ZaloPluginCallback {
    override fun onResult(p0: Boolean, p1: Int, p2: String?, p3: String?) {
        Log.e("ERROR", "$p0, $p1, $p2")
    }

}

class ImOnClickListener(val ctx: Context): View.OnClickListener{
    var mCallBack: ImZaloPluginCallBack = ImZaloPluginCallBack()
    override fun onClick(p0: View?) {

        val feed = FeedData()
        feed.msg = "Prefill message"
        feed.link = "http://news.zing.vn"
        feed.linkTitle = "Zing News"
        feed.linkSource = "http://news.zing.vn"
        feed.linkThumb =
            arrayOf("http://img.v3.news.zdn.vn/w660/Uploaded/xpcwvovb/2015_12_15/cua_kinh_2.jpg")
        OpenAPIService.getInstance().shareFeed(this.ctx, feed, mCallBack)
    }
}
