package com.nickilanjelo

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

import kotlin.system.measureNanoTime

/**
 * Benchmarks: I compare my version with DecimalFormatter
 *
 * In most cases it is faster, and more appropriate for my goals
 */
fun main() {
    // Test data: BigDecimal values to format
    val testCases = listOf(
        BigDecimal("1234.56"), // warm-up test case
        BigDecimal("1234.56"),
        BigDecimal("5678"),
        BigDecimal("1000000.99"),
        BigDecimal("1000000.01"),
        BigDecimal("123456.00"),
        BigDecimal("0.00"),
        BigDecimal("-987654321.12"),
        BigDecimal("1234567890123456789.99"),
        BigDecimal("0.00012345")
    )

    val customFormatSymbols = DecimalFormatSymbols().apply {
        groupingSeparator = ' '  // Space for thousands separator
        decimalSeparator = ','  // Dot for decimal separator
    }
    val decimalFormat = DecimalFormat("#,###.##", customFormatSymbols)

    // Benchmark both methods
    testCases.onEachIndexed { index, testCase ->

        // DecimalFormat
        val decimalFormatTime = measureNanoTime {
            repeat(10_000) {
                decimalFormat.format(testCase)
            }
        }

        // New formatter
        val time = measureNanoTime {
            repeat(10_000) {
                FastMoneyFormatter.newFormat(testCase)
            }
        }

        if (index != 0) {
            println("FastMoneyFormatter: ${time / 1_000_000.0} ms")
            println("DecimalFormat: ${decimalFormatTime / 1_000_000.0} ms")

            println("---")
        }
    }
}
