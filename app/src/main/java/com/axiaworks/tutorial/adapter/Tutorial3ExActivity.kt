package com.axiaworks.tutorial.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.axiaworks.tutorial.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Tutorial3ExActivity: AppCompatActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, Tutorial3ExActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial3_ex)

        intiViewPager2WithFragments()
    }



    private fun intiViewPager2WithFragments() {
        Log.i("Tutorial3ExActivity", "intiViewPager2WithFragments")
        val viewPager:ViewPager2 = findViewById(R.id.viewpager2)
        val adapter = ViewPager2Adapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
        }.attach()


    }

}