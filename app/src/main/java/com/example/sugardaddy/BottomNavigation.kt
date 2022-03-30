package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sugardaddy.BottomNavigationFragment.DramaFragment
import com.example.sugardaddy.BottomNavigationFragment.FilmFragment
import com.example.sugardaddy.BottomNavigationFragment.MyAccountFragment
import com.example.sugardaddy.BottomNavigationFragment.MyListFragment
import com.example.sugardaddy.databinding.ActivityBottomNavigationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation : AppCompatActivity() {

    private val dramaFragment = DramaFragment()
    private val filmFragment = FilmFragment()
    private val myListFragment = MyListFragment()
    private val myAccountFragment = MyAccountFragment()

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var buttonNavView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentFragment(dramaFragment)

        buttonNavView = binding.bottomNavigation

        supportActionBar?.title = "Drama"

        buttonNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_drama ->{
                    currentFragment(dramaFragment)
                    supportActionBar?.title = "Drama"
                }
                R.id.ic_film ->{
                    currentFragment(filmFragment)
                    supportActionBar?.title = "Film"
                }
                R.id.ic_my_list ->{
                    currentFragment(myListFragment)
                    supportActionBar?.title = "my List"

                }
                R.id.ic_my_account ->{
                    currentFragment(myAccountFragment)
                    supportActionBar?.title = "My Account"

                }
            }
            true
        }
    }

    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_cntainer, fragment)
            commit()
        }
    }
}