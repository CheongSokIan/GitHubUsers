package com.tina.githubusers.userList

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.ListItemUserBinding

class UserListViewHolder(private val binding: ListItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(user: User) {
        binding.apply {
            textLoginName.text = user.login
            textStaffBadge.isVisible = user.isAdmin

            Glide.with(imageAvatar.context)
                .load(user.avatarUrl)
                .circleCrop()
                .into(imageAvatar)

            itemContainer.setOnClickListener {
                // Navigate to User Detail Fragment
            }
        }
    }
}