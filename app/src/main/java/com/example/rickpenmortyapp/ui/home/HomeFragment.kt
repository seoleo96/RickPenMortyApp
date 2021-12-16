package com.example.rickpenmortyapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickpenmortyapp.R
import com.example.rickpenmortyapp.core.App
import com.example.rickpenmortyapp.databinding.FragmentHomeBinding
import com.example.rickpenmortyapp.domain.model.CharacterDomainModel
import com.example.rickpenmortyapp.ui.UIState


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private var adapter: RickAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        homeViewModel = (requireActivity().application as App).homeViewModel
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        gridLayoutManager = GridLayoutManager(requireContext(), ONE)
        adapter = RickAdapter(gridLayoutManager!!)
        binding.rv.layoutManager = gridLayoutManager
        binding.rv.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
        toSearch()
        changeView()
    }

    private fun changeView() {
        binding.apply {
            changeView.setOnClickListener {
                if (gridLayoutManager?.spanCount == ONE) {
                    gridLayoutManager?.spanCount = TWO
                    it.setBackgroundResource(R.drawable.ic_baseline_list_24)
                } else {
                    gridLayoutManager?.spanCount = ONE
                    it.setBackgroundResource(R.drawable.ic_dashboard_black_24dp)
                }
                adapter?.itemCount?.let { it1 -> adapter?.notifyItemRangeChanged(0, it1) }
            }
        }
    }

    private fun toSearch() {
        binding.searchView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_searchDialogFragment)
        }
    }


    private fun subscribeUI() {
        homeViewModel.fetchData()
        homeViewModel.observe(viewLifecycleOwner, { listUIState ->
            listUIState.map { state ->
                when (state) {
                    is UIState.Error -> showError(state.id)
                    is UIState.Success -> showContent(state.data)
                    is UIState.Progress -> showProgress()
                }
            }
        })
    }

    private fun showContent(data: List<CharacterDomainModel>) {
        if (data.isEmpty()){
            showError(R.string.empty_cache)
            return
        }
        adapter?.updateList(data)
        binding.apply {
            rv.isVisible = true
            errorText.isGone = true
            progressBar.isGone = true
            infoList.text = data.size.toString()
            searchView.isVisible = true
            textView.isVisible = true
            infoList.isVisible = true
            changeView.isVisible = true
        }
    }

    private fun showError(id: Int) {
        binding.apply {
            rv.isGone = true
            searchView.isGone = true
            textView.isGone = true
            infoList.isGone = true
            changeView.isGone = true
            progressBar.isInvisible = true
            errorText.text = requireContext().getString(id)
            errorText.isVisible = true
        }
    }

    private fun showProgress() {
        binding.apply {
            rv.isGone = true
            errorText.isInvisible = true
            progressBar.isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}