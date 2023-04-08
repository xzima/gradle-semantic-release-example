<div style="text-align: center">
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

> **[?]**
> What are the project requirements/dependencies?

### Development

```shell
npm i # need for install git hooks
```

### Installation

> **[?]**
> Describe how to install and get started with the project.

## Applied practices

### Issue automation

Для организации автоматического создания веток на основе правил из issue используется [create-issue-branch].
Порядок действия:

- при задании assignee для issue автоматически создаётся ветка и pull request на основе правил
  из [issue-branch.yml](.github/issue-branch.yml)
- ?? что будет при закрытии pr

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
