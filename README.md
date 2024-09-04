<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
<h1>gradle-semantic-release-example</h1>
<br />

![GitHub](https://img.shields.io/github/license/xzima/gradle-semantic-release-example?style=for-the-badge)

</div>
<details open="open">
<summary>Table of Contents</summary>

- [About](#about)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Development](#development)
    - [Installation](#installation)
- [Applied practices](#applied-practices)
- [Security](#security)
- [License](#license)
- [External resources](#external-resources)

</details>

---

## About

> An example project that is automatically deployed

### Built With

JVM Kotlin Gradle

## Getting Started

### Prerequisites

- [nvm](https://github.com/nvm-sh/nvm)
- [docker](https://docs.docker.com/engine/install)
- [sdkman](https://sdkman.io/install)

### Development

```shell
nvm use # need for configure nodejs
sdk env # need for configure jvm
npm i # need for install git hooks
```

### Installation

```shell
TODO
```

## Applied practices

### Issue automation

Для организации автоматического создания веток на основе правил из issue используется [create-issue-branch].

При задании assignee для issue автоматически создаётся ветка и pull request на основе правил
из [issue-branch.yml](.github/issue-branch.yml).

При создании pull request в тело добавляет текст формата `closes #XX`, что приводит при закрытии pull request к
автоматическому закрытию issue.
Если данное поведение избыточно, то до закрытия pull request следует удалить данный фрагмент из его тела.

### Github labels

Для синхронизации labels используется [micnncim/action-label-syncer],
а для проверки labels в pull requests -
[jesusvasquez333/verify-pr-label-action].

Для описания labels используется файл [labels.yml](.github/labels.yml).

### Conventional commits

Для проверки сообщений коммитов на соответствие
методологии [Conventional Commits] на уровне публикации коммитов
используется [husky]+[commitlint], а на уровне
CI [wagoid/commitlint-github-action].

Конфигурация commitlint находится в [commitlint.config.js](commitlint.config.mjs).

### Automated dependency updates

Для автоматического обновления зависимостей используется [renovate].
Это приложение автоматически отслеживает все манифесты в приложении, которые содержат списки зависимостей и
обновляет их версии.

Конфигурационный файл для renovate находится в [renovate.json](.github/renovate.json) и содержит следующие директивы:

- использовать [Conventional Commits] в текстах коммитов и заголовках pull requests(build(deps): ...)
- добавлять label dependencies, указывать assignee и reviewer на основе [CODEOWNERS](.github/CODEOWNERS) в pull requests
- использовать ветку develop как базовую
- Использовать [Dependency Dashboard Approval workflow] для обновления всех зависимостей

### Automated Gitflow pull requests

Для автоматического создания pull requests между главной веткой(master), ветка разработки(develop) и
ветками выпуска(rc,release) используется [gitflow-action].

В его обязанности входит:

- при появлении ветки rc, создание pull request rc->master
- при изменении ветки master, создание pull request master->develop

## CI test section

Тестирование в контексте CI происходит следующим образом:

- задача на тестирование запускается для PR и перед сборкой артефакта
- перед запуском тестов выполняется:
    - [sdkman/sdkman-action] - установка jdk
    - [gradle/actions/setup-gradle] - установка gradle wrapper и кеширование зависимостей
- выполняется `./gradlew build`, которые включает в себя тесты
- далее запускается [EnricoMi/publish-unit-test-result-action] - публикация результатов выполнения тестов в github
  actions и pull request

## Semantic version publication

Для организации версионирования на основе [semantic version] используется [semantic-release].
Конфигурационный файл для [semantic-release] находится в [release.config.js](release.config.js) и содержит следующие
плагины:

- semantic-release/commit-analyzer - определяет соответствие [Conventional Commits] scopes с уровнем публикуемого
  релиза.
- semantic-release/release-notes-generator - генерирует release notes на основе [Conventional Commits]
- semantic-release/github - публикует релиз в github и комментирует pull request и issues связанные с релизом
- semantic-release/exec - реализует кастомные этапы, в том числе публикацию в docker hub и выведение переменных из
  процесса [semantic-release] для использования в других этапах github actions
- semantic-release/changelog - генерирует [CHANGELOG.md](CHANGELOG.md) для стабильных релизов, исключающих prerelease
- semantic-release/git - выполняет фиксацию изменений в репозитории в процессе выполнения выкатки релиза. Включает
  изменения [CHANGELOG.md](CHANGELOG.md).

Для поддержки [semantic version] на уровне gradle используется плагин [git-version].
Для сборки рабочей версии артефакта следует `github action` указывает параметр `-Pversion`.
Для сборок не предназначенных для публикации или не являющихся стабильными [git-version] рассчитывает версию на основе
ветки и hash-commit. В случае если имеется незафиксированные изменения, то добавляется суффикс `+SNAPSHOT`.

## Docker hub publication

Для публикации образов docker используются возможности [Spring Boot Gradle Plugin].
Был кастомизирован [build.gradle.kts](build.gradle.kts) в области задачи `bootBuildImage`.
На основе параметров `dockerHubUsername` и `dockerHubPassword` определяется имя образа и необходимость его публикации в
docker hub. Параметр `withLatest` используется для создания/публикации дополнительного тега docker image - latest.

## Настройка лицензирования в проекте

https://github.com/scm-manager/gradle-license-plugin
https://github.com/CadixDev/licenser
https://github.com/hierynomus/license-gradle-plugin
https://github.com/jk1/Gradle-License-Report
https://github.com/chrimaeon/gradle-licenses-plugin
https://github.com/YumiProject/yumi-gradle-licenser

### Указание лицензии в репозитории

Добавить файл [LICENSE](LICENSE) в корень репозитория.

### Указание лицензии в исходных файлах

Для указания лицензий в исходных файлах используется
gradle-plugin [spotless](https://github.com/diffplug/spotless/tree/main/plugin-gradle).

При первоначальном генерировании заголовков следует использовать команду
`./gradlew spotlessApply -PspotlessSetLicenseHeaderYearsFromGitHistory=true`

Для обновления заголовков следует использовать команду `./gradlew spotlessApply`.

Для проверки заголовков следует использовать команду `./gradlew spotlessCheck`.

### Указание лицензии в jar-артефакте

Для удобства использования параметров в gradle.build используется
плагин [gradle-extensions-plugin](https://github.com/vlsi/vlsi-release-plugins/blob/master/plugins/gradle-extensions-plugin).

Список стандартных заголовков jar-манифеста представлен в:

- https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jar.html#Manifest_Specification
- https://docs.osgi.org/reference/bundle-headers.html

Для объявления контактной информации используется плагин [com.netflix.nebula.contacts](https://github.com/nebula-plugins/gradle-contacts-plugin). Смысл использования в том что он интегрируется с другими плагинами `com.netflix.nebula`.

Для наполнения заголовков jar-манифеста используется набор плагинов [com.netflix.nebula.info](https://github.com/nebula-plugins/gradle-info-plugin):
 - nebula.info-broker - база для использования всех остальных плагинов
 - nebula.info-basic - собирает базовую информацию сборки gradle
 - nebula.info-java - собирает информацию о версиях jvm
 - nebula.info-ci - собирает информацию сборки ci/cd
 - nebula.info-scm - собирает информацию git
 - nebula.info-jar - добавляет собранные атрибуты в jar-манифест

Информация об авторе и лицензии проставляется через добавление следующей логики в Jar-таску:

```kotlin
tasks.configureEach<Jar> {
    // ...
    manifest {
        attributes["Bundle-License"] = license
        attributes["Implementation-Vendor"] = author.asString()
    }
}
```

Для агрегации, проверки и указывания лицензий используемых зависимостей, в случае если происходит сборка fat-jar
используются gradle-плагины:

- [license-gather](https://github.com/vlsi/vlsi-release-plugins/tree/master/plugins/license-gather-plugin)
    - анализирует лицензии зависимостей(gatherLicense)
    - проверяет их согласно правилам(verifyLicenses)
- [stage-vote-release](https://github.com/vlsi/vlsi-release-plugins/tree/master/plugins/stage-vote-release-plugin)
    - плагин для публикации артефакта. Для сборки fat-jar выключен
    - генерирует расширенный файл лицензии(renderLicense)
    - добавляет расширенный файл лицензии и файлы лицензий зависимостей в jar-артефакт(licenseFiles)

Для того чтобы в jar-артефакт добавлялись файлы лицензий так-же необходимо в Jar-тасках прописать логику копирования:

```kotlin
tasks.configureEach<Jar> {
    // ...
    into("META-INF") {
        dependencyLicenses(licenseFiles)
    }
}
```

### Указание лицензии в docker-артефакте

Для указания лицензии и прочих атрибутов в docker-образе будет
использоваться [OCI-specified labels](https://github.com/opencontainers/image-spec/blob/main/annotations.md#pre-defined-annotation-keys).

Так как Spring Boot Gradle Plugin по умолчанию для сборки docker-image через [Paketo Buildpacks](https://paketo.io/)
использует сборщик [paketobuildpacks/builder-jammy-base](https://github.com/paketo-buildpacks/builder-jammy-base),
который в свою очередь использует [paketo-buildpacks/java](https://github.com/paketo-buildpacks/java),
который в свою очередь использует [paketo-buildpacks/image-labels](https://github.com/paketo-buildpacks/image-labels),
то для того чтобы указать необходимые labels достаточно в переменные окружения добавить значения описанные в
документации к последнему сборщику.

### Создание Dependencies Licence Report

## Security

gradle-semantic-release-example is provided **"as is"** without any **warranty**. Use at your own risk.

## License

This project is licensed under the **Apache Software License 2.0**.

See [LICENSE](LICENSE) for more information.

## External resources

- [husky]
- [commitlint]
- [wagoid/commitlint-github-action]
- [micnncim/action-label-syncer]
- [jesusvasquez333/verify-pr-label-action]
- [Conventional Commits]
- [Mermaid](https://mermaid-js.github.io/mermaid/#/)
- [semantic-release](https://semantic-release.gitbook.io/semantic-release/)

[husky]:https://typicode.github.io/husky

[commitlint]:https://commitlint.js.org/

[wagoid/commitlint-github-action]:https://github.com/wagoid/commitlint-github-action

[micnncim/action-label-syncer]:https://github.com/micnncim/action-label-syncer

[jesusvasquez333/verify-pr-label-action]:https://github.com/jesusvasquez333/verify-pr-label-action

[Conventional Commits]:https://www.conventionalcommits.org/en/v1.0.0/

[create-issue-branch]:https://github.com/robvanderleek/create-issue-branch

[renovate]:https://github.com/renovatebot/renovate

[Dependency Dashboard Approval workflow]:https://docs.renovatebot.com/key-concepts/dashboard/#dependency-dashboard-approval-workflow

[gitflow-action]:https://github.com/Logerfo/gitflow-action

[sdkman/sdkman-action]:https://github.com/sdkman/sdkman-action

[gradle/actions/setup-gradle]:https://github.com/gradle/actions/blob/main/docs/setup-gradle.md

[EnricoMi/publish-unit-test-result-action]:https://github.com/EnricoMi/publish-unit-test-result-action

[semantic version]:https://semver.org/

[semantic-release]:https://github.com/semantic-release/semantic-release

[git-version]:https://github.com/palantir/gradle-git-version

[kotlin-semver]:https://github.com/z4kn4fein/kotlin-semver

[Spring Boot Gradle Plugin]:https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/
