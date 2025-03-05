package com.jsm.cashierone.repository.user

import com.jsm.cashierone.db.common.suspendTransaction
import com.jsm.cashierone.db.user.UserDao
import com.jsm.cashierone.db.user.UserTable
import com.jsm.cashierone.db.user.toModel
import com.jsm.cashierone.model.User
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and

interface UserRepository {
    suspend fun login(email: String, password: String): User
    suspend fun register(user: User): User
    suspend fun getUsersByRol(rolId: Int): List<User>
    suspend fun getAll(): List<User>
    suspend fun getByEmail(email: String): User?
}

class UserRepositoryImpl : UserRepository {
    override suspend fun login(email: String, password: String) = suspendTransaction {
        UserDao.find { UserTable.id eq email and(UserTable.password eq password) }
            .firstOrNull()?.toModel() ?:
            throw EntityNotFoundException(EntityID(email, UserTable), UserDao)
    }

    override suspend fun register(user: User): User {
        return UserDao.new {
            email = EntityID(user.email, UserTable)
            password = user.password
            name = user.name
            phoneNumber = user.phoneNumber
            rolId = user.rolId
        }.toModel()
    }

    override suspend fun getUsersByRol(rolId: Int) = suspendTransaction {
        UserDao.find { UserTable.rolId eq rolId }.map { it.toModel() }
    }

    override suspend fun getAll() = suspendTransaction {
        UserDao.all().map { it.toModel() }
    }

    override suspend fun getByEmail(email: String) = suspendTransaction {
        UserDao.findById(email)?.toModel()
    }
}