package com.akrwt.innerwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

/**
 * A simple [Fragment] subclass.
 */
class ResumeFragment : Fragment() {
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resume, container, false)
        spinner = view.findViewById(R.id.spinner)

        val monthsArray = resources.getStringArray(R.array.months_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, monthsArray)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
               // m = monthsArray[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        return view
    }
}
