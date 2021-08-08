package ru.tregubowww.andersen_home_work_6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ContactsFragment : Fragment(), ConfirmationDialogFragment.ConfirmationListener {

    private var listener: TransactionsContactsFragmentClicks? = null

    private lateinit var adapterContacts: ContactsAdapter
    private lateinit var searchView: SearchView

    private val itemRecyclerClickListener = object : ContactsAdapter.OnRecyclerItemClicked {
        override fun onClick(contact: Contact) {
            listener?.onContactClick(contact)
        }

        override fun onLongClick(position: Int, contact: Contact) {
            val a = ConfirmationDialogFragment.newInstance(position, contact)
            a.show(childFragmentManager, null)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransactionsContactsFragmentClicks) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        adapterContacts = ContactsAdapter(itemRecyclerClickListener)
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        initSearchView(view)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.contactList)

        recyclerView.adapter = adapterContacts
        recyclerView.layoutManager =
            GridLayoutManager(context, 1)

        adapterContacts.contactList = ContactsDB.contactList

        val contactItemDecoration =
            ContactItemDecoration(
                resources.getDimensionPixelSize(R.dimen.sidePadding),
                resources.getDimensionPixelSize(R.dimen.topAndBottomPadding),
            )

        recyclerView.addItemDecoration(contactItemDecoration)
    }

    private fun initSearchView(view: View) {

        searchView = view.findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    val resultList = mutableListOf<Contact>()
                    for (it in ContactsDB.contactList) {
                        if (
                            it.name.lowercase(Locale.ROOT).contains(newText.lowercase(Locale.ROOT))
                            || it.surname.lowercase(Locale.ROOT).contains(newText.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(it)
                        }
                    }
                    adapterContacts.contactList = resultList
                    return false
                }
            }
        )
        searchView.setOnCloseListener {
            adapterContacts.contactList = ContactsDB.contactList
            false
        }
    }

    interface TransactionsContactsFragmentClicks {
        fun onContactClick(contact: Contact)
    }

    override fun onConfirmDialogButtonClicked(position: Int?, contact: Contact?) {
        ContactsDB.contactList.remove(contact)
        position?.let { adapterContacts.notifyItemRemoved(it) }

    }

    companion object {
        fun newInstance() = ContactsFragment()
    }
}