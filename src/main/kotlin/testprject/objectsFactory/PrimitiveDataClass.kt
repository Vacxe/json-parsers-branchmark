package testprject.objectsFactory

import kotlinx.serialization.Serializable

@Serializable
data class PrimitiveDataClass(
        val name: String,
        val int: Int,
        val double: Double,
        val float: Float,
        val boolean: Boolean,
        val optional: String? = "Optional String"
)