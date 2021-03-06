### Included:
* GSON
* Kotlin Serialization
* Moshi

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
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000          | 1000    | 0              | 4              | 0              | 1                 | 43                | 1                 | 0                   | 34                  | 1                   |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 100000        | 1000    | 5              | 10             | 5              | 112               | 180               | 115               | 86                  | 136                 | 88                  |
| com.google.gson.Gson flat structure                           | com.google.gson.Gson                           | 1000000       | 1000    | 59             | 207            | 107            | 1125              | 1457              | 1312              | 885                 | 1221                | 996                 |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000          | 1000    | 0              | 0              | 0              | 0                 | 36                | 0                 | 0                   | 18                  | 1                   |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 100000        | 1000    | 5              | 17             | 9              | 59                | 82                | 66                | 83                  | 132                 | 102                 |
| testprject.jsonSerializers.KotlinSerialization flat structure | testprject.jsonSerializers.KotlinSerialization | 1000000       | 1000    | 59             | 249            | 111            | 598               | 784               | 690               | 871                 | 1375                | 1063                |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 1000          | 1000    | 0              | 1              | 0              | 0                 | 13                | 0                 | 1                   | 43                  | 1                   |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 100000        | 1000    | 5              | 17             | 5              | 84                | 114               | 86                | 123                 | 154                 | 124                 |
| testprject.jsonSerializers.MoshiSerialization flat structure  | testprject.jsonSerializers.MoshiSerialization  | 1000000       | 1000    | 59             | 213            | 88             | 855               | 1152              | 963               | 1276                | 1699                | 1404                |

### Nullability and default values:
#### Serialization 

Object structure

```
@Serializable
data class PrimitiveDataClass(
        val name: String,
        val int: Int,
        val double: Double,
        val float: Float,
        val boolean: Boolean,
        val optional: String? = "Optional String"
)
```

Generation

```
PrimitiveDataClass(
            name = "Object ${Random.nextInt()}",
            int = Random.nextInt(),
            double = Random.nextDouble(),
            float = Random.nextFloat(),
            boolean = Random.nextBoolean(),
            optional = null
    )
```

* Gson
  * Input object: <BR>
  ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=null)```
  * Serialized `String` <BR>
  ```{"name":"Object 770591202","int":-822616830,"double":0.8621779982333868,"float":0.33106828,"boolean":true}```
  * Deserialize object <BR>
  ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=null)```
  
* Kotlin serialization by default

    * Input object: <BR>
    ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=null)```
    * Serialized `String` <BR>
    ```{"name":"Object 770591202","int":-822616830,"double":0.8621779982333868,"float":0.33106828,"boolean":true,"optional":null}}```
    * Deserialize object <BR>
    ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=null)```

* Kotlin deserialize without null in json
    
    * Serialized `String` <BR>
    ```{"name":"Object 770591202","int":-822616830,"double":0.8621779982333868,"float":0.33106828,"boolean":true}}```
    * Deserialize object <BR>
    ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=Optional Srting)```

* Moshi

    * Input object: <BR>
    ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=null)```
    * Serialized `String` <BR>
    ```{"name":"Object 770591202","int":-822616830,"double":0.8621779982333868,"float":0.33106828,"boolean":true}}```
    * Deserialize object <BR>
    ```PrimitiveDataClass(name=Object 770591202, int=-822616830, double=0.8621779982333868, float=0.33106828, boolean=true, optional=Optional String))```
    