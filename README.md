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

- [node.js/npm](https://nodejs.org/en/download)
- [docker](https://docs.docker.com/engine/install)
- [jvm](https://adoptium.net/temurin/releases)
- [gradle](https://gradle.org/install/)

### Development

```shell
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

Конфигурация commitlint находится в [commitlint.config.js](commitlint.config.js).

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

- задача на тестирование запускается для pull на ветках develop, master, rc и на pull request для остальных веток
- перед запуском тестов выполняется [actions/setup-java] - установка jdk и [gradle/gradle-build-action] - более
  продуктивное кеширование зависимостей
- выполняется `./gradlew build`, которые включает в себя тесты
- далее запускается [EnricoMi/publish-unit-test-result-action] - публикация результатов выполнения тестов в github
  actions и pull request

## Semantic version publication

Для организации версионирования на основе [semantic version] используется [semantic-release].
Конфигурационный файл для [semantic-release] находится в [release.config.js](release.config.js) и содержит следующие
плагины:

- semantic-release/commit-analyzer - определяет соответствие скоупов [Conventional Commits] с уровнем публикуемого
  релиза.
- semantic-release/release-notes-generator - генерирует release notes на основе [Conventional Commits]
- semantic-release/github - публикует релиз в github и комментирует pull request и issues связанные с релизом
- semantic-release/exec - реализует кастомные этапы, в том числе публикацию в docker hub и выведение переменных из
  процесса [semantic-release] для использования в других этапах github actions
- semantic-release/changelog - генерирует [CHANGELOG.md](CHANGELOG.md) для стабильных релизов, исключающих prerelease
- semantic-release/git - выполняет фиксацию изменений в репозитории в процессе выполнения выкатки релиза. Включает
  изменения [CHANGELOG.md](CHANGELOG.md).

Для поддержки [semantic version] на уровне gradle используется плагин [jgitver] и библиотека [kotlin-semver].
Так как [jgitver] рассчитана на работу с аннотированными тегами, а [semantic-release] - нет, то для правильного
взаимодействия [build.gradle.kts](build.gradle.kts) был кастомизирован в области расчета версии с использованием
библиотеки [kotlin-semver].

## Docker hub publication

Для публикации образов docker используются возможности [Spring Boot Gradle Plugin].
Был кастомизирован [build.gradle.kts](build.gradle.kts) в области задачи `bootBuildImage`.
На основе параметров `dockerHubUsername` и `dockerHubPassword` определяется имя образа и необходимость его публикации в
docker hub. Параметр `withLatest` используется для создания/публикации дополнительного тега docker image - latest.

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

[actions/setup-java]:https://github.com/actions/setup-java

[gradle/gradle-build-action]:https://github.com/gradle/gradle-build-action

[EnricoMi/publish-unit-test-result-action]:https://github.com/EnricoMi/publish-unit-test-result-action

[semantic version]:https://semver.org/

[semantic-release]:https://github.com/semantic-release/semantic-release

[jgitver]:https://github.com/jgitver/gradle-jgitver-plugin

[kotlin-semver]:https://github.com/z4kn4fein/kotlin-semver

[Spring Boot Gradle Plugin]:https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/
