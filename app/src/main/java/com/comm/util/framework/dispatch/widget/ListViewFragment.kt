package com.comm.util.framework.dispatch.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.comm.util.R
import kotlinx.android.synthetic.main.fragment_list_view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListViewFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListData()
    }

    private fun initListData() {
        val dataList: MutableList<String?> = ArrayList()
        for (i in 0..29) {
            dataList.add("tom$i")
        }
//        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(context, android.R.layout.simple_list_item_1, android.R.id.text1, dataList as List<Any?>?)
//        list_view.setAdapter(arrayAdapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
                ListViewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}