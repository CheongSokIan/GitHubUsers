package com.tina.githubusers.userList

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tina.githubusers.R
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.ListItemUserBinding
import com.tina.githubusers.userDetail.UserDetailFragment

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
                val bundle = Bundle().apply { putLong(UserDetailFragment.ARG_USER_ID, user.id) }
                it.findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
            }
        }
    }
}