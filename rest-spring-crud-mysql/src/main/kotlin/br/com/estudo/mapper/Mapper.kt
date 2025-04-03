package br.com.estudo.mapper

import br.com.estudo.data.vo.v1.PersonVO
import br.com.estudo.model.Person

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object Mapper {

    private val mapper : Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O, D> parseObject(origin: O, destination: Class<D>?): D = mapper.map(origin, destination)

    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>):ArrayList<D> {
        val destinationList = ArrayList<D>()
        for(item in origin){
            destinationList.add(mapper.map(item, destination))
        }
        return destinationList
    }
}