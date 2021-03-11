package testprject.objectsFactory

interface ObjectFactory {
    fun generate(): PrimitiveDataClass

    val info: String
}