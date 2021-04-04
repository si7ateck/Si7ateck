package com.si7ateck.dz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.si7ateck.dz.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    private val viewModel: LoginRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        auth = FirebaseAuth.getInstance()
        var email = ""
        var passwd = ""
        Log.d("tagtag", "yesyes $email $passwd $auth")


        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this

        _binding!!.register.setOnClickListener {
            email = _binding!!.email.text.toString()
            passwd = _binding!!.password.text.toString()
            Log.d("tagtag", "yesyes $email $passwd $auth")

            if (email.isNotEmpty() && passwd.isNotEmpty()) {
                viewModel.register(email, passwd)
            } else {
                Toast.makeText(
                    context,
                    "Email Address and Password Must Be Entered",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        _binding!!.goBack.setOnClickListener() {
            findNavController().navigate(R.id.action_registerFragment_to_loginRegisterFragment)
        }


        return binding.root
    }
}