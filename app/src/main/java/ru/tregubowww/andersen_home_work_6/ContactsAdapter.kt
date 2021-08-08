package ru.tregubowww.andersen_home_work_6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactsAdapter(
    private val clickListener: OnRecyclerItemClicked,
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    var contactList = mutableListOf<Contact>()
        set(value) {
            val contactDiffUtils = ContactDiffUtils(field, value)
            val contactDiffResult = DiffUtil.calculateDiff(contactDiffUtils)
            field = value
            contactDiffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_contact, parent, false)
        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val itemContact = contactList[position]
        holder.bind(itemContact, clickListener, position)
    }

    class ContactsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.nameTextView)
        private val surName = itemView.findViewById<TextView>(R.id.surNameTextView)
        private val phoneNumber = itemView.findViewById<TextView>(R.id.phoneNumberTextView)
        private val avatar = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(contact: Contact, clickListener: OnRecyclerItemClicked, position: Int) {

            itemView.setOnClickListener { clickListener.onClick(contact) }
            itemView.setOnLongClickListener {
                clickListener.onLongClick(adapterPosition, contact)
                true
            }

            name.text = contact.name
            surName.text = contact.surname
            phoneNumber.text = contact.phoneNumber

            Glide
                .with(itemView)
                .load(contact.avatarPath)
                .into(avatar)

        }
    }

    interface OnRecyclerItemClicked {
        fun onClick(contact: Contact)
        fun onLongClick(position: Int, contact: Contact)
    }

    override fun getItemCount() = contactList.size
}