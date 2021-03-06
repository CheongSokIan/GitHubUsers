package com.tina.githubusers.userList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tina.githubusers.Injection
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.FragmentUserListBinding
import kotlinx.coroutines.launch

class UserListFragment : Fragment(), UserListContract.View {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { UserListAdapter { id -> loadMoreData(id) } }

    private lateinit var presenter: UserListPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        presenter = UserListPresenter(this, Injection.provideUserRepository(requireContext()))

        binding.recyclerViewList.adapter = adapter

        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        loadMoreData(0)
    }

    override fun showUserList(users: List<User>) {
        // In order to trigger DiffCallback update list, create a new list everytime
        val newList = users.toList()
        adapter.submitList(newList) {
            binding.progressCircular.isVisible = false
        }
    }


    private fun loadMoreData(id: Long) {
        lifecycleScope.launch {
            binding.progressCircular.isVisible = true
            presenter.loadUserList(id)
        }
    }
}