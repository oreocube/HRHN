package com.hrhn.presentation.ui.screen.main.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hrhn.databinding.FragmentTodayBinding
import com.hrhn.presentation.ui.screen.addchallenge.AddChallengeActivity
import com.hrhn.presentation.ui.screen.edit.EditChallengeActivity
import com.hrhn.presentation.util.observeEvent
import com.hrhn.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment() {
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<TodayViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater).apply {
            composeView.setContent {
                TodayScreen(viewModel)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            addEvent.observeEvent(viewLifecycleOwner) {
                startActivity(AddChallengeActivity.newIntent(requireContext()))
            }
            message.observeEvent(viewLifecycleOwner) {
                requireContext().showToast(it)
            }
            editEvent.observeEvent(viewLifecycleOwner) {
                startActivity(EditChallengeActivity.newIntent(requireContext(), it))
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
