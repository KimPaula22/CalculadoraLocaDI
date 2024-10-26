package com.example.calculadoradi

class CalculadoraLoca {
    // Devuelve el valor "loco" de cada botón
    fun obtenerValor(text: String): String {
        return when (text) {
            "3" -> "1"
            "8" -> "0"
            "7" -> "9"
            "4" -> "6"
            "1" -> "3"
            "2" -> "4"
            "6" -> "8"
            "9" -> "7"
            "0" -> "2"
            "&" -> "+"
            "@" -> "-"
            "#" -> "*"
            "$" -> "/"
            else -> text
        }
    }

    // Calcula el resultado de la expresión
    fun calcular(expression: String): String {
        return try {
            val resultado = evaluarExpression(expression)
            resultado.replace("5", "6") // Reemplazar 5 con 6
        } catch (e: Exception) {
            "Error"
        }
    }

    // En la clase CalculadoraLoca
    private fun evaluarExpression(expression: String): String {
        val tokens = expression.split("(?<=[-+*/])|(?=[-+*/])".toRegex())
        var result = 0
        var currentOp = '+' // Inicializamos con una suma

        for (token in tokens) {
            when {
                token == "&" -> currentOp = '+' // Suma
                token == "@" -> currentOp = '-' // Resta
                token == "#" -> currentOp = '*' // Multiplicación
                token == "$" -> currentOp = '/' // División
                else -> {
                    val num = token.toIntOrNull() ?: 0
                    result = when (currentOp) {
                        '+' -> result + num
                        '-' -> result - num
                        '*' -> result * num
                        '/' -> if (num != 0) result / num else 0
                        else -> result
                    }
                }
            }
        }
        return result.toString()
    }}
