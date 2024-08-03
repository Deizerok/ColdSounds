package com.example.coldsounds.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coldsounds.profile.ProfileViewModel
import com.example.coldsounds.R
import com.example.coldsounds.databinding.ProfileFragmentBinding
import com.example.coldsounds.google_auth_ui_client.domain.UserData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        const val KEY_USER_DATA = "key_user_data"


        fun newInstance(userData: UserData) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_USER_DATA, userData)
            }
        }
    }

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.show()

        val animationFadeIn = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)
        binding.profileMainLayout.startAnimation(animationFadeIn)

        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(KEY_USER_DATA, UserData::class.java)
        } else {
            requireArguments().getSerializable(KEY_USER_DATA) as UserData
        }!!

        binding.generalUserNameTextView.text = userData.username
        binding.userNameTextView.text = userData.username
        binding.gmailTextView.text = userData.gmail
        Picasso.get()
            .load(userData.profilePictureUrl)
            .placeholder(R.drawable.person_icon)
            .into(binding.googleUserPicture);
            binding.googleUserPicture.clipToOutline = true

        binding.logOutButton.setOnClickListener {
            viewModel.signOut(Firebase.auth)
            binding.profileMainLayout.startAnimation(animationFadeOut)
        }

//        binding.favoritesMusicButton.setOnClickListener {
//            Toast.makeText(requireContext(),"Sorry, this feature is not working", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}