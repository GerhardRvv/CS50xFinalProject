package com.gerhard.cs50x.tweet.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.tweet.viewmodel.TweetsViewModel
import com.gerhard.cs50x.tweet.ui.UserDetailsScreen
import com.gerhard.cs50x.theme.TwitterAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private val viewModel: TweetsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TwitterAppTheme {
                    UserDetailsScreen(
                        viewModel = viewModel,
                        onBackClicked = {
                            this@UserDetailsFragment.findNavController().popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Parcelize
data class UserDetailsFragmentNavArgs(
    val user: TwitterUser,
) : Parcelable
