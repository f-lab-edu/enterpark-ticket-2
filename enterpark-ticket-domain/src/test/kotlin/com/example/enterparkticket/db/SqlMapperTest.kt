package com.example.enterparkticket.db

import io.kotest.core.spec.style.FunSpec
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource

class SqlMapperTest : FunSpec({

    context("JdbcTemplate") {
        val userId = 1L
        val email = "123@naver.com"

        val sql = "UPDATE user SET email = ? WHERE user_id = ?"
        val jdbcTemplate = getJdbcTemplate()
        jdbcTemplate.update(sql, email, userId)
    }
})

fun getJdbcTemplate(): JdbcTemplate {
    val url = "jdbc:mysql://localhost:3307/enterpark_ticket_db"
    val username = "root"
    val password = "1234"

    return JdbcTemplate(DriverManagerDataSource(url, username, password))
}
