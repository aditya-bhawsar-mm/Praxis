package com.mutualmobile.praxis.ui.fragments.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.FragmentAboutDialogBinding

class AboutDialogFragment : DialogFragment() {

    private var _binding: FragmentAboutDialogBinding? = null
    private val binding : FragmentAboutDialogBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_dialog, container, false)
        binding.mutualMobileWebLink.movementMethod = LinkMovementMethod.getInstance()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}