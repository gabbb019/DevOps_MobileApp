package com.example.devopsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.devopsproject.API.POST
import com.example.devopsproject.databinding.FragmentLoginBinding
import com.example.devopsproject.databinding.FragmentRegisterBinding
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register,container,false)

        binding.signintext.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSignup.setOnClickListener {
            if (binding.name.text.toString().isNullOrEmpty() || binding.username.text.toString().isNullOrEmpty() || binding.password.text.toString().isNullOrEmpty()){
                val toast = Toast.makeText(getActivity(), "Please fill all fields.", Toast.LENGTH_SHORT)
                toast.show()
            }

            else {
                var name = binding.name.text.toString()
                var uname = binding.username.text.toString()
                var pword = binding.password.text.toString()

                val ref = FirebaseDatabase.getInstance().getReference("accounts")
                val data = POST(name, uname, pword)
                ref.child(uname).setValue(data)
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        return binding.root
    }
}