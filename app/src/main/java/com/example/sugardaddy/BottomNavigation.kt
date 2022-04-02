package com.example.sugardaddy

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.marginTop
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

        supportActionBar?.hide()

        buttonNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_drama -> {
                    currentFragment(dramaFragment)
                    binding.searchView.visibility = View.VISIBLE
                }
                R.id.ic_film -> {
                    currentFragment(filmFragment)
                    binding.searchView.visibility = View.VISIBLE
                }
                R.id.ic_my_list -> {
                    currentFragment(myListFragment)
                    binding.searchView.visibility = View.GONE
                }
                R.id.ic_my_account -> {
                    currentFragment(myAccountFragment)
                    binding.searchView.visibility = View.GONE
                }
            }
            true
        }

        val dramaFilm = arrayOf(
            "BlackPanther",
            "Spider-Man",
            "Naruto",
        )

        val dramaFilmAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            dramaFilm
        )

        binding.dramaFilmList.adapter = dramaFilmAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (dramaFilm.contains(query)) {

                    dramaFilmAdapter.filter.filter(query)

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dramaFilmAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_cntainer, fragment)
            commit()
        }
    }
}