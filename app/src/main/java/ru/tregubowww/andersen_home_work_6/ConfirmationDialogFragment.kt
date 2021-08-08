package ru.tregubowww.andersen_home_work_6

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmationDialogFragment : DialogFragment() {

    private var position: Int? = null
    private var contact: Contact? = null

    private var dialogEventListener: ConfirmationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dialogEventListener = parentFragment as ConfirmationListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(CONTACT_POSITION_KEY_EXTRA)
            contact = it.getParcelable(CONTACT_KEY_EXTRA)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                dialogEventListener?.onConfirmDialogButtonClicked(position, contact)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    override fun onDetach() {
        super.onDetach()
        dialogEventListener = null
    }

    interface ConfirmationListener {
        fun onConfirmDialogButtonClicked(position: Int?, contact: Contact?)
    }

    companion object {
        private const val CONTACT_POSITION_KEY_EXTRA = "CONTACT_POSITION_KEY_EXTRA"
        private const val CONTACT_KEY_EXTRA = "CONTACT_KEY_EXTRA"

        fun newInstance(position: Int, contact: Contact) =
            ConfirmationDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(CONTACT_POSITION_KEY_EXTRA, position)
                    putParcelable(CONTACT_KEY_EXTRA, contact)
                }
            }
    }
}