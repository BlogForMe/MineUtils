package com.comm.util.dagger.xiangxue

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comm.util.databinding.ActivityDaggerXxBinding
import javax.inject.Inject

class DaggerxxActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDaggerXxBinding.inflate(layoutInflater) }
    private val TAG = "DaggerxxActivity"

    @Inject
    lateinit var httpObject: HttpObject

    @Inject
    lateinit var databaseObject: DatabaseObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        DaggerMyComponent.create().inject(this)

        DaggerMyComponent.builder()
            .httpModule(HttpModule())
            .dataBaseModule(DataBaseModule())
            .build()
            .inject(this)
        Log.i(TAG, "onCreate: httpObject $httpObject  databaseObject  $databaseObject ")


    }
}