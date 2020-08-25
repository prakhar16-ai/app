package com.akrwt.innerwork

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class InternshipFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var internshipAdapter: InternshipsAdapter
    private lateinit var internshipList: ArrayList<Internship>
    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_internships, container, false)
        radioGroup = v.findViewById(R.id.radio_grp)
        setHasOptionsMenu(true)

        internshipList = ArrayList()
        for (i in 0 until 5) {
            internshipList.add(
                Internship(
                    R.drawable.innerwork_logo, "Android Developer",
                    "Innerwork Solution Pvt. Lmt.",
                    "Unpaid",
                    "Work From Home",
                    "6 months",
                    "Internship"
                )
            )
        }
        for (i in 0 until 5) {
            internshipList.add(
                Internship(
                    R.drawable.innerwork_logo, "Android Developer",
                    "Innerwork Solution Pvt. Lmt.",
                    "Unpaid",
                    "Work From Home",
                    "6 months",
                    "Job"
                )
            )
        }
        internshipList.add(
            Internship(R.drawable.ic_intern, "Sample",
                "Innerwork Solution Pvt. Lmt.",
                "Unpaid",
                "Work From Home",
                "6 months",
                "Job")
        )

        internshipAdapter = InternshipsAdapter(requireContext(), internshipList)

        recyclerView = v.findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = internshipAdapter
            setHasFixedSize(true)
        }

        radioGroup.setOnCheckedChangeListener{group, checked_id->
            val radio: RadioButton = v.findViewById(checked_id)
            val text = radio.text.toString()
            val filteredList = filterInternships(internshipList,text)
            internshipAdapter.setFilter(filteredList)
        }
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)

        val searchView = item.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Search Here"

        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                val filteredList: ArrayList<Internship> = filterCompany(internshipList, newText)
                internshipAdapter.setFilter(filteredList)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    fun filterCompany(p1: ArrayList<Internship>, query: String): ArrayList<Internship> {
        val myQuery = query.toLowerCase(Locale.ENGLISH)
        val filteredList: ArrayList<Internship> = ArrayList()

        for (company: Internship in p1) {
            val text: String = company.name.toLowerCase(Locale.ENGLISH)
            val secondText:String = company.title.toLowerCase(Locale.ENGLISH)
            if (text.startsWith(myQuery) || secondText.startsWith(myQuery)) {
                filteredList.add(company)
            }
        }
        return filteredList
    }
    fun filterInternships(p1: ArrayList<Internship>, query: String): ArrayList<Internship> {
        val myQuery = query.toLowerCase(Locale.ENGLISH)
        val filteredList: ArrayList<Internship> = ArrayList()

        for (internships: Internship in p1) {
            val text: String = internships.jobOrInternship.toLowerCase(Locale.ENGLISH)

            if (text.startsWith(myQuery)) {
                filteredList.add(internships)
            }
        }
        return filteredList
    }
}
