package com.devartLab.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devartLab.task.databinding.ActivityMainBinding
import com.devartLab.task.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        supportFragmentManager.beginTransaction().replace(R.id.rootId,HomeFragment()).commit()
    }
}