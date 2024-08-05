package com.example.coldsounds.google_auth_ui_client.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coldsounds.R
import com.example.coldsounds.google_auth_ui_client.presentation.SignInViewModel
import com.example.coldsounds.databinding.SignInFragmentBinding
import com.example.coldsounds.google_auth_ui_client.domain.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = requireContext(),
            oneTapClient = Identity.getSignInClient(requireContext()),
        )
    }

    private var _binding: SignInFragmentBinding? = null
    private val binding: SignInFragmentBinding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()

    private lateinit var signInLauncher: ActivityResultLauncher<IntentSenderRequest>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        val animationFadeOut = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)

        signInLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                viewModel.signIn(googleAuthUiClient, result.data!!)
                binding.signInMainLayout.startAnimation(animationFadeOut)
            }
        }

        if (Firebase.auth.currentUser != null) {
            viewModel.checkSignIn(googleAuthUiClient)
        }

        binding.signInWithGoogleButton.setOnClickListener {
            lifecycleScope.launch {
                val signInPendingIntent = googleAuthUiClient.getSignInIntent()
                signInPendingIntent?.let {
                    val intentSenderRequest = IntentSenderRequest.Builder(it.intentSender).build()
                    signInLauncher.launch(intentSenderRequest)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val RC_SIGN_IN = 1001
    }
}