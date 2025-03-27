package br.com.estudo.mapper

import org.modelmapper.ModelMapper

object Mapper {

    private val mapper = ModelMapper()

    fun <O, D> parseObject(origin: O, destination: Class<D>?): D = mapper.map(origin, destination)

    fun <O, D> parseListObjects(origin: List<O>, destination: Class<D>):ArrayList<D> {
        val destinationList = ArrayList<D>()
        for(item in origin){
            destinationList.add(mapper.map(item, destination))
        }
        return destinationList
    }
}