package testprject.jsonSerializers

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import testprject.objects.TopLevelObject

class MoshiSerialization : JsonSerializer {
    private val moshi = Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    var jsonAdapter: JsonAdapter<TopLevelObject> = moshi.adapter(TopLevelObject::class.java)

    override fun name(): String = MoshiSerialization::class.java.canonicalName

    override fun toJson(any: TopLevelObject): String = jsonAdapter.toJson(any)

    override fun fromString(string: String): TopLevelObject = jsonAdapter.fromJson(string)!!
}

