package com.comm.util.work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.databinding.ActivityReloadBinding

class ReloadActivity : AppCompatActivity() {
    val biding by lazy { ActivityReloadBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.btReloadPage.setOnClickListener {
            finish()
        }
    }
}