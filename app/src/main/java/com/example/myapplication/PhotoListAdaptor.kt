package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemPhotoListCardBinding
import com.example.myapplication.models.PhotoListModel

class PhotoListAdaptor(private val photos: List<PhotoListModel>) :
    RecyclerView.Adapter<PhotoListAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPhotoListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.binding.titleTextView.text = photo.title
        Glide.with(holder.itemView)
            .load(photo.thumbnail)
            .into(holder.binding.thumbnailImage)
    }

    override fun getItemCount(): Int = photos.size

    inner class ViewHolder(val binding: ItemPhotoListCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}
