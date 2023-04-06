# gradle-semantic-release-example

> An example project that is automatically deployed

## Что нужно сделать?

- [x] создан проект gradle+kotlin
- [x] добавил проверку commitLint для github actions и git hooks
- [x] `[amazing-github-template]` добавил синхронизацию labels и их проверку в pull requests
- [ ] `[amazing-github-template]` создан репозиторий на github с лицензией и readme
- [ ] добавить механизм обновления зависимостей через renovate
- [ ] `[task management][amazing-github-template]` добавить шаблоны issues
- [ ] `[task management]` добавить механизм создания pull requests на основе issues
- [ ] добавить автоматическое создание pull request для gitflow
- [ ] `[semantic-release]` добавить механизм обновления версии в gradle.properties
- [ ] `[semantic-release]` добавить генерацию CHANGELOG.md
- [ ] `[semantic-release]` организовать процесс выкатки релиза, чтобы его можно было
  частично-перезапускать([idea](https://github.com/semantic-release/semantic-release/discussions/2331))
- [ ] `[semantic-release]` добавить публикацию в docker hub
- [ ] `[semantic-release]` добавить нотификации об удачном/сломанном релизе
- [ ] `[test]` добавить unit-tests и их вызов в CI
- [ ] `[test]` добавить линтеры([idea](https://github.com/diffplug/spotless))
- [ ] `[license][test]` добавить проверку лицензионных заголовков
- [ ] `[license][gradle]` добавить функцию автоматического задания лицензионных заголовков
- [ ] `[license][semantic-release]` добавить генерацию LICENSES_THIRD_PARTY.txt
- [ ] `[gradle]` вынести зависимости в toml
- [ ] `[gradle]` добавлять покрытие тестами jacoco
- [ ] `[gradle]` добавлять аналогию maven-enforcer-plugin
- [ ] `[gradle]` проверять конфликты зависимостей
- [ ] добавить docker-compose для локальных запусков
- [ ]

## Quick start

- npm i

## Applied practices

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
