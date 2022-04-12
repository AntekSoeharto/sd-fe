package com.example.sugardaddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.sugardaddy.BottomNavigationFragment.DramaFragment
import com.example.sugardaddy.BottomNavigationFragment.FilmFragment
import com.example.sugardaddy.BottomNavigationFragment.MyAccountFragment
import com.example.sugardaddy.BottomNavigationFragment.MyListFragment
import com.example.sugardaddy.databinding.ActivityBottomNavigationBinding
import com.example.sugardaddy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation : AppCompatActivity() {

    private val dramaFragment = DramaFragment()
    private val filmFragment = FilmFragment()
    private val myListFragment = MyListFragment()
    private val myAccountFragment = MyAccountFragment()

    private lateinit var binding: ActivityBottomNavigationBinding
    private lateinit var buttonNavView : BottomNavigationView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        currentFragment(dramaFragment)

        buttonNavView = binding.bottomNavigation
        searchView = binding.searchView

        val listDramaFilm = arrayOf("Batman","DOTS","Spider-Man","Naruto","Doraemon","Power Ranger","Kamen Rider","Avengers End-Game")

        val listAdapter : ArrayAdapter<String> = ArrayAdapter(
            this,android.R.layout.simple_list_item_1,listDramaFilm
        )

        binding.dramaFilmList.adapter = listAdapter;

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (listDramaFilm.contains(query)){

                    listAdapter.filter.filter(query)

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })

        supportActionBar?.hide()

        buttonNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_drama -> {
                    currentFragment(dramaFragment)
                    binding.searchView.visibility = View.VISIBLE
                    binding.dramaFilmList.visibility = View.VISIBLE
                }
                R.id.ic_film -> {
                    currentFragment(filmFragment)
                    binding.searchView.visibility = View.VISIBLE
                    binding.dramaFilmList.visibility = View.VISIBLE
                }
                R.id.ic_my_list -> {
                    currentFragment(myListFragment)
                    binding.searchView.visibility = View.GONE
                    binding.dramaFilmList.visibility = View.GONE
                }
                R.id.ic_my_account -> {
                    currentFragment(myAccountFragment)
                    binding.searchView.visibility = View.GONE
                    binding.dramaFilmList.visibility = View.GONE
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