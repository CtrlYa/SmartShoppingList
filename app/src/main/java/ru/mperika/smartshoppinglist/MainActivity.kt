package ru.mperika.smartshoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartshoppinglist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import ru.mperika.smartshoppinglist.ui.main.shopping_list.ShoppingListFragment

class MainActivity : AppCompatActivity() {
    lateinit var activeFragment: Fragment
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.allItemsFragment, R.id.shoppingListFragment),
            drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_all_items -> {
                    Toast.makeText(
                        applicationContext,
                        "Элемент меню - ВСЕ ЭЛЕМЕНТЫ",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(R.id.allItemsFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.menu_shop_list -> {
                    Toast.makeText(
                        applicationContext,
                        "Элемент меню - СПИСОК ПОКУПОК",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(R.id.shoppingListFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.menu_fridge -> {
                    Toast.makeText(applicationContext, "Элемент меню - ХОЛОДОС", Toast.LENGTH_SHORT)
                        .show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }

        }
//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//
//
//        val tabView = findViewById<TabLayout>(R.id.tabs)
//        TabLayoutMediator(tabView, viewPager) { tab, position ->
//            tab.text = TAB_TITLES[position]
//        }.attach()
//
//
        val fab: FloatingActionButton = findViewById(R.id.fab)
        var counter = 0
        fab.setOnClickListener { view ->
//            if (viewPager.currentItem == 1) {
//                counter++
//                val myFragment = supportFragmentManager.findFragmentByTag("f" + viewPager.currentItem)
//                myFragment?.let {
//                    val intent = Intent(it.context, ProductEditActivity::class.java)
//                    startActivityForResult(intent, ActivityCodes.CREATE.id)
//                }
//            } else {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show()
//            }
            Snackbar.make(view, "Fragment is: ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()

        }
//
//        tabView.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                activeFragment  = supportFragmentManager.findFragmentByTag("f" + viewPager.currentItem)!!
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ActivityCodes.CREATE.id) {
            if (resultCode == Activity.RESULT_OK) {
                notifySuccess(activeFragment)
            }
        }
    }

    private fun notifySuccess(fragment: Fragment) {
        if (fragment is ShoppingListFragment) {
            fragment.updateAll()
        }
    }
}