package com.example.enterparkticket.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.assertions.shouldFail
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import java.sql.SQLException
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

class DataSourceTimeoutTest : StringSpec({

    // DB wait_timeout = 40(s)
    val dbWaitTimeout = 40_000L

    "Hikari CP의 max-lifetime이 DB(MySQL)의 wait_timeout보다 길면 warn 로그가 발생한다." {
        val dataSource = createDataSource(newMaxLifeTime = dbWaitTimeout + 3000)

        // 첫 번째 커넥션 정상 확인
        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        // DB wait_timeout 초과 대기
        delay(43.seconds)

        // 만료된 커넥션으로 새로운 쿼리 시도
        shouldFail {
            dataSource.connection.use {
                it.createStatement().executeQuery("SELECT 1;")
            }
        }

        dataSource.close()
    }

    "Hikari CP의 max-lifetime이 DB의 wait_timeout보다 짧으면 정상적으로 커넥션이 정리된다." {
        val dataSource = createDataSource()

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        // max-lifetime 초과 대기
        delay(40.seconds)

        dataSource.hikariPoolMXBean.activeConnections shouldBe 0 // 현재 사용 중인 활성 커넥션 수
        // 만료된 커넥션을 제거하고 새 커넥션을 생성하여 유휴 상태로 준비
        dataSource.hikariPoolMXBean.idleConnections shouldBe 1 // 유휴 상태로 유지되고 있는 커넥션 수

        dataSource.close()
    }

    "Hikari CP의 idle-timeout이 max-lifetime보다 길면 idle-timeout이 비활성화된다." {
        val dataSource = createDataSource(newIdleTimeout = 33_000)

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        delay(33.seconds)

        dataSource.hikariPoolMXBean.idleConnections shouldBe 1

        dataSource.close()
    }

    "Hikari CP의 idle-timeout이 max-lifetime보다 짧으면 정상적으로 유휴 커넥션이 정리된다." {
        val dataSource = createDataSource()

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        delay(30.seconds)

        dataSource.hikariPoolMXBean.idleConnections shouldBe 1

        dataSource.close()
    }

    "Hikari CP의 keepalive-time이 max-lifetime보다 길면 keepalive-time이 비활성화된다." {
        val dataSource = createDataSource(newKeepaliveTime = 33_000)

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        delay(33.seconds)

        // 만료된 커넥션으로 새로운 쿼리 시도
        shouldFail {
            dataSource.connection.use {
                it.createStatement().executeQuery("SELECT 1;")
            }
        }

        dataSource.close()
    }

    "Hikari CP의 keepalive-time이 max-lifetime보다 짧으면 정상적으로 ping을 보낸다." {
        val dataSource = createDataSource(newMaxLifeTime = 33000)

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;")
        }

        delay(33.seconds)

        dataSource.connection.use {
            it.createStatement().executeQuery("SELECT 1;").next() shouldBe true
        }

        dataSource.close()
    }

    "JDBC socketTimeout 초과 시 쿼리 실행 중 커넥션이 종료된다." {
        val dataSource = createDataSource(socketTimeout = 3000)

        shouldThrow<SQLException> {
            dataSource.connection.use {
                logger.error { it }
                it.createStatement().executeQuery("SELECT SLEEP(5);")
            }
        }

        dataSource.close()
    }

    "HikariCP의 max-lifetime 초과 시 실행 중인 쿼리는 완료 후 커넥션이 종료된다." {
        // max-lifetime - 30초, 쿼리 실행 시간 - 35초
        val dataSource = createDataSource(socketTimeout = 40000)

        dataSource.connection.use {
            logger.info { "쿼리 시작" }
            it.createStatement().executeQuery("SELECT SLEEP(35);")
            logger.info { "쿼리 완료" }
        }

        dataSource.close()
    }

    "Statement.queryTimeout 초과 시 실행 중인 쿼리는 강제 종료된다." {
        // max-lifetime - 30초, 쿼리 실행 시간 - 35초
        val dataSource = createDataSource(socketTimeout = 40000)

        dataSource.connection.use {
            val statement = it.createStatement()

            logger.info { "쿼리 시작" }
            statement.queryTimeout = 10
            statement.executeQuery("SELECT SLEEP(35);")
            logger.info { "쿼리 완료" }
        }

        dataSource.close()
    }
})

fun createDataSource(
    newMaxLifeTime: Long = 30000,
    newIdleTimeout: Long = 10000,
    newKeepaliveTime: Long = 30000,
    socketTimeout: Long = 5000,
    connectTimeout: Long = 5000,
): HikariDataSource {
    val config = HikariConfig().apply {
        jdbcUrl =
            "jdbc:mysql://localhost:3307/enterpark_ticket_db?socketTimeout=$socketTimeout&connectTimeout=$connectTimeout"
        username = "root"
        password = "1234"
        driverClassName = "com.mysql.cj.jdbc.Driver"
        maximumPoolSize = 5
        minimumIdle = 1
        connectionTimeout = 30000
        maxLifetime = newMaxLifeTime
        idleTimeout = newIdleTimeout
        keepaliveTime = newKeepaliveTime
    }
    return HikariDataSource(config)
}
