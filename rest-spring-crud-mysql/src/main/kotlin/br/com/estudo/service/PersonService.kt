package br.com.estudo.service

import br.com.estudo.controller.PersonController
import br.com.estudo.data.vo.v1.PersonVO
import br.com.estudo.exception.ResourceNotFoundException
import br.com.estudo.mapper.Mapper
import br.com.estudo.model.Person
import br.com.estudo.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
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
        val personVO = Mapper.parseObject(person, PersonVO::class.java)
        val selfLink = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(selfLink)
        return personVO
    }

    fun findAll(): List<PersonVO> {
        logger.info("findAll")
        val people = repository.findAll()
        val peopleVo =  Mapper.parseListObjects(people, PersonVO::class.java)
        for(personVO in peopleVo) {
            val selfLink = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
            personVO.add(selfLink)
        }
        return peopleVo
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("create Person $person")
        val entity = Mapper.parseObject(person, Person::class.java)
        val personVO = Mapper.parseObject(person, PersonVO::class.java)
        val selfLink = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(selfLink)
        return personVO
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("updating person $person")

        val entity =repository.findById(person.key)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $person.id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        val personVO = Mapper.parseObject(repository.save(entity), PersonVO::class.java)
        val selfLink = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(selfLink)
        return personVO
    }

    fun delete(id: Long) {
        logger.info("deleting $id")
        val entity = repository.findById(id)
            .orElseThrow(){ResourceNotFoundException("Person not found for this id :: $id")}
        return repository.deleteById(entity.id)
    }
}