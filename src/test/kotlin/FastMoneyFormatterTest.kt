import com.google.common.truth.Truth
import com.nickilanjelo.FastMoneyFormatter
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class FastMoneyFormatterTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "1234.56; 1 234,56",
            "5678; 5 678",
            "1000000.111; 1 000 000,11",
            "1234567890123456789.9; 1 234 567 890 123 456 789,90",
            "0.00; 0"
        ],
        delimiter = ';'
    )
    fun `returns correctly formatted string`(
        inputString: String,
        expected: String
    ) {
        val bigDecimal = BigDecimal(inputString)
        val actual = FastMoneyFormatter.newFormat(bigDecimal)
        Truth.assertThat(actual).isEqualTo(expected)
    }
}