package com.example.kreditbee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kreditbee.adapter.DateAdapter
import com.example.kreditbee.databinding.FragmentDynamicBinding
import com.example.kreditbee.networks.resopnse.AlbumIdResponse1
import com.example.kreditbee.resopnse.AlbumResponse
import com.example.kreditbee.resopnse.responseStatus.Status
import com.example.kreditbee.viewModel.AlbumIdViewModel
import com.example.kreditbee.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DynamicFragment : Fragment() {

    var binding : FragmentDynamicBinding? =null
    private lateinit var viewModel : AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        binding = FragmentDynamicBinding.inflate(inflater, container, false)

        val albumData = viewModel.getDataForAlbum(getArguments()?.getInt("position").toString())
//        Toast.makeText(context,getArguments()?.getInt("position").toString(),Toast.LENGTH_SHORT).show()

        albumData.observe(viewLifecycleOwner, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    it.data?.let { it1 -> setData(it1) }

                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        })

        return binding!!.root
    }

    private fun setData(data : AlbumIdResponse1){
        val dataAdapter = DateAdapter(data)
        binding!!.albumData.adapter = dataAdapter
    }

    companion object {
        fun newInstance(): DynamicFragment {
            return DynamicFragment()
        }
    }
}