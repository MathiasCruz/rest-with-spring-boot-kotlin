package br.com.estudo.service

import br.com.estudo.exception.ResourceNotFoundException
import br.com.estudo.model.Person
import br.com.estudo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {
    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("findById: $id")
        return repository.findById(id).orElseThrow(){ResourceNotFoundException("Person not found for this id :: $id")}
    }

    fun findAll(): List<Person> {
        logger.info("findAll")
        return repository.findAll()
    }
    fun create(person: Person): Person {
        logger.info("create Person $person")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        logger.info("updating person $person")

        val entity =repository.findById(person.id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $person.id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return repository.save(entity)
    }

    fun delete(id: Long) {
        logger.info("deleting $id")
        val entity = repository.findById(id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $id")}
        return repository.deleteById(entity.id)
    }
}