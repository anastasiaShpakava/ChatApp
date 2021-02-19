package com.company.chatapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toolbar


import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.company.chatapp.R
import com.company.chatapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView


class CurrentUserFragment : Fragment() {
    private var profile_image: CircleImageView? = null
    private var username: TextView? = null
    private var toolbar: Toolbar? = null

    private var firebaseUser: FirebaseUser? = null
    private var reference: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_current_user, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        activity?.setActionBar(toolbar)
        activity?.setTitle("")

        profile_image = view.findViewById(R.id.profile_image)
        username = view.findViewById(R.id.user_name)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser!!.uid)

        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user: User? = dataSnapshot.getValue(User::class.java)
                username?.setText(user?.username)
                if (user?.imageURL.equals("default")){
                    profile_image?.setImageResource(R.mipmap.ic_launcher)
                }else{
                    Glide.with(this@CurrentUserFragment).load(user?.imageURL).into(profile_image!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       if(item.itemId==R.id.logout){
           FirebaseAuth.getInstance().signOut()
           val navController = findNavController()
           navController.navigate(R.id.mainFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}