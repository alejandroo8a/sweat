package com.amor.sweatchallenge.presentation.home.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.amor.sweatchallenge.R

import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileData: ProfileData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)
        setupViewData()
    }

    private fun setupViewData() {
        binding.nameText.text = profileData.name
        binding.emailText.text = profileData.email
        binding.phoneText.text = profileData.phone
        binding.locationText.text = profileData.location
        Picasso.get().load(profileData.largeImage).placeholder(R.drawable.ic_placeholder).into(binding.pictureImage)
    }

    companion object {

        fun newInstance(profileData: ProfileData): DetailFragment {
            val detailFragment = DetailFragment()
            detailFragment.profileData = profileData
            return detailFragment
        }

    }

}