package com.company.chatapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.company.chatapp.MainActivity
import com.company.chatapp.R
import com.company.chatapp.viewmodel.LoginRegisterViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class LoginFragment : Fragment() {

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

    private var loginRegisterViewModel: LoginRegisterViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.editTextUsername)
        email = view.findViewById(R.id.editTextEmail)
        password = view.findViewById(R.id.editTextPassword)
        button_register = view.findViewById(R.id.buttonRegister)

        toolbar = view.findViewById(R.id.toolbar)
        activity?.setActionBar(toolbar)
        activity?.setTitle("Login")

        auth = FirebaseAuth.getInstance()
        loginRegisterViewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel::class.java)
        loginRegisterViewModel?.mutableLiveData?.observe(viewLifecycleOwner) { t ->
            if (t != null) {
                getView()?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_loginFragment_to_currentUserFragment)
                }
            }
        }
        button_register?.setOnClickListener {
            txtUsername = username?.text.toString()
            txtEmail = email?.text.toString()
            txtPassword = password?.text.toString()
            if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(
                    txtPassword
                )
            ) {
                Toast.makeText(
                    (activity as MainActivity),
                    "All fields are required",
                    Toast.LENGTH_LONG
                ).show()
            } else if (txtPassword?.length!! < 6) {
                Toast.makeText(
                    (activity as MainActivity),
                    "Password must be ar least 6 characters",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                loginRegisterViewModel?.login(txtPassword!!, txtEmail!!)
            }
        }

        return view
    }
}