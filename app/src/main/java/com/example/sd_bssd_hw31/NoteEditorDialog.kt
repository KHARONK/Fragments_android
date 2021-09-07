package com.example.sd_bssd_hw31

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class NoteEditorDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val editName = EditText(requireContext()).apply{
            setHint(R.string.name_placeholder_holder)
        }
        val editDate = EditText(requireContext()).apply{
            setHint(R.string.date_placeholder_holder)
        }
        val editDesc = EditText(requireContext()).apply{
            setHint(R.string.desc_placeholder_holder)
        }

        val linearLayout = LinearLayoutCompat(requireContext()).apply{
            orientation = LinearLayoutCompat.VERTICAL
            addView(editName)
            addView(editDesc)
            addView(editDesc)
        }

        val ad = AlertDialog.Builder(requireContext()).apply {
            setTitle("Note Editor")
            setMessage("Edit Content")
            setView(linearLayout)
            setPositiveButton( "Save") {_,_->
                val result = editName.text.toString()
               setFragmentResult("noteDataChange",
                bundleOf("nameKey" to result))}

            setPositiveButton("Save") {_,_->
                val result = editDate.text.toString()
                setFragmentResult( "noteDateChange",
                bundleOf("nameKey" to result))}

            setPositiveButton("Save") {_,_->
                val result = editDesc.text.toString()
                setFragmentResult( "noteDateChange",
                    bundleOf("nameKey" to result))}

            setNegativeButton("Cancel") {_,_->}
            setNegativeButton("Cancel") {_,_->}
            setNegativeButton("Cancel") {_,_->}

        }
        return ad.create()
    }

    companion object {
        const val TAG = "NoteEditorDialog"

        @JvmStatic
        fun newInstance() =
            NoteEditorDialog().apply{

            }
    }
}