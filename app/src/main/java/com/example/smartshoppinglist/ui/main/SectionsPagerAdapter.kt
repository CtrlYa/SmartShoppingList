package com.example.smartshoppinglist.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smartshoppinglist.R
import com.example.smartshoppinglist.ui.main.fridge.FridgeFragment
import com.example.smartshoppinglist.ui.main.shopping_list.ShoppingListFragment

val TAB_TITLES = arrayOf(
    "Холодос",
    "Список покупок"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when(position) {
            0 -> FridgeFragment.newInstance(position + 1)
            1 -> ShoppingListFragment.newInstance(position + 1)
            else -> FridgeFragment.newInstance(position + 1)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}