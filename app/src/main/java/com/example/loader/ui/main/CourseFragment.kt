package com.example.loader.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loader.R
import com.example.loader.data.Repository
import com.example.loader.databinding.FragmentCourseBinding
import com.example.loader.ui.main.list.CourseListAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CourseFragment() : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val repository: Repository by instance()
    private val factory by instance<CourseViewModelFactory>()
    private lateinit var binding: FragmentCourseBinding
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(CourseViewModel::class.java)
        binding = FragmentCourseBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewModel.load()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvs()
    }



    private fun setupRvs() {

        val adapter = CourseListAdapter(viewModel)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.listView)
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = llm;

        viewModel.course.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })



    }


}