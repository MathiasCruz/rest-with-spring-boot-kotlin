package br.com.estudo.service

import br.com.estudo.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {
    private val counter = AtomicLong()
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("findById: $id")
        return Person(
            id = counter.incrementAndGet(),
            "Mathias",
            "Silva",
            "Belo Horizonte",
            "Masculino"
        )
    }
}