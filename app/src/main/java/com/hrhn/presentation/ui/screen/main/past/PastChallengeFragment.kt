package com.hrhn.presentation.ui.screen.main.past

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hrhn.databinding.FragmentPastChallengeBinding
import com.hrhn.domain.model.Challenge
import com.hrhn.presentation.ui.screen.review.ReviewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PastChallengeFragment : Fragment() {
    private var _binding: FragmentPastChallengeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<PastChallengeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPastChallengeBinding.inflate(inflater).apply {
            composeView.setContent {
                PastChallengeScreen(
                    viewModel = viewModel,
                    onItemClick = ::navigateToReview,
                )
            }
        }
        return binding.root
    }

    private fun navigateToReview(challenge: Challenge) {
        startActivity(ReviewActivity.newIntent(requireContext(), challenge))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
