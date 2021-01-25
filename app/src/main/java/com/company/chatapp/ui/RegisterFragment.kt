package com.company.chatapp.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.company.chatapp.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth
    private val reference: DatabaseReference? = null

    private var username: EditText? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var button_register: Button? = null

    var txt_username: String? = null
    var txt_email: String? = null
    var txt_password: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.editTextUsername)
        email = view.findViewById(R.id.editTextEmail)
        password = view.findViewById(R.id.editTextPassword)
        button_register = view.findViewById(R.id.buttonRegister)

        button_register?.setOnClickListener {
            txt_username = username?.text.toString()
            txt_email = email?.text.toString()
            txt_password = password?.text.toString()
        }
        auth = FirebaseAuth.getInstance()

        return view
    }


    private fun register(userName:String, password:String, email:String){
        auth?.createUserWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener {

        })
    }
    private fun createAccount(email: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validate()) {
            return
        }
    }

    private fun validate(): Boolean {
        var valid: Boolean = true

        if (txt_username?.isEmpty() == true || txt_username?.length!! < 3) {
            username?.setError("at least 3 characters")
            valid = false
        } else {
            username?.setError(null)
        }

        if (txt_password?.isEmpty() == true || txt_password?.length!! < 4
                || txt_password?.length!! > 10) {
            password?.setError("between 4 and 10 alphanumeric characters")
            valid = false
        } else {
            password?.setError(null)
        }

        if (txt_email?.isEmpty() == true || android.util.Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
            email?.setError("enter a valid email address")
            valid = false
        } else {
            email?.setError(null)
        }

        return valid

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RegisterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}