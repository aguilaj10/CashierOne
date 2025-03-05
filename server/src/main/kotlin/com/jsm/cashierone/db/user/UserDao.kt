package com.jsm.cashierone.db.user

import com.jsm.cashierone.model.User
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

class UserDao (email: EntityID<String>) : Entity<String>(email) {
    companion object : EntityClass<String, UserDao>(UserTable)

    var email by UserTable.id
    var password by UserTable.password
    var name by UserTable.name
    var phoneNumber by UserTable.phoneNumber
    var rolId by UserTable.rolId
}

object UserTable : IdTable<String>("usuarios") {
    override val id: Column<EntityID<String>> = text("email").uniqueIndex().entityId()
    val password = text("password")
    val name = text("nombre")
    val phoneNumber = text("telefono").nullable()
    val rolId = integer("rol_id").nullable()
}

fun UserDao.toModel() = User(
    email.value,
    password,
    phoneNumber,
    name,
    rolId
)