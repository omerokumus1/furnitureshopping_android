package com.omerokumus.furnitureshopping.feature.main.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerokumus.furnitureshopping.databinding.ItemProfileOptionRowBinding

class ProfileOptionRowAdapter(
    private val profileOptionList: List<ProfileOptionRow>,
    private val onClickRow: (ProfileOptionRow) -> Unit
) :
    RecyclerView.Adapter<ProfileOptionRowAdapter.ProfileOptionRowViewHolder>() {


    inner class ProfileOptionRowViewHolder(val binding: ItemProfileOptionRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileOptionRowBinding.inflate(inflater, parent, false)
        return ProfileOptionRowViewHolder(binding)
    }

    override fun getItemCount() = profileOptionList.size

    override fun onBindViewHolder(holder: ProfileOptionRowViewHolder, position: Int) {
        profileOptionList[position].let {
            holder.binding.run {
                title.text = it.title
                subtitle.text = it.subtitle
                root.setOnClickListener { _ -> onClickRow(it) }
            }
        }
    }
}