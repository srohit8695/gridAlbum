package com.example.kreditbee.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.kreditbee.resopnse.AlbumResponse
import com.example.kreditbee.ui.DynamicFragment


class DynamicFragmentAdapter internal constructor(
    fm: FragmentManager?,
    private val mNumOfTabs: Int,
    private val data : AlbumResponse
) :
    FragmentStatePagerAdapter(fm!!) {
    // get the current item with position number
    override fun getItem(position: Int): Fragment {
        val b = Bundle()
        val albumId = data[position].id
        b.putInt("position", albumId!!)
        val frag: Fragment = DynamicFragment.newInstance()
        frag.setArguments(b)
        return frag
    }

    // get total number of tabs
    override fun getCount(): Int {
        return mNumOfTabs
    }
}

