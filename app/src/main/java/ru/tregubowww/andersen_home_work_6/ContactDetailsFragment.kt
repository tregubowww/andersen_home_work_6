package ru.tregubowww.andersen_home_work_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class ContactDetailsFragment : Fragment() {

    private var contact: Contact? = null

    private lateinit var phoneNumberEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var cityNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            contact = it.getParcelable(CONTACT_KEY_EXTRA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        fillFields()
        setButtonSaveClickListener()

    }

    private fun setButtonSaveClickListener() {
        buttonSave.setOnClickListener {
            contact?.phoneNumber = phoneNumberEditText.text.toString()
            contact?.name = nameEditText.text.toString()
            contact?.surname = surnameEditText.text.toString()
            contact?.city = cityNumberEditText.text.toString()
            contact?.email = emailEditText.text.toString()
        }
    }

    private fun fillFields() {
        phoneNumberEditText.setText(contact?.phoneNumber ?: "")
        nameEditText.setText(contact?.name ?: "")
        surnameEditText.setText(contact?.surname ?: "")
        cityNumberEditText.setText(contact?.city ?: "")
        emailEditText.setText(contact?.email ?: "")
    }

    private fun initView(view: View) {
        phoneNumberEditText = view.findViewById(R.id.numberPhoneEditText)
        nameEditText = view.findViewById(R.id.nameEditText)
        surnameEditText = view.findViewById(R.id.surnameEditText)
        cityNumberEditText = view.findViewById(R.id.cityEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        buttonSave = view.findViewById(R.id.buttonSave)
    }

    companion object {
        private const val CONTACT_KEY_EXTRA = "CONTACT_KEY_EXTRA"

        fun newInstance(contact: Contact) =
            ContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CONTACT_KEY_EXTRA, contact)
                }
            }
    }
}