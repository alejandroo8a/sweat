package com.amor.sweatchallenge.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(
    private val listener: (profile: ProfileData) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val profilePictureList = arrayListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profilePictureList[position], listener)
    }

    override fun getItemCount(): Int = profilePictureList.size

    fun addFavoriteData(newProfileData: ArrayList<ProfileData>) {
        profilePictureList.clear()
        profilePictureList.addAll(newProfileData)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(profileData: ProfileData, listener: (profile: ProfileData) -> Unit) = with(itemView) {
            Picasso.get().load(profileData.largeImage).placeholder(R.drawable.ic_placeholder).into(profilePicture)
            titleText.text = profileData.name
            setOnClickListener { listener(profileData) }
        }
    }
}