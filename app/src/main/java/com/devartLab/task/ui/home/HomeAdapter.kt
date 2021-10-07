package com.devartLab.task.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devartLab.task.databinding.ItemOrdersBinding
import com.devartLab.task.model.HomeResponseModel


class HomeAdapter(
    private var dataList: List<HomeResponseModel>,
    var onHomeItemClicked: OnHomeItemClicked
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOrdersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], onHomeItemClicked)

    }


    class ViewHolder(private var binding: ItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeResponseModel, onHomeItemClicked: OnHomeItemClicked) {
            binding.model = item
            binding.executePendingBindings()
            binding.idDetailTrip.setOnClickListener {
                onHomeItemClicked.onItemClicked(item)
            }
            binding.idRemoveTrip.setOnClickListener {

            }
        }

    }
    interface OnHomeItemClicked {
        fun onItemClicked(dataList: HomeResponseModel)
    }


}