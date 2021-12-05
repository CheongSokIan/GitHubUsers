package com.tina.githubusers.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.ListItemUserBinding


class UserListAdapter(private val loadMore: (id: Long) -> Unit) : ListAdapter<User, UserListViewHolder>(UserDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = getItem(position)
        val numberOfItem = position + 1
        holder.bindTo(user, numberOfItem)

        if (itemCount - position == 1) {
            loadMore(user.id)
        }
    }
}


object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}