package ru.effectivemobile.courses.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.effectivemobile.courses.base_feature.mvvm.BaseFragment
import ru.effectivemobile.courses.profile.data.ProfileUiEvent
import ru.effectivemobile.courses.profile.data.ProfileUiState
import ru.effectivemobile.courses.profile.databinding.ProfileFragmentBinding

@AndroidEntryPoint
internal class ProfileFragment : BaseFragment<ProfileUiState, ProfileUiEvent>() {

    lateinit var binding: ProfileFragmentBinding
    override val viewModel: ProfileViewModel by viewModels()

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
        initListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListeners() {
        with(binding) {
            logOutButton.setOnClickListener {
                viewModel.onSignOutClick()
            }
        }
    }
}