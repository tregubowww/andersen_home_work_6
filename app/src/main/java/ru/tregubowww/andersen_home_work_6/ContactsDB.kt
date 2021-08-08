package ru.tregubowww.andersen_home_work_6

import kotlin.random.Random

object ContactsDB {

    private val surnameList = mutableListOf(
        "Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов", "Михайлов", "Новиков", "Федоров"
    )
    private val nameList = mutableListOf(
        "Иван", "Анатолий", "Владимир", "Павел", "Борис", "Юрий", "Александр", "Михаил", "Игорь", "Федор"
    )

    val contactList = generateContacts()

    private fun generateContacts(): MutableList<Contact> {
        val random = Random
        val contactList = mutableListOf<Contact>()
        repeat(100) {
            contactList.add(
                Contact(
                    id = it,
                    phoneNumber = "+7 ${random.nextLong(90000000000, 99999999999)}",
                    name = nameList[random.nextInt(0, 9)],
                    surname = surnameList[random.nextInt(0, 9)],
                    city = "",
                    email = "",
                    avatarPath = "https://picsum.photos/id/$it/367/267"
                )
            )
        }
        return contactList
    }

}