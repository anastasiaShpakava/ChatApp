package com.company.chatapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu

import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.company.chatapp.MainActivity
import com.company.chatapp.R
import com.company.chatapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView

class CurrentUserActivity : AppCompatActivity() {
    private var profile_image: CircleImageView? = null
    private var username: TextView? = null
    private var toolbar: Toolbar? = null

    private var firebaseUser: FirebaseUser? = null
    private var reference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_user)

        toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)
        setTitle("")

        profile_image = findViewById(R.id.profile_image)
        username = findViewById(R.id.user_name)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser!!.uid)

        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user: User? = dataSnapshot.getValue(User::class.java)
                username?.setText(user?.username)
                if (user?.imageURL.equals("default")){
                    profile_image?.setImageResource(R.mipmap.ic_launcher)
                }else{
                    Glide.with(this@CurrentUserActivity).load(user?.imageURL).into(profile_image!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.logout) {
            FirebaseAuth.getInstance().signOut()
var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }
}

