package com.example.enterparkticket.domain.payment.event

import com.example.enterparkticket.domain.common.aop.Async
import com.example.enterparkticket.domain.common.aop.Transaction
import com.example.enterparkticket.domain.reservation.event.ReservationWaitingPaymentEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

private val logger = KotlinLogging.logger {}

@Component
class ReservationWaitingPaymentEventHandler {

    @TransactionalEventListener
    fun handle(event: ReservationWaitingPaymentEvent) = Async.eventTaskExecutor {
        Transaction.propagationRequiresNew {
            logger.info { "티켓 예매 결제 하기 ${event.reservations}" }
        }
    }
}
