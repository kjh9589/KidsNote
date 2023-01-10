package com.kjs.kidsnote.presentation.main.detail_picsum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kjs.kidsnote.databinding.ViewholderDetailPicsumBinding
import com.kjs.model.picsum.PicsumModel

class DetailPicsumAdapter(
    private val listener: DetailPicsumListener
): ListAdapter<PicsumModel, DetailPicsumAdapter.ViewHolder>(diffUtil) {
    interface DetailPicsumListener {
        fun setOnLike(id: String, isLike: Boolean, position: Int)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PicsumModel>() {
            override fun areItemsTheSame(oldItem: PicsumModel, newItem: PicsumModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PicsumModel, newItem: PicsumModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    inner class ViewHolder(private val binding: ViewholderDetailPicsumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PicsumModel) {
            binding.picsumModel = item
            binding.executePendingBindings()

            binding.likeImageView.setOnClickListener {
                binding.likeImageView.changeState()
                listener.setOnLike(item.id, !item.isLike, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderDetailPicsumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}