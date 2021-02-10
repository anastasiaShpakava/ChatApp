package com.company.chatapp.ui


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.company.chatapp.MainActivity
import com.company.chatapp.R
import com.google.firebase.auth.FirebaseAuth
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.editTextUsername)
        email = view.findViewById(R.id.editTextEmail)
        password = view.findViewById(R.id.editTextPassword)
        button_register = view.findViewById(R.id.buttonRegister)

        toolbar = view.findViewById(R.id.toolbar)
        activity?.setActionBar(toolbar)
        activity?.setTitle("Registration")

        auth = FirebaseAuth.getInstance()

        button_register?.setOnClickListener {
            txtUsername = username?.text.toString()
            txtEmail = email?.text.toString()
            txtPassword = password?.text.toString()
            if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                Toast.makeText((activity as MainActivity), "All fields are required", Toast.LENGTH_LONG).show()
            } else if (txtPassword?.length!! < 6) {
                Toast.makeText((activity as MainActivity), "Password must be ar least 6 characters", Toast.LENGTH_LONG).show()

            } else {
                register(txtPassword!!, txtEmail!!)
            }
        }

        return view
    }


    private fun register(password: String, email: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((activity as MainActivity)) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val userId = user?.uid.toString()
                reference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)
                val map = mapOf("id" to userId, "username" to username.toString(), "imageURL" to "default")
                reference?.setValue(map)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        activity?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.frameLayout, SecondFragment())?.commit()
                    }
                }
            } else {
                Toast.makeText(context, "You can't register with these email and password", Toast.LENGTH_LONG).show()
            }
        }
    }
}