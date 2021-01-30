package ru.mperika.smartshoppinglist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.ui.main.SectionsPagerAdapter
import ru.mperika.smartshoppinglist.ui.main.TAB_TITLES
import ru.mperika.smartshoppinglist.ui.main.shopping_list.ShoppingListFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.mperika.smartshoppinglist.ui.edit.ProductEditActivity

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabView = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabView, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position].toString()
        }.attach()
        val fab: FloatingActionButton = findViewById(R.id.fab)
        var counter = 0
        fab.setOnClickListener { view ->
            if (viewPager.currentItem == 1) {
                counter++
                val myFragment = supportFragmentManager.findFragmentByTag("f" + viewPager.currentItem)
                myFragment?.let {
//                    (it as ShoppingListFragment).addData("Элемент списка $counter")
                    var intent = Intent(it.context, ProductEditActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
            Snackbar.make(view, "Fragment is: ${viewPager.currentItem}", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()

        }
    }
}