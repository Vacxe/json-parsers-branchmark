package testprject.jsonSerializers

import testprject.objects.TopLevelObject

interface JsonSerializer {
    fun name(): String
    fun toJson(any: TopLevelObject): String
    fun fromString(string: String): TopLevelObject
}