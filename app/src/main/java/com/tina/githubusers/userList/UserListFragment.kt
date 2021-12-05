package com.tina.githubusers.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tina.githubusers.databinding.FragmentUserListBinding

class UserListFragment: Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        return _binding!!.root
    }
}