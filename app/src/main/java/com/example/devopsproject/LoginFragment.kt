package com.example.devopsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.devopsproject.API.ApiInterface
import com.example.devopsproject.API.MyData
import com.example.devopsproject.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {
    var baseURL = "https://devopsproject-1c164-default-rtdb.asia-southeast1.firebasedatabase.app/accounts/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login,container,false)

        binding.btnSignin.setOnClickListener {
            var uname = binding.username.text.toString()
            var url = baseURL + uname + ".json/"
            var retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ApiInterface::class.java)
            val call = service.getData()
            call.enqueue(object : Callback<MyData> {
                override fun onResponse(call: Call <MyData>, response: Response<MyData>){ if (response.code() == 200) {
                    if (response.body().toString() != "null" && response.body()?.name.toString() != "null"){
                        var dataResponse = response.body()!!
                        if(dataResponse.password == binding.password.text.toString()){
                            view?.findNavController()?.navigate(R.id.action_loginFragment_to_mainScreenFragment)
                        }
                    }
                    else{
                        val toast = Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
                }
                override fun onFailure(call: Call<MyData>, t: Throwable) {

                }
            })
        }

        binding.signuptext.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding.root

    }
}