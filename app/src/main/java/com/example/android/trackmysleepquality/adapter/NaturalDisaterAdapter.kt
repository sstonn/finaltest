package com.example.android.trackmysleepquality.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.MainActivity
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.databinding.ActivityMainBinding
import com.example.android.trackmysleepquality.databinding.ListItemDisaterBinding
import com.example.android.trackmysleepquality.domain.Disater
import com.example.android.trackmysleepquality.pojo.NaturalDisater

class NaturalDisaterAdapter(val activity: MainActivity,val context: Context,val clickListener: NaturalDisaterListener): ListAdapter<Disater,NaturalDisaterAdapter.ViewHolder>(NaturalDisaterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Log.i("TestDataAdapter", "data at $position is $item")

        val magnitudeColor:Int = getMagnitudeColor(context,item.score)

        holder.bind(clickListener,item!!,magnitudeColor)
    }
    fun getMagnitudeColor(context:Context,score: Double): Int {
        val magnitudeFloor:Int = score.toInt()
        val magnitudeColorResourceId:Int = when(magnitudeFloor){
            1 -> R.color.magnitude1
            2 -> R.color.magnitude2
            3 -> R.color.magnitude3
            4 -> R.color.magnitude4
            5 -> R.color.magnitude5
            6 -> R.color.magnitude6
            7 -> R.color.magnitude7
            8 -> R.color.magnitude8
            9 -> R.color.magnitude9
            else -> R.color.magnitude10plus
        }
        return ContextCompat.getColor(context,magnitudeColorResourceId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemDisaterBinding): RecyclerView.ViewHolder(binding.root){
//        val disaterScore = binding.disaterScore
//        val disaterTitle = binding.disaterTitle

        fun bind(clickListener: NaturalDisaterListener,item: Disater,magnitudeColor:Int) {
            binding.disater = item
            binding.clickListener = clickListener

            val magnitudeCircle: GradientDrawable = binding.disaterScore.background as GradientDrawable
            magnitudeCircle.setColor(magnitudeColor)

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemDisaterBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}
class NaturalDisaterDiffCallback: DiffUtil.ItemCallback<Disater>(){
    override fun areItemsTheSame(oldItem: Disater, newItem: Disater): Boolean {
        return oldItem.score == newItem.score
    }

    override fun areContentsTheSame(oldItem: Disater, newItem: Disater): Boolean {
        return oldItem == newItem
    }
}
class NaturalDisaterListener(val clickListener: (disaterLink:String)-> Unit){
    fun onClick(disater: Disater) = clickListener(disater.id)
}