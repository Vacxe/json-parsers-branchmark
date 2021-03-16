package testprject.jsonSerializers

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import testprject.objects.TopLevelObject

class KotlinSerialization(private val json: Json = Json.Default) : JsonSerializer {

    override fun name(): String = KotlinSerialization::class.java.canonicalName

    override fun toJson(any: TopLevelObject): String = json.encodeToString(any)

    override fun fromString(string: String): TopLevelObject = json.decodeFromString(string)
}

