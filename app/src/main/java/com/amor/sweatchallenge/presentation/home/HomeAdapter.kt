package com.amor.sweatchallenge.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amor.sweatchallenge.R
import com.amor.sweatchallenge.data.ProfileData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
private val listener: () -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val profilePictureList = arrayListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profilePictureList[position], listener)
    }

    override fun getItemCount(): Int = profilePictureList.size

    fun addProfileData(newProfileData: ArrayList<ProfileData>) {
        profilePictureList.addAll(newProfileData)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(profileData: ProfileData, listener: () -> Unit) = with(itemView) {
            Picasso.get().load(profileData.thumbnail).placeholder(R.drawable.ic_placeholder).into(profilePicture)
            titleText.text = profileData.name
            subtitleText.text = profileData.cell
            setOnClickListener { listener() }
        }
    }
}