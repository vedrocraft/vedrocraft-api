# Vedrocraft API
## Использование в вашем проекте:
### Maven:
#### Repository:
Maven
```xml
<repository>
  <id>reposilite-repository-releases</id>
  <name>Reposilite Repository</name>
  <url>https://maven.vedrocraft.space/releases</url>
</repository>
```
Gradle Kotlin
```kts
maven {
    name = "reposiliteRepositoryReleases"
    url = uri("https://maven.vedrocraft.space/releases")
}
```
Gradle Groovy
```groovy
maven {
    name "reposiliteRepositoryReleases"
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