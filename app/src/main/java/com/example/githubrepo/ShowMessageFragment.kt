package com.example.githubrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubrepo.databinding.FragmentShowMessageBinding

class ShowMessageFragment : Fragment() {
    private var binding: FragmentShowMessageBinding? = null
    var myMessage = "Пока сообщений нет."

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowMessageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        disposable = this.app.eventBus.get()
//            .subscribe{
//                binding?.showTextView?.text = "EventBus.Event.myMessage()"
//            }

        App.compositeDisposable.add(this.app.eventBus.listen(EventBus.EventAddMessage::class.java).subscribe {
            myMessage = it.message.toString()
        })

        binding?.showTextView?.text = myMessage
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}