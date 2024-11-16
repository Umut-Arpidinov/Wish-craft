package com.google.wishcraft.presentation.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.wishcraft.R
import com.google.wishcraft.databinding.ItemMyWishBinding
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.presentation.extensions.loadImage

class MyWishesAdapter : ListAdapter<Wish, MyWishesAdapter.WishViewHolder>(DiffUtils) {


    private var onWishClickListener: ((Wish) -> Unit)? = null

    fun onWishClickListener(listener: (Wish) -> Unit) {
        onWishClickListener = listener
    }

    inner class WishViewHolder(val binding: ItemMyWishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wish: Wish) = with(binding) {
            wish.staticObject?.url?.let {
                ivWishImage.loadImage(it, R.drawable.ic_launcher_background)
            }
            tvProductName.text = wish.giftName
            tvPrice.text = wish.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding =
            ItemMyWishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishViewHolder(binding)

    }


    override fun onBindViewHolder(holder: MyWishesAdapter.WishViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    companion object {

        object DiffUtils : DiffUtil.ItemCallback<Wish>() {
            override fun areItemsTheSame(oldItem: Wish, newItem: Wish): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Wish, newItem: Wish): Boolean {
                return oldItem == newItem
            }
        }
    }


}