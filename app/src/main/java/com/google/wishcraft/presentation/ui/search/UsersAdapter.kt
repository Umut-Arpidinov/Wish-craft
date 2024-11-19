package com.google.wishcraft.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.wishcraft.R
import com.google.wishcraft.databinding.ItemUserBinding
import com.google.wishcraft.domain.models.User
import com.google.wishcraft.presentation.extensions.loadImage

class UsersAdapter : ListAdapter<User, UsersAdapter.UserViewHolder>(DiffUtils) {


    private var followClickListener : ((id: Int, itemPosition: Int) -> Unit)? = null

    fun followClicked(listener : (id: Int, itemPosition: Int) -> Unit) {
        followClickListener = listener
    }

    private var unFollowClickListener : ((id: Int, itemPosition: Int) -> Unit)? = null

    fun unFollowClicked(listener : (id: Int, itemPosition: Int) -> Unit) {
        unFollowClickListener = listener
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            imageProfile.loadImage(
                user.staticObject?.url,
                R.drawable.profile_placeholder
            )
            user.username?.let {
                tvUserName.text = it
            }
            user.location?.let {
                tvUserInfo.text = it
            }

            buttonFollow.setFollowingState(user.followed)
            buttonFollow.setOnClickListener {
                when(user.followed) {
                    true -> {
                        user.id?.let {
                            unFollowClickListener?.invoke(it, adapterPosition)
                        }

                    }
                    false -> {
                        user.id?.let {
                            followClickListener?.invoke(it, adapterPosition)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)

    }


    override fun onBindViewHolder(holder: UsersAdapter.UserViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    private fun Button.setFollowingState(isFollowed: Boolean) {
        if(isFollowed){
            setBackgroundResource(R.drawable.button_following)
            setTextColor(context.getColor(R.color.sea_blue_2))
            setText(R.string.following)
        } else {
            setBackgroundResource(R.drawable.button_follow)
            setTextColor(context.getColor(R.color.white))
            setText(R.string.follow)
        }
    }

    companion object {

        object DiffUtils : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}