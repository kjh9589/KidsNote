package com.kjs.kidsnote.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kjs.kidsnote.databinding.ViewholderMainPicsumBinding
import com.kjs.model.picsum.PicsumModel

class MainPicsumAdapter(
    private val listener: MainPicsumListener
): ListAdapter<PicsumModel, MainPicsumAdapter.ViewHolder>(diffUtil) {
    interface MainPicsumListener {
        fun moveToMain(item: PicsumModel, position: Int)
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

    inner class ViewHolder(private val binding: ViewholderMainPicsumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PicsumModel) {
            binding.picsumModel = item
            binding.executePendingBindings()

            binding.thumbnailImageView.setOnClickListener {
                listener.moveToMain(item, absoluteAdapterPosition)
            }

            binding.likeImageView.setOnClickListener {
                binding.likeImageView.changeState()
                listener.setOnLike(item.id, !item.isLike, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewholderMainPicsumBinding.inflate(
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