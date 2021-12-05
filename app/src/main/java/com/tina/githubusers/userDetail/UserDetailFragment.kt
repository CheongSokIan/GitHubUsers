package com.tina.githubusers.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tina.githubusers.databinding.FragmentUserDetailBinding

class UserDetailFragment: Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)


        return _binding!!.root
    }

    companion object {
        const val ARG_USER_ID = "ARG_USER_ID"
    }
}