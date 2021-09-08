package com.mutualmobile.praxis.ui.fragments.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.FragmentJokeBinding

class JokeFragment : Fragment(R.layout.fragment_joke) {

    private val args by navArgs<JokeFragmentArgs>()

    private var _binding: FragmentJokeBinding? = null
    private val binding: FragmentJokeBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DataBindingUtil.bind(view)

        binding.apply { jokeTV.text = args.jokes }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    companion object {
        const val JOKE_LIST_INTENT = "Joke_list_intent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jokeList = intent.getParcelableArrayListExtra<Joke>(JOKE_LIST_INTENT)
        binding!!.lifecycleOwner = this
        viewModel!!.showJoke(jokeList)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding!!.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        ActivityNavigator.finishActivityWithAnimation(R.anim.slide_right_in, R.anim.slide_right_out, this)
    }*/

}