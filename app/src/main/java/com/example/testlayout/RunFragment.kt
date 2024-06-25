package com.example.testlayout

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RunFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RunFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var stopwatch : Chronometer
    private lateinit var startStop : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_run, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startStop = requireView().findViewById(R.id.run_start_stop_button)
        stopwatch = requireView().findViewById(R.id.run_stopwatch)
        startStop.setOnClickListener {
            if (startStop.text == "START") {
                resetChrono()
                stopwatch.start()
                startStop.text = "STOP"
            } else if (startStop.text == "STOP") {
                stopwatch.stop()
                startStop.text = "START"
            }
        }
    }

    fun resetChrono( ) {
        var chronoText : String = stopwatch.text.toString( )
        var idleMilliseconds : Long = ClockUtility.milliseconds(
            chronoText )
        stopwatch.base = SystemClock.elapsedRealtime() - idleMilliseconds
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RunFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RunFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}