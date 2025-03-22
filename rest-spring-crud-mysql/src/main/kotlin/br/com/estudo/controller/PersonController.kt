package br.com.estudo.controller

import br.com.estudo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import br.com.estudo.service.PersonService
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private  lateinit var service: PersonService

    @RequestMapping(value = ["/{id}"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
        )
    fun findById(@PathVariable(value="id") id : Long): Person {
        return service.findById(id)
    }

    @RequestMapping(value = ["/{id}"],
        method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
        )
    fun deleteById(@PathVariable(value="id") id : Long) {
        return service.delete(id)
    }

    @RequestMapping(method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
        )
    fun findAll(): List<Person> {
        return service.findAll()
    }

    @RequestMapping(method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
        )
    fun createPerson(@RequestBody person: Person): Person{
        return service.create(person)
    }

    @RequestMapping(method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
        )
    fun updatePerson(@RequestBody person: Person): Person{
        return service.update(person)
    }
}