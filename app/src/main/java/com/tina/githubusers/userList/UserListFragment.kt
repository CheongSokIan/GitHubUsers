package com.tina.githubusers.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tina.githubusers.Injection
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.FragmentUserListBinding
import kotlinx.coroutines.launch

class UserListFragment : Fragment(), UserListContract.View {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { UserListAdapter() }

    private lateinit var presenter: UserListPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        presenter = UserListPresenter(this, Injection.provideUserRepository(requireContext().applicationContext))

        binding.recyclerViewList.adapter = adapter

        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    override fun showUserList(users: List<User>) {
        adapter.submitList(users)
    }

    private fun refresh() {
        lifecycleScope.launch {
            presenter.loadUserList(0, 100)
        }
    }
}