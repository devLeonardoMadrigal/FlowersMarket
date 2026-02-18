package com.example.flowersmarket.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowersmarket.R
import com.example.flowersmarket.databinding.ItemFlowersBinding
import com.example.flowersmarket.model.FlowersResponseItem


class FlowersAdapter(private val flowers: List<FlowersResponseItem>) : RecyclerView.Adapter<FlowersAdapter.FlowersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowersViewHolder {
        val binding = ItemFlowersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FlowersViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FlowersViewHolder,
        position: Int
    ) {
        val flowerItems = flowers[position]

        holder.binding.apply {
            tvTitle.text = flowerItems.name
            tvDescription.text = flowerItems.instructions

            Glide.with(holder.itemView.context)
                .load("https://services.hanselandpetal.com/photos/"+flowerItems.photo)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(flowerImage)
        }
    }

    override fun getItemCount(): Int {
        return flowers.size
    }

    class FlowersViewHolder(val binding: ItemFlowersBinding) :
            RecyclerView.ViewHolder(binding.root)

}