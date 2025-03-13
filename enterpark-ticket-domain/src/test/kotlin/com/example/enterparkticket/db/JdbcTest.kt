package com.example.enterparkticket.db

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.style.FunSpec
import java.sql.*

private val logger = KotlinLogging.logger {}

class JdbcTest : FunSpec({

    context("jdbc") {
        val sql = "SELECT * FROM user WHERE user_id = ?"

        val connection = getConnection()
        val preparedStatement = connection.prepareStatement(sql)
        preparedStatement.setLong(1, 1L)
        val resultSet = preparedStatement.executeQuery()

        try {
            if (resultSet.next()) {
                logger.info { resultSet.getLong("user_id") }
            } else {
                throw NoSuchElementException("user not found")
            }
        } catch (e: SQLException) {
            logger.error { e }
            throw e
        } finally {
            close(connection, preparedStatement, resultSet)
        }
    }
})

fun getConnection(): Connection {
    val url = "jdbc:mysql://localhost:3307/enterpark_ticket_db"
    val username = "root"
    val password = "1234"

    try {
        return DriverManager.getConnection(url, username, password)
    } catch (e: SQLException) {
        logger.error { e }
        throw e
    }
}

fun close(connection: Connection, preparedStatement: PreparedStatement, resultSet: ResultSet?) {
    if (resultSet != null) {
        try {
            resultSet.close()
        } catch (e: SQLException) {
            logger.error { e }
        }
    }

    try {
        preparedStatement.close()
    } catch (e: SQLException) {
        logger.error { e }
    }

    try {
        connection.close()
    } catch (e: SQLException) {
        logger.error { e }
    }
}
