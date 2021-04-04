package com.si7ateck.dz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.si7ateck.dz.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this

        var email = ""
        var passwd = ""

        _binding!!.fragmentLoginLogin.setOnClickListener {
            email = _binding!!.fragmentLoginEmail.text.toString()
            passwd = _binding!!.fragmentLoginPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(activity, "enter your full credentials", Toast.LENGTH_SHORT)
                    .show()
            } else {
//                auth.signInWithEmailAndPassword(email, passwd)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Log.d("tag", "yes")
//                            Toast.makeText(activity, "login done", Toast.LENGTH_SHORT).show()
//                            findNavController()
//                                .navigate(R.id.action_loginFragment_to_loggedinFragment)
//                        } else {
//                            Log.d("tag", task.exception!!.message.toString())
//                        }
//                    }
            }
        }

//        _binding!!.fragmentLoginRegister.setOnClickListener {
//            findNavController()
//                .navigate(R.id.action_loginFragment_to_registerFragment)
//        }

        return binding.root
    }

}