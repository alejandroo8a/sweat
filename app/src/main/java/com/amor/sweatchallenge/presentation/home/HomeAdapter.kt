package com.amor.sweatchallenge.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.amor.sweatchallenge.util.pagination.PaginationUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(
    private val paginationUtil: PaginationUtil,
    private val callbackStatusResult: StatusResult,
    private val listener: (profile: ProfileData) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(), Filterable {

    private val profilePictureList = arrayListOf<ProfileData>()
    private val completeProfilePictureList = arrayListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profilePictureList[position], listener)
    }

    override fun getItemCount(): Int = profilePictureList.size

    override fun getFilter(): Filter {
        return myFilter
    }

    fun addProfileData(newProfileData: ArrayList<ProfileData>) {
        profilePictureList.addAll(newProfileData)
        completeProfilePictureList.addAll(newProfileData)
        notifyDataSetChanged()
    }

    private var myFilter: Filter = object : Filter() {

        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filteredList: MutableList<ProfileData> = ArrayList()
            if (charSequence.isEmpty() ) {
                paginationUtil.setLoading(false)
                filteredList.addAll(completeProfilePictureList)
            } else {
                if(profilePictureList.isEmpty()) {
                    profilePictureList.addAll(completeProfilePictureList)
                }
                for (profile in profilePictureList) {
                    if (profile.name.toLowerCase(Locale.ROOT).contains(
                            charSequence.toString().toLowerCase(
                                Locale.ROOT
                            )
                        )
                    ) {
                        filteredList.add(profile)
                    }
                }
            }

            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(
            charSequence: CharSequence,
            filterResults: FilterResults
        ) {
            profilePictureList.clear()
            profilePictureList.addAll(filterResults.values as Collection<ProfileData>)
            if (profilePictureList.size == 0) {
                callbackStatusResult.showViewNoResult()
            } else {
                callbackStatusResult.hideViewNoResult()
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(profileData: ProfileData, listener: (profile: ProfileData) -> Unit) = with(itemView) {
            Picasso.get().load(profileData.thumbnail).placeholder(R.drawable.ic_placeholder).into(profilePicture)
            titleText.text = profileData.name
            subtitleText.text = profileData.phone
            setOnClickListener { listener(profileData) }
        }
    }
}