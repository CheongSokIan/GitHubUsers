package com.tina.githubusers.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tina.githubusers.Injection
import com.tina.githubusers.data.User
import com.tina.githubusers.databinding.FragmentUserDetailBinding
import kotlinx.coroutines.launch


class UserDetailFragment : Fragment(), UserDetailContract.View {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var presenter: UserDetailPresenter

    private var login: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        presenter = UserDetailPresenter(this, Injection.provideUserRepository(requireContext()))

        login = arguments?.getString(ARG_LOGIN)

        binding.imageClose.setOnClickListener { findNavController().navigateUp() }

        return _binding!!.root
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { presenter.loadDetail(login!!) }
    }


    override fun showDetail(user: User) {
        binding.apply {
            textUsername.text = user.name
            textBio.text = user.bio

            textLoginName.text = user.login
            textStaffBadge.isVisible = user.isAdmin

            textLocation.text = user.location
            textLink.text = user.blog

            Glide.with(imageAvatar.context)
                .load(user.avatarUrl)
                .circleCrop()
                .into(imageAvatar)
        }
    }


    companion object {
        const val ARG_LOGIN = "ARG_LOGIN"
    }
}