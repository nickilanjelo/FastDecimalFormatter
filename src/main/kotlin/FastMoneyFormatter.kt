package com.nickilanjelo

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

object FastMoneyFormatter {

    private val builder = StringBuilder().apply { setLength(0) }

    fun newFormat(amount: BigDecimal): String {
        val amountStr = amount
            .abs()
            .setScale(2, RoundingMode.FLOOR)
            .toPlainString()

        val numberLength = amountStr.length
        val intPartLength = numberLength - 3
        val lastNumber = amountStr[numberLength - 1]
        val penultimateNumber = amountStr[numberLength - 2]
        val hasFractionalPart = lastNumber != '0' || penultimateNumber != '0'

        var startingSeparatorIndex = (intPartLength % 3).let { result ->
            if (result == 0) 3 else result
        }

        builder.apply { setLength(0) }
        for (i in 0..<intPartLength) {
            if (startingSeparatorIndex == i && i != intPartLength - 1) {
                builder.append(' ')
                startingSeparatorIndex += 3
            }
            builder.append(amountStr[i])
        }
        if (hasFractionalPart) {
            builder.append(',', penultimateNumber, lastNumber)
        }
        return builder.toString()
    }
}
