package com.example.sd_bssd_hw31

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nameView: TextView
    private lateinit var dateView: TextView
    private lateinit var descView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        setFragmentResultListener( "noteDataChange") {requestKey, bundle ->
            val result = bundle.getString( "nameKey")
            nameView.text = result.toString() }

        setFragmentResultListener( "noteDataChange") {requestKey, bundle ->
            val result = bundle.getString( "nameKey")
            dateView.text = result.toString() }

        setFragmentResultListener( "noteDataChange") {requestKey, bundle ->
            val result = bundle.getString( "nameKey")
           descView.text = result.toString() }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nameView = TextView(context).apply{
           setText(R.string.name_placeholder_holder)
        }
        //Beginning text for left side
        dateView = TextView(context).apply{
            setText(R.string.date_placeholder_holder)
        }
        descView = TextView(context).apply{
            setText(R.string.desc_placeholder_holder)
        }

        val textHolderLL = LinearLayoutCompat(requireContext()).apply {
            orientation = LinearLayoutCompat.VERTICAL
            addView(nameView)
            addView(dateView)
            addView(descView)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.ALIGN_PARENT_LEFT)
        }
        //End of text for left Side



        //Edit button for right side
        val editButton = Button(requireContext()).apply {
            text = "Edit"
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.ALIGN_PARENT_RIGHT
            )
            setOnClickListener {
                NoteEditorDialog.newInstance().show(
                    parentFragmentManager, NoteEditorDialog.TAG)
            }
        }
        //End of edit button for right side

        //Delete button for right side

        val deleteButton = Button(requireContext()).apply {
            text = "Delete"
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.ALIGN_PARENT_RIGHT)

            setOnClickListener{
                AlertDialog.Builder(requireContext()).apply{
                    setTitle("Delete")
                    setPositiveButton("Yes", DialogInterface.OnClickListener{
                            dialogInterface, i ->
                        activity?.supportFragmentManager?.commit {
                            remove(this@NoteFragment)
                            dialogInterface.dismiss()
                        }
                    })
                    setNegativeButton("no", null)
                    create()
                    show()
                }
            }
        }

        //End of delete Button for right side

        val relativeLayout = RelativeLayout(requireContext()).apply {
            setBackgroundColor(Color.WHITE)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
            addView(textHolderLL)
            addView(editButton)
            addView(deleteButton)
        }
          return relativeLayout

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            NoteFragment().apply {
                arguments = Bundle().apply {
                   // putString(ARG_PARAM1, param1)
                   // putString(ARG_PARAM2, param2)
                }
            }
    }
}