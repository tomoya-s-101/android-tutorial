package com.axiaworks.tutorial.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.axiaworks.tutorial.R
import com.axiaworks.tutorial.databinding.ItemConnpassItemBinding
import com.axiaworks.tutorial.mvvm.response.ConnpassItem

class ConnpassEventsAdapter(
    private var eventItems: List<ConnpassItem>
) : RecyclerView.Adapter<ConnpassEventsAdapter.ViewHolder>() {

    var onClickListener: ((ConnpassItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_connpass_item, parent, false
            )
        )
    }

    override fun getItemCount() = eventItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(eventItems[position], onClickListener)
    }

    class ViewHolder(private val binding: ItemConnpassItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ConnpassItem, listener: ((ConnpassItem) -> Unit)?) {
            binding.item = item
            binding.root.setOnClickListener {
                listener?.invoke(item)
            }
        }
    }

    fun setList(list: List<ConnpassItem>) {
        eventItems = list
        notifyDataSetChanged()
    }
}