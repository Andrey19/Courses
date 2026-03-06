package ru.effectivemobile.courses.entry

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.effectivemobile.courses.base_feature.mvvm.BaseFragment
import ru.effectivemobile.courses.entry.data.EntryUiEvent
import ru.effectivemobile.courses.entry.data.EntryUiState
import ru.effectivemobile.courses.entry.databinding.EntryFragmentBinding

@AndroidEntryPoint
internal class EntryFragment : BaseFragment<EntryUiState, EntryUiEvent>() {

    lateinit var binding: EntryFragmentBinding
    override val viewModel: EntryViewModel by viewModels()


    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
        initListeners()
    }

    override fun handleUiEvent(event: EntryUiEvent) {
        when (event) {
            EntryUiEvent.SetInputButtonActive -> {
                binding.button.isEnabled = true
            }
            EntryUiEvent.SetInputButtonInActive -> {
                binding.button.isEnabled = false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EntryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListeners() {
        with(binding) {
            button.setOnClickListener {
                viewModel.onSignInClick()
            }
            vkButton.setOnClickListener {
                viewModel.onVkClick()
            }
            okButton.setOnClickListener {
                viewModel.onOkClick()
            }
            emailEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) = Unit

                override fun afterTextChanged(s: Editable?) {
                    viewModel.onEmailInput(s.toString())
                }
            })
            passwordEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) = Unit

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) = Unit

                override fun afterTextChanged(s: Editable?) {
                    viewModel.onPasswordInput(s.toString())
                }
            })
        }
    }
}