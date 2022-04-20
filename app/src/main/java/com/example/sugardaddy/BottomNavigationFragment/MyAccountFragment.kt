package com.example.sugardaddy.BottomNavigationFragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.sugardaddy.BottomNavigation
import com.example.sugardaddy.Entity.User
import com.example.sugardaddy.Helper.MappingHelper
import com.example.sugardaddy.R
import com.example.sugardaddy.SignInActivity
import com.example.sugardaddy.Helper.UserSingleton
import com.example.sugardaddy.db.DatabaseContract
import com.example.sugardaddy.db.DatabaseContract.UserColumn.Companion.PASSWORD
import com.example.sugardaddy.db.FilmHelper
import com.example.sugardaddy.db.UserHelper
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception
import kotlin.properties.Delegates


class MyAccountFragment : Fragment(), View.OnClickListener {

    private lateinit var btnLogOut: Button
    private lateinit var tvName: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvBirthDate: TextView
    private lateinit var userHelper: UserHelper
    private lateinit var filmHelper: FilmHelper
    private lateinit var btnSave: Button
    private lateinit var edtNewPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private var idTemp by Delegates.notNull<Int>()

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
        btnSave = view.findViewById(R.id.btn_save)

        tvName = view.findViewById(R.id.tv_name)
        tvUserName = view.findViewById(R.id.tv_username)
        tvEmail = view.findViewById(R.id.tv_email)
        tvGender = view.findViewById(R.id.tv_gender)
        tvBirthDate = view.findViewById(R.id.tv_birth_of_date)
        edtNewPassword = view.findViewById(R.id.newPassword)
        edtConfirmPassword = view.findViewById(R.id.confirmPassword)
        activity?.let { UserHelper(it.applicationContext).also { userHelper = it } }
        activity?.let { FilmHelper(it.applicationContext).also { filmHelper = it } }

        btnLogOut.setOnClickListener(this)
        btnSave.setOnClickListener(this)

        loadAccountInfo()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_logout -> {
                val intent = Intent(activity, SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                userHelper.clearAllUserhelp()
                filmHelper.clearAllFilmhelp()
                UserSingleton.user = User()
                startActivity(intent)
            }
            R.id.btn_save ->{
                if(edtNewPassword.text.toString() == edtConfirmPassword.text.toString()){
                    verifyUpdatePassword()
                }else{
                    Toast.makeText(activity, "Password Not Same With Confirm", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyUpdatePassword(){
        val uesrHelper = activity?.let { UserHelper.getInstance(it.applicationContext) }
        userHelper.open()
        val values = ContentValues()
        values.put(PASSWORD, edtNewPassword.text.toString())
        userHelper.update(idTemp.toString(), values)
        userHelper.close()
        UpdateApiPassword()

    }

    private fun UpdateApiPassword(){
        val client = AsyncHttpClient()

        val param = "email="+tvEmail.text.toString()+"&password="+edtNewPassword.text.toString()
        val url = "https://sd-heroku.herokuapp.com/user?" + param
//        Log.e("DeBug", "$url")
        client.put(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)

                try {
                    val user: User = User()
                    val responseObject = JSONObject(result)
                    val status = responseObject.getInt("status")
                    if(status == 200){
                        Toast.makeText(activity, "Success Change Password", Toast.LENGTH_SHORT).show()
                        edtConfirmPassword.text.clear()
                        edtNewPassword.text.clear()
                    }else{
                        Toast.makeText(activity, "Failed Change Password", Toast.LENGTH_SHORT).show()
                    }


                } catch (e: Exception){
                    Log.e("Error", "$e")
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Log.e("Error", "$statusCode")
            }

        })
    }

    private fun loadAccountInfo(){
        lifecycleScope.launch {
            val noteHelper = activity?.let { UserHelper.getInstance(it.applicationContext) }
            if (noteHelper != null) {
                noteHelper.open()
            }
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = noteHelper?.queryAll()
                MappingHelper.mapUserCursorToArrayList(cursor)
            }
            val users = deferredNotes.await()
            for (i in 0 until users.size){
                if (i == 0){
                    Log.e("Debuh : ", "${users.get(i).nama}")
                    idTemp = users.get(i).id
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
