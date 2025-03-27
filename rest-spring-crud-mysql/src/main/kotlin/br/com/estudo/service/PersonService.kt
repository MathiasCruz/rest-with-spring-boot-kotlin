package br.com.estudo.service

import br.com.estudo.data.vo.v1.PersonVO
import br.com.estudo.exception.ResourceNotFoundException
import br.com.estudo.mapper.Mapper
import br.com.estudo.model.Person
import br.com.estudo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): PersonVO {
        logger.info("findById: $id")
        val person = repository.findById(id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $id")}
        return Mapper.parseObject(person, PersonVO::class.java)}

    fun findAll(): List<PersonVO> {
        logger.info("findAll")
        val people = repository.findAll()
        return Mapper.parseListObjects(people, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("create Person $person")
        val entity = Mapper.parseObject(person, Person::class.java)
        return Mapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("updating person $person")

        val entity =repository.findById(person.id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $person.id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return Mapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("deleting $id")
        val entity = repository.findById(id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $id")}
        return repository.deleteById(entity.id)
    }
}