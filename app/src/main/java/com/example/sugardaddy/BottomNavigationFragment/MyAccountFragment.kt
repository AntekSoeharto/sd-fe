package com.example.sugardaddy.BottomNavigationFragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.sugardaddy.BottomNavigation
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.Helper.MappingHelper
import com.example.sugardaddy.R
import com.example.sugardaddy.SignInActivity
import com.example.sugardaddy.db.UserHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MyAccountFragment : Fragment(), View.OnClickListener {

    private lateinit var btnLogOut: Button
    private lateinit var tvName: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvBirthDate: TextView
    private lateinit var userHelper: UserHelper

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

        tvName = view.findViewById(R.id.tv_name)
        tvUserName = view.findViewById(R.id.tv_username)
        tvEmail = view.findViewById(R.id.tv_email)
        tvGender = view.findViewById(R.id.tv_gender)
        tvBirthDate = view.findViewById(R.id.tv_birth_of_date)
        activity?.let { UserHelper(it.applicationContext).also { userHelper = it } }

        btnLogOut.setOnClickListener(this)
        loadAccountInfo()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_logout -> {
                val intent = Intent(activity, SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                userHelper.clearAllhelp()
                startActivity(intent)
            }
        }
    }

    private fun loadAccountInfo(){
        lifecycleScope.launch {
            val noteHelper = activity?.let { UserHelper.getInstance(it.applicationContext) }
            if (noteHelper != null) {
                noteHelper.open()
            }
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = noteHelper?.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val users = deferredNotes.await()
            for (i in 0 until users.size){
                if (i == 0){
                    Log.e("Debuh : ", "${users.get(i).nama}")
                    tvName.text = users.get(i).nama
                    tvUserName.text = users.get(i).username
                    tvEmail.text = users.get(i).email
                    tvGender.text = users.get(i).gender
                    tvBirthDate.text = users.get(i).birthDay
                }
            }
            if (noteHelper != null) {
                noteHelper.close()
            }
        }
    }
}
