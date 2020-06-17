package com.amor.sweatchallenge.presentation.home.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.amor.sweatchallenge.R

import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.databinding.FragmentDetailBinding
import com.amor.sweatchallenge.util.launchCallPhone
import com.amor.sweatchallenge.util.launchMap
import com.amor.sweatchallenge.util.saveContact
import com.amor.sweatchallenge.util.showSnackBar
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModel()

    private lateinit var profileData: ProfileData
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)
        setupView()
        addListeners()
        isFavorite = profileData.isFavorite
        setRightFavoriteIcon()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                saveContact(profileData.name)
                true
            }
            else -> false
        }
    }

    private fun setupView() {
        binding.nameText.text = profileData.name
        binding.emailText.text = profileData.email
        binding.phoneText.text = profileData.phone
        binding.locationText.text = profileData.location
        Picasso.get().load(profileData.largeImage).into(binding.pictureImage)
    }

    private fun addListeners() {
        binding.favoriteFab.setOnClickListener {
            handleIsFavorite()
        }

        binding.callButton.setOnClickListener {
            launchCallPhone(profileData.phone)
        }

        binding.locationButton.setOnClickListener {
            launchMap(profileData.latitude, profileData.longitude)
        }
    }

    private fun handleIsFavorite() {
        isFavorite = !isFavorite
        if (isFavorite) {
            showSnackBar(R.string.user_added)
            viewModel.addFavoriteUser(profileData)
        } else {
            showSnackBar(R.string.user_delete)
            viewModel.deleteFavoriteUser(profileData.userId)
        }
        setRightFavoriteIcon()
    }

    private fun setRightFavoriteIcon() {
        if (isFavorite) {
            binding.favoriteFab.setImageResource(R.drawable.ic_favorite_fill)
        } else {
            binding.favoriteFab.setImageResource(R.drawable.ic_favorite_empty)
        }
    }

    companion object {

        fun newInstance(profileData: ProfileData): DetailFragment {
            val detailFragment = DetailFragment()
            detailFragment.profileData = profileData
            return detailFragment
        }

    }

}