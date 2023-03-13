package com.example.kreditbee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kreditbee.databinding.ImageGridBinding
import com.example.kreditbee.networks.resopnse.AlbumIdResponseItem1


class DateAdapter(var dateList: List<AlbumIdResponseItem1>) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    inner class DateViewHolder(val binding : ImageGridBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DateViewHolder {
        val binding = ImageGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        with(holder){
            binding.title.text = dateList[position].title
            Glide.with(itemView.context)
                .load(dateList[position].thumbnailUrl)
                .into(binding.imageData)
        }
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

}