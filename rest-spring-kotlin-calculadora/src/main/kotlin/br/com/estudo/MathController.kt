package br.com.estudo

import br.com.estudo.exception.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController {
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    fun sum(@PathVariable("numberOne") numberOne: String?,
            @PathVariable("numberTwo") numberTwo: String?): Double {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
           throw UnsupportedMathOperationException("$numberOne and $numberTwo must be numbers")
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank())
                   return false
        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank())return 0.0
        val number = strNumber.replace(",".toRegex(), ".")
        return number.toDouble()
    }
}
