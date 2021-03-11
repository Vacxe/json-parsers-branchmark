package testprject.objectsFactory

import testprject.objectsFactory.ObjectFactory
import testprject.objectsFactory.PrimitiveDataClass

import kotlin.random.Random

class SingleLevelModelFactory : ObjectFactory {
    override fun generate(): PrimitiveDataClass = PrimitiveDataClass(
            name = "Object ${Random.nextInt()}",
            int = Random.nextInt(),
            double = Random.nextDouble(),
            float = Random.nextFloat(),
            boolean = Random.nextBoolean()
    )

    override val info = "flat structure"
}