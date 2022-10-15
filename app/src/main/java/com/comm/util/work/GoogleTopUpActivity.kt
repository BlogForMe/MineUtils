package com.comm.util.work

import android.content.Intent
import android.os.Bundle
import com.comm.util.base.BaseActivity
import com.comm.util.databinding.ActivityGoogleTopUpBinding

class GoogleTopUpActivity : BaseActivity() {
    val biding by lazy { ActivityGoogleTopUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.btPage.setOnClickListener {
            onTopUpAbandon()
        }
        biding.btGoReload.setOnClickListener {
            startActivity(Intent(this, ReloadActivity::class.java))
            finish()
        }
    }

    private fun onTopUpAbandon() {
        val data = Intent()
        data.putExtra("gspTopUpResponse", "onTopUpAbandon")
        this.setResult(0, data)
        finish()
    }
}