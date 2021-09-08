package com.connect.androidinternassignment3.utils;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.connect.androidinternassignment3.ui.fragment.Login
import com.connect.androidinternassignment3.ui.fragment.SignUp


class TabAdapter : FragmentPagerAdapter {

    constructor(fragmentManager: FragmentManager) : super(fragmentManager)

    var fragmentList : ArrayList<Fragment> = arrayListOf()
    var stringList : ArrayList<String> = arrayListOf()

    fun addFragment(fragment:Fragment,s:String){
        fragmentList.add(fragment)
        stringList.add(s)
    }

    override fun getCount(): Int {
        return  fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringList[position]

    }

}
