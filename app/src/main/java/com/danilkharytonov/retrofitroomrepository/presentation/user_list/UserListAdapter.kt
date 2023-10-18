package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.danilkharytonov.retrofitroomrepository.databinding.UserItemBinding
import com.danilkharytonov.retrofitroomrepository.domain.model.User

class UserListAdapter(
    private val binder: (User) -> Unit
) : PagingDataAdapter<User, UserListAdapter.ViewHolder>(ItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = UserItemBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user, binder)
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.login.uuid == newItem.login.uuid
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onClick: (User) -> Unit) {
            binding.nameUserList.text = "${user.name.firstName} ${user.name.lastName}"
            binding.root.setOnClickListener {
                onClick(user)
            }
        }
    }
}