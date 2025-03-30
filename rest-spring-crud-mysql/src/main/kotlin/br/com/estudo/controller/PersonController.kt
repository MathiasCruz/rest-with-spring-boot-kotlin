package br.com.estudo.controller

import br.com.estudo.data.vo.v1.PersonVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import br.com.estudo.service.PersonService
import br.com.estudo.util.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private  lateinit var service: PersonService

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.MEDIA_APPLICATION_JSON,
            MediaType.MEDIA_APPLICATION_XML,
            MediaType.MEDIA_APPLICATION_YML])
    fun findById(@PathVariable(value="id") id : Long): PersonVO {
        return service.findById(id)
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.MEDIA_APPLICATION_JSON,
            MediaType.MEDIA_APPLICATION_XML,
            MediaType.MEDIA_APPLICATION_YML])
    fun deleteById(@PathVariable(value="id") id : Long):ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping(produces = [MediaType.MEDIA_APPLICATION_JSON,
        MediaType.MEDIA_APPLICATION_XML,
        MediaType.MEDIA_APPLICATION_YML])
    fun findAll(): List<PersonVO> {
        return service.findAll()
    }

    @PostMapping(consumes = [MediaType.MEDIA_APPLICATION_JSON,
        MediaType.MEDIA_APPLICATION_XML,
        MediaType.MEDIA_APPLICATION_YML],
        produces = [MediaType.MEDIA_APPLICATION_JSON,
            MediaType.MEDIA_APPLICATION_XML,
            MediaType.MEDIA_APPLICATION_YML])
    fun createPerson(@RequestBody person: PersonVO): PersonVO{
        return service.create(person)
    }

    @PutMapping(consumes = [MediaType.MEDIA_APPLICATION_JSON,
        MediaType.MEDIA_APPLICATION_XML,
        MediaType.MEDIA_APPLICATION_YML],
        produces = [MediaType.MEDIA_APPLICATION_JSON,
            MediaType.MEDIA_APPLICATION_XML,
            MediaType.MEDIA_APPLICATION_YML])
    fun updatePerson(@RequestBody person: PersonVO): PersonVO{
        return service.update(person)
    }
}