package com.axiaworks.tutorial.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.axiaworks.tutorial.R
import com.axiaworks.tutorial.databinding.ActivityTutorial6ExBinding
import org.koin.android.viewmodel.ext.android.viewModel

class Tutorial6ExActivity : AppCompatActivity() {
    companion object {
        fun callingIntent(context: Context) = Intent(context, Tutorial6ExActivity::class.java)
    }

    private val viewModel: Tutorial6ExViewModel by viewModel()
    private lateinit var binding: ActivityTutorial6ExBinding
    private var adapter: ConnpassEventsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityTutorial6ExBinding>(
            this, R.layout.activity_tutorial6_ex
        ).apply {
            recyclerView.adapter = ConnpassEventsAdapter(emptyList()).apply {
                this@Tutorial6ExActivity.adapter = this
            }
            searchButton.setOnClickListener {
                viewModel.fetchTagEvents("android")
            }
        }

        bindViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bindViews() {
        viewModel.isProgress.observe(this, Observer {
            binding.progressContainer.visibility = if (it == true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.connpassEvents.observe(this, Observer {
            it?.events_items?.let { list ->
                adapter?.setList(list)
            }
        })
    }
}