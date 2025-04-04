package br.com.estudo.mockito.service

import br.com.estudo.repository.PersonRepository
import br.com.estudo.service.PersonService
import br.com.estudo.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

class PersonServiceTest {

 private lateinit var inputObject: MockPerson
 @InjectMocks
 private lateinit var service: PersonService
 @Mock
 private lateinit var repository: PersonRepository

@BeforeEach
 fun setUp() {
  MockitoAnnotations.openMocks(this)
  inputObject = MockPerson()
 }

@Test
 fun findById() {
  val person = inputObject.mockEntity(1)
  person.id = 1
 `when` (repository.findById(1)).thenReturn(Optional.of(person))
  val result = service.findById(1)
  assertNotNull(result)
  assertNotNull(result.key)
  assertNotNull(result.links)
  assertTrue(result.links.toString().contains("/person/1"))
 }

@Test
 fun findAll() {}

@Test
 fun create() {}

@Test
 fun update() {}

@Test
 fun delete() {}
}