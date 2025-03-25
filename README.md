# Vedrocraft API
## Использование в вашем проекте:
### Maven:
#### Repository:
Maven
```xml
<repository>
  <id>vedrocraft-repository-releases</id>
  <name>Vedrocraft Maven Repository</name>
  <url>https://maven.vedrocraft.space/releases</url>
</repository>
```
Gradle Kotlin
```kts
maven {
    name = "vedrocraftRepositoryReleases"
    url = uri("https://maven.vedrocraft.space/releases")
}
```
Gradle Groovy
```groovy
maven {
    name "vedrocraftRepositoryReleases"
    url "https://maven.vedrocraft.space/releases"
}
```

### Dependency
Maven
```xml
<dependency>
  <groupId>ru.sema1ary</groupId>
  <artifactId>{модуль}</artifactId>
  <version>{версия}</version>
</dependency>
```
Gradle Kotlin
```kts
implementation("ru.sema1ary:{модуль}:{версия}")
```
Gradle Groovy
```groovy
implementation "ru.sema1ary:{модуль}:{версия}"
```

Все модули: bukkit, common, velocity     
За версией можно следить на [maven.vedrocraft.space](https://maven.vedrocraft.space/)