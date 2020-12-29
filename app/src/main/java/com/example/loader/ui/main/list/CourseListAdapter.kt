package com.example.loader.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loader.databinding.CourseRowBinding
import com.example.loader.models.CourseList
import com.example.loader.ui.main.CourseViewModel

class CourseListAdapter(private val viewModels: CourseViewModel?) :
        RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    private val items = ArrayList<CourseList>()

    fun submitList(newList: LinkedHashSet<CourseList>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(courseList: CourseList) {
            val binding = DataBindingUtil.bind<CourseRowBinding>(itemView)?.apply {
                this.viewModel = viewModels
                this.courseList = courseList
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CourseRowBinding.inflate(inflater, parent, false)
        return CourseViewHolder(binding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(items[position])
    }
}