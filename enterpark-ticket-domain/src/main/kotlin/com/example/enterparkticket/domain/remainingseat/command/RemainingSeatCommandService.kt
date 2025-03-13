package com.example.enterparkticket.domain.remainingseat.command

import com.example.enterparkticket.domain.remainingseat.model.RemainingSeat
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemainingSeatCommandService {

    fun decreaseRemainingSeatCount(remainingSeat: RemainingSeat) {
        remainingSeat.decreaseCount()
    }
}
