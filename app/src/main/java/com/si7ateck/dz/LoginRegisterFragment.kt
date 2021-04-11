package com.si7ateck.dz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser


class LoginRegisterFragment : Fragment() {

    private val viewModel: LoginRegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.login_register_fragment, container, false)

        viewModel.getUserLiveData()
            ?.observe(viewLifecycleOwner,
                { firebaseUser: FirebaseUser? ->
                    if (firebaseUser != null) {
                        findNavController()
                            .navigate(R.id.action_loginRegisterFragment_to_loggedinFragment)
                    }
                })
        var email = ""
        var passwd = ""

        val loginButton = view.findViewById<Button>(R.id.fragment_loginregister_login)
        val registerButton = view.findViewById<TextView>(R.id.fragment_loginregister_register)

        loginButton.setOnClickListener {
            email = view.findViewById<EditText>(R.id.full_name).text.toString()
            passwd =
                view.findViewById<EditText>(R.id.password).text.toString()
            if (email.isNotEmpty() && passwd.isNotEmpty()) {
                viewModel.login(email, passwd)
            } else {
                Toast.makeText(
                    context,
                    "Email Address and Password Must Be Entered",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginRegisterFragment_to_registerFragment)
        }

        return view
    }


}