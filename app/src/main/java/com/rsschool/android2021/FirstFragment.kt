package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var onFragmentSendRangeDataListener: OnFragmentSendRangeDataListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentSendRangeDataListener){
            onFragmentSendRangeDataListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val min: EditText = view.findViewById(R.id.min_value)
        val max: EditText = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            onFragmentSendRangeDataListener?.sendRangeData(min.text.toString().toInt(),max.text.toString().toInt())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    interface OnFragmentSendRangeDataListener{
        fun sendRangeData(min: Int, max: Int)
    }
}

