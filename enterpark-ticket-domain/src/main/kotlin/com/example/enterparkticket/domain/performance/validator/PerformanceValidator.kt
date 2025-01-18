package com.example.enterparkticket.domain.performance.validator

import com.example.enterparkticket.domain.performance.exception.AgeLimitException
import com.example.enterparkticket.domain.performance.model.Performance
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PerformanceValidator {

    fun validateUserAge(performance: Performance, userAge: LocalDate) {
        val currentDate = LocalDate.now()
        val age = currentDate.year - userAge.year + AGE_OFFSET
        if (age < performance.ageLimit.age) {
            throw AgeLimitException()
        }
    }

    companion object {
        const val AGE_OFFSET = 1
    }
}
