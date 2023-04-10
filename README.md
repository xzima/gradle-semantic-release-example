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

Конфигурация commitlint находится в [commitlint.config.js](configs/commitlint.config.js).

### Automated dependency updates

Для автоматического обновления зависимостей используется [renovate].
Это приложение автоматически отслеживает все манифесты в приложении, которые содержат списки зависимостей и
обновляет их версии.

Конфигурационный файл для renovate находится в [renovate.json](.github/renovate.json) и содержит следующие директивы:

- использовать [Conventional Commits] в текстах коммитов и заголовках pull requests(build(deps): ...)
- добавлять label dependencies, указывать assignee и reviewer на основе [CODEOWNERS](.github/CODEOWNERS) в pull requests
- использовать ветку develop как базовую
- Использовать [Dependency Dashboard Approval workflow] для обновления всех зависимостей

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
