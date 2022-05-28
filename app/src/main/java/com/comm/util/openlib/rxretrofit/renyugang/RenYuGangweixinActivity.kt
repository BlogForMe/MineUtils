package com.comm.util.openlib.rxretrofit.renyugang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.R
import com.comm.util.openlib.rxretrofit.renyugang.TopArticle2.delay
import com.comm.util.openlib.rxretrofit.renyugang.TopArticle3.retryWhen
import kotlinx.android.synthetic.main.activity_ren_yu_gangweixin.*

class RenYuGangweixinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ren_yu_gangweixin)


        //中
        button2.setOnClickListener {
            delay()
        }

        // 下
        button3.setOnClickListener {

            //            retry()
//            retryUntil()
            retryWhen()
//             repeat();
        }

    }
}
