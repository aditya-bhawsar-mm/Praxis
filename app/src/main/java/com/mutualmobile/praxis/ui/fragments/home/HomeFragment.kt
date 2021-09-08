package com.mutualmobile.praxis.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.FragmentHomeBinding
import com.mutualmobile.praxis.utils.ResponseWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DataBindingUtil.bind(view)

        binding.apply {
            randomJokesButtonCoroutine.setOnClickListener { viewModel.loadDataRx() }
            randomJokesButtonRx.setOnClickListener { viewModel.loadCoroutineData() }
            aboutButton.setOnClickListener { showAboutFragment() }
        }

        viewModel.jokesData.observe(viewLifecycleOwner){ responded->
            when(responded){
                is ResponseWrapper.Waiting->{}
                is ResponseWrapper.Loading->{
                    handleDataLoadingUi(true)
                }
                is ResponseWrapper.Success->{
                    handleDataLoadingUi(false)

                    viewModel.resetResponse()
                }
                is ResponseWrapper.Error->{
                    handleDataLoadingUi(false)
                    viewModel.resetResponse()
                }
            }
        }
    }

    private fun handleDataLoadingUi(loading: Boolean) {
        binding.apply {
            progressbar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
            randomJokesButtonCoroutine.isEnabled = !loading
            randomJokesButtonRx.isEnabled = !loading
            aboutButton.isEnabled = !loading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showJokeActivity(bundle: Bundle) {

        ActivityNavigator.startActivityWithDataAndAnimation(ShowJokeActivity::class.java, bundle, R.anim.slide_left_in, R.anim.slide_left_out, this)
    }

    private fun showAboutFragment() { findNavController().navigate(R.id.action_homeFragment_to_aboutDialog) }
}