package com.example.githubrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubrepo.databinding.FragmentAddMessageBinding

class AddMessageFragment : Fragment() {
    private var binding: FragmentAddMessageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMessageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.addMessageButton?.setOnClickListener {
            this.app.eventBus.post(EventBus.EventAddMessage(binding?.addMessageEditText?.text.toString()))
        }


            //простой вариант с просто событием
//        binding?.addMessageButton?.setOnClickListener {
//            myMessage.myMessage = binding?.addMessageEditText?.text.toString()
//            this.app.eventBus.post(EventBus.Event())
//        }
    }
}