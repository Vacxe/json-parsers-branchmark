package testprject

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import testprject.jsonSerializers.GsonJsonSerializer
import testprject.jsonSerializers.JsonSerializer
import testprject.jsonSerializers.KotlinSerialization
import testprject.jsonSerializers.MoshiSerialization
import testprject.objects.TopLevelObject
import testprject.objectsFactory.ObjectFactory
import testprject.objectsFactory.SingleLevelModelFactory
import java.io.FileOutputStream
import java.time.Duration
import java.time.Instant.now
import kotlin.math.roundToLong

private val experimentSet = setOf(
        Experiment(GsonJsonSerializer(),
                SingleLevelModelFactory(),
                1000,
                100
        ),
        Experiment(GsonJsonSerializer(),
                SingleLevelModelFactory(),
                100_000,
                100
        ),
        Experiment(GsonJsonSerializer(),
                SingleLevelModelFactory(),
                1_000_000,
                100
        ),
        Experiment(KotlinSerialization(),
                SingleLevelModelFactory(),
                1000,
                100
        ),
        Experiment(KotlinSerialization(),
                SingleLevelModelFactory(),
                100_000,
                100
        ),
        Experiment(KotlinSerialization(),
                SingleLevelModelFactory(),
                1_000_000,
                100
        ),
        Experiment(MoshiSerialization(),
                SingleLevelModelFactory(),
                1000,
                100
        ),
        Experiment(MoshiSerialization(),
                SingleLevelModelFactory(),
                100_000,
                100
        ),
        Experiment(MoshiSerialization(),
                SingleLevelModelFactory(),
                1_000_000,
                100
        )
)

fun main() {
    val results: ArrayList<Pair<Experiment, ExperimentSetResult>> = arrayListOf()

    experimentSet.forEach { experiment ->

        val experimentsResults = (0..experiment.repeatTimes).mapIndexed { _, i ->
            println("Experiment '${experiment.name}' iteration: $i of ${experiment.repeatTimes}")
            doExperiment(experiment)
        }
        val experimentSetResult = experimentsResults.aggregate()

        results.add(experiment to experimentSetResult)

        val result = StringBuilder()
                .appendLine("Serializer: ${experiment.name}")
                .appendLine("Total count of iterations: ${experiment.repeatTimes}, Objects per generation: ${experiment.objectCount}")
                .appendLine("Generation: Min: ${experimentSetResult.generationTimeMin}, Max: ${experimentSetResult.generationTimeMax}, Avg: ${experimentSetResult.generationTimeAvg}")
                .appendLine("Serialization: Min: ${experimentSetResult.serializationTimeMin}, Max: ${experimentSetResult.serializationTimeMax}, Avg: ${experimentSetResult.serializationTimeAvg}")
                .appendLine("Deserialization: Min: ${experimentSetResult.deserializationTimeMin}, Max: ${experimentSetResult.deserializationTimeMax}, Avg: ${experimentSetResult.deserializationTimeAvg}")
        println(result)
    }

    excelResultsReporter(results)
}

private fun doExperiment(experiment: Experiment): ExperimentRawResult {
    val (randomValueArray, generationTime) = generate(experiment, experiment.objectFactory)
    val (serializedString, serializationTime) = serialize(experiment.jsonSerializer, randomValueArray)
    val (_, deserializationTime) = deserialize(experiment.jsonSerializer, serializedString)
    return ExperimentRawResult(generationTime, serializationTime, deserializationTime)
}

private fun generate(experiment: Experiment, objectFactory: ObjectFactory): Pair<TopLevelObject, Long> {
    val start = now()
    val topLevelObject = TopLevelObject((0..experiment.objectCount).map { objectFactory.generate() })
    val end = now()
    val generationTime = Duration.between(start, end).toMillis()
    return topLevelObject to generationTime
}

private fun serialize(jsonSerializer: JsonSerializer, randomValueArray: TopLevelObject): Pair<String, Long> {
    val start = now()
    val serializedString = jsonSerializer.toJson(randomValueArray)
    val end = now()
    val generationTime = Duration.between(start, end).toMillis()
    return serializedString to generationTime
}

private fun deserialize(jsonSerializer: JsonSerializer, serializedString: String): Pair<TopLevelObject, Long> {
    val start = now()
    val topLevelObject = jsonSerializer.fromString(serializedString)
    val end = now()
    val generationTime = Duration.between(start, end).toMillis()
    return topLevelObject to generationTime
}

data class Experiment(
        val jsonSerializer: JsonSerializer,
        val objectFactory: ObjectFactory,
        val objectCount: Int,
        val repeatTimes: Int
) {
    val name: String = "${jsonSerializer.name()} ${objectFactory.info}"
}

data class ExperimentRawResult(val generationTime: Long,
                               val serializationTime: Long,
                               val deserializationTime: Long)

data class ExperimentSetResult(val generationTimeMin: Long,
                               val generationTimeMax: Long,
                               val generationTimeAvg: Long,
                               val serializationTimeMin: Long,
                               val serializationTimeMax: Long,
                               val serializationTimeAvg: Long,
                               val deserializationTimeMin: Long,
                               val deserializationTimeMax: Long,
                               val deserializationTimeAvg: Long)

private fun List<ExperimentRawResult>.aggregate(): ExperimentSetResult {
    val generationTimes = this.map { it.generationTime }
    val serializationTimes = this.map { it.serializationTime }
    val deserializationTimes = this.map { it.deserializationTime }
    return ExperimentSetResult(
            generationTimes.minOrNull() ?: 0,
            generationTimes.maxOrNull() ?: 0,
            generationTimes.average().roundToLong(),
            serializationTimes.minOrNull() ?: 0,
            serializationTimes.maxOrNull() ?: 0,
            serializationTimes.average().roundToLong(),
            deserializationTimes.minOrNull() ?: 0,
            deserializationTimes.maxOrNull() ?: 0,
            deserializationTimes.average().roundToLong()
    )
}

private fun excelResultsReporter(results: List<Pair<Experiment, ExperimentSetResult>>) {
    val workbook = XSSFWorkbook()
    val sheet: XSSFSheet = workbook.createSheet("Serialization comparison")
    sheet.createRow(0).apply {
        var columnNumber = 0
        createCell(++columnNumber).also { it.setCellValue("Experiment name") }
        createCell(++columnNumber).also { it.setCellValue("Serializer") }
        createCell(++columnNumber).also { it.setCellValue("Total objects") }
        createCell(++columnNumber).also { it.setCellValue("Repeats") }
        createCell(++columnNumber).also { it.setCellValue("Generation min") }
        createCell(++columnNumber).also { it.setCellValue("Generation max") }
        createCell(++columnNumber).also { it.setCellValue("Generation avg") }
        createCell(++columnNumber).also { it.setCellValue("Serialization min") }
        createCell(++columnNumber).also { it.setCellValue("Serialization max") }
        createCell(++columnNumber).also { it.setCellValue("Serialization avg") }
        createCell(++columnNumber).also { it.setCellValue("Deserialization min") }
        createCell(++columnNumber).also { it.setCellValue("Deserialization max") }
        createCell(++columnNumber).also { it.setCellValue("Deserialization avg") }
    }

    results.forEachIndexed { index, pair ->
        sheet.createRow(index + 1).apply {
            var columnNumber = 0
            createCell(++columnNumber).also { it.setCellValue(pair.first.name) }
            createCell(++columnNumber).also { it.setCellValue(pair.first.jsonSerializer.name()) }
            createCell(++columnNumber).also { it.setCellValue(pair.first.objectCount.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.first.repeatTimes.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.generationTimeMin.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.generationTimeMax.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.generationTimeAvg.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.serializationTimeMin.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.serializationTimeMax.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.serializationTimeAvg.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.deserializationTimeMin.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.deserializationTimeMax.toString()) }
            createCell(++columnNumber).also { it.setCellValue(pair.second.deserializationTimeAvg.toString()) }
        }
    }


    FileOutputStream("ComparationSerialization.xlsx").use { outputStream -> workbook.write(outputStream) }
}
