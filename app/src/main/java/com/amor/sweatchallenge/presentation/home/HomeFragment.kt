package com.amor.sweatchallenge.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.GenericData
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.databinding.FragmentHomeBinding
import com.amor.sweatchallenge.presentation.MainActivity
import com.amor.sweatchallenge.util.SearchViewUtil
import com.amor.sweatchallenge.util.hideKeyboard
import com.amor.sweatchallenge.util.pagination.CallbackLoadMoreItems
import com.amor.sweatchallenge.util.pagination.PaginationUtil
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), CallbackLoadMoreItems {

    private val paginationUtil: PaginationUtil by inject()
    private val searchViewUtil: SearchViewUtil by inject()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    lateinit var adapter: HomeAdapter
    lateinit var favoriteAdapter: FavoriteAdapter
    var isSetupFavoriteAdapter = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        addObservers()
        setupAdapter()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.showProfilesPictures(paginationUtil.currentPage)
        viewModel.getUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_home, menu)
        searchViewUtil.setupSearchView(menu.findItem(R.id.search), activity, adapter)
    }

    override fun loadItems() {
        paginationUtil.setLoading(true)
        paginationUtil.currentPage = paginationUtil.currentPage + PaginationUtil.DEFAULT_PAGE
        viewModel.showProfilesPictures(paginationUtil.currentPage)
    }

    private fun addObservers() {
        viewModel.profilePicturesResult.observe(this, Observer (this::handleProfilePictureResult))
        viewModel.userResult.observe(this, Observer (this::handleUserResult))
    }

    private fun handleProfilePictureResult(response: GenericData<ArrayList<ProfileData>>) {
        binding.progressBar.visibility = View.GONE
        paginationUtil.setLoading(false)
        if(response.isSuccessful) {
            response.data?.let {
                adapter.addProfileData(it)
            }
        }
    }

    private fun handleUserResult(userList: ArrayList<ProfileData>) {
        if(userList.isNotEmpty()) {
            showFavorites()
            if (isSetupFavoriteAdapter) {
                favoriteAdapter.addFavoriteData(userList)
            } else {
                setupFavoriteAdapter(userList)
            }
        } else {
            hideFavorites()
        }
    }

    private fun setupAdapter() {
        adapter = HomeAdapter(paginationUtil, this::clickOnProfile)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.profileRecyclerView.layoutManager = linearLayoutManager
        binding.profileRecyclerView.adapter = adapter
        setupPagination(linearLayoutManager)
    }

    private fun setupFavoriteAdapter(userList: ArrayList<ProfileData>) {
        favoriteAdapter =  FavoriteAdapter(this::clickOnProfile)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.userRecyclerView.layoutManager = linearLayoutManager
        binding.userRecyclerView.adapter = favoriteAdapter
        favoriteAdapter.addFavoriteData(userList)
    }

    private fun setupPagination(linearLayoutManager: LinearLayoutManager) {
        paginationUtil.setRecycler(binding.profileRecyclerView)
        paginationUtil.addPaginationListener(linearLayoutManager, this)
        paginationUtil.currentPage = PaginationUtil.DEFAULT_PAGE
        paginationUtil.isFirstPage = true
    }

    private fun clickOnProfile(profileData: ProfileData) {
        hideKeyboard()
        (activity as MainActivity).addDetailFragment(profileData)
    }

    private fun showFavorites() {
        binding.titleText.visibility = View.VISIBLE
        binding.userRecyclerView.visibility = View.VISIBLE
    }

    private fun hideFavorites() {
        binding.titleText.visibility = View.GONE
        binding.userRecyclerView.visibility = View.GONE
    }
}