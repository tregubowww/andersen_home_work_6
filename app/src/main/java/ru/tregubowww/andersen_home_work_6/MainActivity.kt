package ru.tregubowww.andersen_home_work_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), TransactionsContactsFragmentClicks {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            onSaveClick()
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

    override fun onSaveClick() {
        if (resources.getBoolean(R.bool.isTab)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.tabletContainerContactsFragment, ContactsFragment.newInstance())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, ContactsFragment.newInstance())
                .commit()
        }
    }
}