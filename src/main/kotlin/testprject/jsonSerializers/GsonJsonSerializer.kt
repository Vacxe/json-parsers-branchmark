package testprject.jsonSerializers

import com.google.gson.Gson
import testprject.objects.TopLevelObject

class GsonJsonSerializer : JsonSerializer {
    override fun name(): String = Gson::class.java.canonicalName

    override fun toJson(any: TopLevelObject): String = Gson().toJson(any)

    override fun fromString(string: String): TopLevelObject = Gson().fromJson(string, TopLevelObject::class.java)
}