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
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000          | 100     | 0              | 8              | 0              | 1                 | 69                | 3                 | 1                   | 27                  | 2                   |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 100000        | 100     | 6              | 17             | 7              | 115               | 195               | 130               | 88                  | 139                 | 100                 |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000000       | 100     | 62             | 169            | 105            | 1155              | 1464              | 1326              | 909                 | 1246                | 1019                |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000          | 100     | 0              | 0              | 0              | 0                 | 16                | 1                 | 1                   | 18                  | 1                   |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 100000        | 100     | 6              | 19             | 11             | 63                | 100               | 73                | 84                  | 128                 | 106                 |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000000       | 100     | 62             | 206            | 96             | 625               | 759               | 676               | 884                 | 1325                | 1025                |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 1000          | 100     | 0              | 1              | 0              | 0                 | 14                | 1                 | 1                   | 39                  | 2                   |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 100000        | 100     | 6              | 14             | 7              | 85                | 115               | 96                | 125                 | 209                 | 133                 |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 1000000       | 100     | 61             | 174            | 100            | 859               | 1113              | 981               | 1304                | 1680                | 1450                |rialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000000       | 100     | 59             | 161            | 93             | 602               | 753               | 645               | 914                 | 1317                | 1069                |