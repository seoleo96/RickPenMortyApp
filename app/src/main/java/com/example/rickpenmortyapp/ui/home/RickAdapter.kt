package com.example.rickpenmortyapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickpenmortyapp.R
import com.example.rickpenmortyapp.domain.model.CharacterDomainModel
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


const val ONE = 1
const val TWO = 2

class RickAdapter(private val gridLayoutManager: GridLayoutManager) :
    RecyclerView.Adapter<RickAdapter.RickViewHolder>() {


    private val list = arrayListOf<CharacterDomainModel>()
    fun updateList(list: List<CharacterDomainModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) =
        if (gridLayoutManager.spanCount == ONE)
            ONE
        else
            TWO

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RickViewHolder {
        return if (viewType == ONE)
            RickViewHolder(R.layout.rick_row_linear.makeView(parent))
        else
            RickViewHolder(R.layout.rick_row_grid.makeView(parent))
    }

    private fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)

    override fun onBindViewHolder(holder: RickViewHolder, position: Int) {
        val bind: CharacterDomainModel = list[position]
        holder.bind(bind)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RickViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val mName = itemView.findViewById<TextView>(R.id.name_tv)
        private val mSpecies = itemView.findViewById<TextView>(R.id.species_gender_tv)
        private val mStatus = itemView.findViewById<TextView>(R.id.status_tv)
        private val mImage = itemView.findViewById<CircleImageView>(R.id.profile_image)
        private val cv = itemView.findViewById<CardView>(R.id.cv)
        private val blue = itemView.findViewById<ImageView>(R.id.favourite_imageview)
        fun bind(quote: CharacterDomainModel) {
            quote.apply {
                mName.text = name
                mSpecies.text = species
                Glide
                    .with(view.context)
                    .load(image)
                    .centerCrop()
                    .into(mImage)

                status.apply {
                    when (this) {
                        "Alive" -> {
                            mStatus.text = this.uppercase(Locale.getDefault())
                            mStatus.setTextColor(Color.rgb(0, 215, 10))
                        }
                        "Dead" -> {
                            mStatus.text = this.uppercase(Locale.getDefault())
                            mStatus.setTextColor(Color.RED)
                        }
                        else -> {
                            mStatus.text = this.uppercase(Locale.getDefault())
                            mStatus.setTextColor(Color.GRAY)
                        }
                    }
                }
            }
        }
    }
}