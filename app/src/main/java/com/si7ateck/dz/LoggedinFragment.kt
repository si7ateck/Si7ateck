package com.si7ateck.dz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


class LoggedinFragment : Fragment() {

    private val viewModel: LoggedinViewMode by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_loggedin, container, false)

        var loggedInUserTextView: TextView =
            view.findViewById<TextView>(R.id.fragment_loggedin_loggedInUser)
        val logOutButton: Button = view.findViewById<Button>(R.id.fragment_loggedin_logOut)
        viewModel.getUserLiveData()?.observe(viewLifecycleOwner,
            { firebaseUser ->
                if (firebaseUser != null) {
                    loggedInUserTextView.text = "Logged In User: ${firebaseUser.email} "
                    logOutButton.isEnabled = true
                } else {
                    logOutButton.isEnabled = false
                }
            })

        viewModel.getLoggedOutLiveData()?.observe(viewLifecycleOwner,
            { loggedOut ->
                if (loggedOut) {
                    Toast.makeText(context, "User Logged Out", Toast.LENGTH_SHORT).show()
                    findNavController()
                        .navigate(R.id.action_loggedinFragment_to_loginRegisterFragment)
                }
            })


        logOutButton.setOnClickListener { viewModel.logOut() }


        return view
    }
}