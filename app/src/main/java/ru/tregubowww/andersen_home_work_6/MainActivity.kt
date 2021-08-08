package ru.tregubowww.andersen_home_work_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ContactsFragment.TransactionsContactsFragmentClicks {

    private val fragmentContacts = ContactsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (resources.getBoolean(R.bool.isTab)) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.tabletContainerContactsFragment, fragmentContacts)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.containerFragment, fragmentContacts)
                    .commit()
            }

        }
    }

    override fun onContactClick(contact: Contact) {
        if (resources.getBoolean(R.bool.isTab)) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.tabletContainerContactDetailsFragment, ContactDetailsFragment.newInstance(contact))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.containerFragment, ContactDetailsFragment.newInstance(contact))
                .commit()
        }

    }
}