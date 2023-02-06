package com.example.albertontakehomeassignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.albertontakehomeassignment.databinding.AcromineItemBinding
import com.example.albertontakehomeassignment.domain.model.Lf

class AcromineAdapter : RecyclerView.Adapter<AcromineAdapter.LongFormViewHolder>() {

    private val longFormList = ArrayList<Lf>()

    inner class LongFormViewHolder(private val binding: AcromineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(lf: Lf) {
            binding.longForm = lf
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LongFormViewHolder {
        val binding = AcromineItemBinding.inflate(LayoutInflater.from(parent.context))
        binding.root.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        return LongFormViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LongFormViewHolder, position: Int) {
        holder.bind(longFormList[position])
    }

    override fun getItemCount(): Int = longFormList.size

    fun updateList(newList: List<Lf>) {
        longFormList.clear()
        longFormList.addAll(newList)
        notifyDataSetChanged()
    }
}
