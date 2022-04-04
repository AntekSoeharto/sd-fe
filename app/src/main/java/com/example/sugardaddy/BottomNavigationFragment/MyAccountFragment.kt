package com.example.sugardaddy.BottomNavigationFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sugardaddy.BottomNavigation
import com.example.sugardaddy.R
import com.example.sugardaddy.SignInActivity


class MyAccountFragment : Fragment(), View.OnClickListener {

    private lateinit var btnLogOut: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogOut = view.findViewById(R.id.btn_logout)

        btnLogOut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(activity, SignInActivity::class.java)
        startActivity(intent)
        (activity as Activity?)!!.overridePendingTransition(0, 0)
    }
}