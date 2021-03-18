package testprject

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals
import kotlinx.serialization.SerializationException

class KotlinSerializationTest {

    @Serializable
    data class Data1(val field: String? = "field")

    @Test
    fun testDefaultOnNull() {
        val expected = Data1(null)
        val result: Data1 = Json { isLenient = true }.decodeFromString("{field: null}")

        assertEquals(result, expected)
    }

    @Test
    fun testDefaultOnEmpty() {
        val expected = Data1("field")
        val result: Data1 = Json.decodeFromString("{}")

        assertEquals(result, expected)
    }


    @Serializable
    data class Data2(val field: String = "field")

    @Test(expected = SerializationException::class)
    fun testDefaultOnNullNonNullable() {
        val expected = Data2("field")
        val result: Data2 = Json { isLenient = true }.decodeFromString("{field: null}")

        assertEquals(result, expected)
    }

    @Test
    fun testDefaultOnEmptyNonNullable() {
        val expected = Data2("field")
        val result: Data2 = Json.decodeFromString("{}")

        assertEquals(result, expected)
    }
}