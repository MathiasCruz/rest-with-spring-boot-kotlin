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

    fun findAll(): List<Person> {
        logger.info("findAll")
        val listPersons:MutableList<Person> = ArrayList()

        for (i in 0..7) {
            val person = mockPerson(i)
            listPersons.add(person)
        }
        return listPersons
    }

    private fun mockPerson(i: Int): Person {
        return Person(
            id = counter.incrementAndGet(),
            "FistName $i",
            "LastName $i",
            "Some City $i",
            "Masculino"
        )
    }

    fun create(person: Person): Person {
        logger.info("create")
        return person
    }

    fun update(person: Person): Person {
        logger.info("update")
        return person
    }

    fun delete(id: Long) {
        logger.info("delete")
    }
}