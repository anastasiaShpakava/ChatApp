package com.company.chatapp.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.company.chatapp.MainActivity
import com.company.chatapp.R
import com.company.chatapp.viewmodel.LoginRegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var reference: DatabaseReference? = null

    private var username: EditText? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var button_register: Button? = null
    private var toolbar: Toolbar? = null

    private var txtUsername: String? = null
    private var txtEmail: String? = null
    private var txtPassword: String? = null
    private var loginRegisterViewModel:LoginRegisterViewModel?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        loginRegisterViewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)
        loginRegisterViewModel?.mutableLiveData?.observe(viewLifecycleOwner, { t ->
            if (t!=null){
                Toast.makeText((activity as MainActivity), "User created", Toast.LENGTH_LONG).show()
            }
        })

auth = FirebaseAuth.getInstance()
        username = view.findViewById(R.id.editTextUsername)
        email = view.findViewById(R.id.editTextEmail)
        password = view.findViewById(R.id.editTextPassword)
        button_register = view.findViewById(R.id.buttonRegister)

        toolbar = view.findViewById(R.id.toolbar)
        activity?.setActionBar(toolbar)
        activity?.setTitle("Registration")


        button_register?.setOnClickListener {

            txtUsername = username?.text.toString()
            txtEmail = email?.text.toString()
            txtPassword = password?.text.toString()
            if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                Toast.makeText((activity as MainActivity), "All fields are required", Toast.LENGTH_LONG).show()
            } else if (txtPassword?.length!! < 6) {
                Toast.makeText((activity as MainActivity), "Password must be ar least 6 characters", Toast.LENGTH_LONG).show()

            } else {
                loginRegisterViewModel?.register(txtPassword!!, txtEmail!!)

//                val userId = auth.currentUser!!.uid //TODO
//                reference = FirebaseDatabase.getInstance().getReference("Users").child(userId)
//                val map = mapOf("id" to userId, "username" to auth.currentUser!!.displayName, "imageURL" to "default")
  //              reference?.setValue(map)?.addOnCompleteListener { task ->
 //                   if (task.isSuccessful) {

                        val navController = findNavController()
                        navController.navigate(R.id.secondFragment)

  //                  }
                }
            }
     //   }

        return view
    }
}