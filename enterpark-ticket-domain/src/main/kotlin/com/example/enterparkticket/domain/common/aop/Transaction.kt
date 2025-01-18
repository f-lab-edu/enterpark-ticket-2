package com.example.enterparkticket.domain.common.aop

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
class Transaction(advice: TransactionAdvice) {

    init {
        transactionAdvice = advice
    }

    companion object {
        private lateinit var transactionAdvice: TransactionAdvice

        fun <T> propagationRequiresNew(function: () -> T): T {
            return transactionAdvice.propagationRequiresNew(function)
        }
    }

    @Component
    class TransactionAdvice {

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        fun <T> propagationRequiresNew(function: () -> T): T {
            return function.invoke()
        }
    }
}
