### Included:
* GSON
* Kotlin Serialization

### Elements per run:
* 1000 
* 100_000
* 1_000_000

Each strategy will executed 100 times

### Result:
* Minimum time 
* Maximum time
* Average (100 times)

### Report:
Excel table

### Execution branch mark on my machine

i7 8700, 32GB Ram

| Experiment name                                               | Serializer                                     | Total objects | Repeats | Generation min | Generation max | Generation avg | Serialization min | Serialization max | Serialization avg | Deserialization min | Deserialization max | Deserialization avg |
| ------------------------------------------------------------- | ---------------------------------------------- | ------------- | ------- | -------------- | -------------- | -------------- | ----------------- | ----------------- | ----------------- | ------------------- | ------------------- | ------------------- |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000          | 100     | 0              | 17             | 0              | 1                 | 86                | 3                 | 1                   | 31                  | 2                   |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 100000        | 100     | 5              | 17             | 6              | 113               | 185               | 121               | 89                  | 128                 | 94                  |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000000       | 100     | 60             | 193            | 108            | 1156              | 1587              | 1339              | 906                 | 1220                | 1045                |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000          | 100     | 0              | 0              | 0              | 0                 | 183               | 2                 | 0                   | 19                  | 1                   |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 100000        | 100     | 5              | 20             | 10             | 61                | 113               | 72                | 91                  | 141                 | 112                 |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000000       | 100     | 59             | 161            | 93             | 602               | 753               | 645               | 914                 | 1317                | 1069                |