package com.amor.sweatchallenge.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
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
            if (charSequence.isEmpty()) {
                filteredList.addAll(completeProfilePictureList)
            } else {
                for (profile in profilePictureList) {
                    if (profile.name.toLowerCase().contains(charSequence.toString().toLowerCase())) {
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