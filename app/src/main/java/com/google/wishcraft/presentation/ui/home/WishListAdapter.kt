package com.google.wishcraft.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.wishcraft.R
import com.google.wishcraft.databinding.ItemWishBinding
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.presentation.extensions.loadImage

class WishListAdapter : ListAdapter<Wish, WishListAdapter.WishViewHolder>(DiffUtils) {


    private var onWishClickListener: ((Wish) -> Unit)? = null

    fun onWishClickListener(listener: (Wish) -> Unit) {
        onWishClickListener = listener
    }


    private var onSaveClickListener: ((Wish) -> Unit)? = null

    fun onSaveClickListener(listener: (Wish) -> Unit) {
        onSaveClickListener = listener
    }




    inner class WishViewHolder(val binding: ItemWishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(wish: Wish) = with(binding) {
           wish.staticObject?.url?.let {
               val url = it.replace("localhost","192.168.1.52")
               ivWishImage.loadImage(url, R.drawable.ic_empty)
           }
            tvWishName.text = wish.giftName
            tvUserName.text = wish.user?.username

            icSave.setOnClickListener {
                onSaveClickListener?.invoke(wish)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding =
            ItemWishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishViewHolder(binding)

    }


    override fun onBindViewHolder(holder: WishListAdapter.WishViewHolder, position: Int) {
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