package testprject.objects

import kotlinx.serialization.Serializable
import testprject.objectsFactory.PrimitiveDataClass

@Serializable
data class TopLevelObject(val list: List<PrimitiveDataClass>)