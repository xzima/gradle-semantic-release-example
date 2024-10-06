## [1.3.2](https://github.com/xzima/gradle-semantic-release-example/compare/1.3.1...1.3.2) (2024-10-06)

### 🔨 Build System

* change project group from com.github to io.github ([bd7970f](https://github.com/xzima/gradle-semantic-release-example/commit/bd7970f3553b44c136ce2a6c7b17334386cb1dd4))

### ⚙️ Continuous Integration

* fix publish workflows and maven artifact configuration ([2c052b6](https://github.com/xzima/gradle-semantic-release-example/commit/2c052b62230316aeff033cc2d44c4c8c3aa64d06))

## [1.3.1](https://github.com/xzima/gradle-semantic-release-example/compare/1.3.0...1.3.1) (2024-10-05)

### ⚙️ Continuous Integration

* fix workflow declarations ([204c6eb](https://github.com/xzima/gradle-semantic-release-example/commit/204c6ebbef60348db4e5fcb43546edbaa8b4fb57))

## [1.3.0](https://github.com/xzima/gradle-semantic-release-example/compare/1.2.1...1.3.0) (2024-10-05)

### ⭐ New Features

* add conflict resolution strategy ([82eb3dc](https://github.com/xzima/gradle-semantic-release-example/commit/82eb3dc36005ab64fd884605f58c7a5a52709cb1))
* configure code coverage report by jacoco ([d9529f6](https://github.com/xzima/gradle-semantic-release-example/commit/d9529f665bf4f709768eae8291cac4c178895089))
* configure gradle wrapper validation and dependency graph upload ([8ed963c](https://github.com/xzima/gradle-semantic-release-example/commit/8ed963ca2181de4f172889837f981962ac8f2b03))
* configure ktlint and editorconfig ([33dff47](https://github.com/xzima/gradle-semantic-release-example/commit/33dff475fc30dae0ca3a7c10914b4798ea1d7a94))
* configure maven artifact publication ([728cfc2](https://github.com/xzima/gradle-semantic-release-example/commit/728cfc2b5895f524cb21e16c9d86718feaadbfc2))
* configure spotless linters ([1a66caf](https://github.com/xzima/gradle-semantic-release-example/commit/1a66caf556f869b62f89331539732d4032529dc0))

### 🐞 Bug Fixes

* gradle warning ([08f8e54](https://github.com/xzima/gradle-semantic-release-example/commit/08f8e54018067cb41af0b98dc685db06cfb22936))

### 📔 Documentation

* clear README.md ([a3561e3](https://github.com/xzima/gradle-semantic-release-example/commit/a3561e3a0713968017a58368d351b3c8b4cc1674))
* configure license in source files ([a9ad2cc](https://github.com/xzima/gradle-semantic-release-example/commit/a9ad2ccace4e8a0ed2fd8e9b51a36d067454e948))
* configure license propagation to docker-image ([147d635](https://github.com/xzima/gradle-semantic-release-example/commit/147d635e6da42a14570040b03f263f161677ac4f))
* configure license third party license verification and propagation to jar ([2ae14d4](https://github.com/xzima/gradle-semantic-release-example/commit/2ae14d4ae2bf54f4cd54d3ad078dd88b98b9cea5))
* optimize jar-manifest attribute calculation with use com.netflix.nebula plugins ([ec5d5ac](https://github.com/xzima/gradle-semantic-release-example/commit/ec5d5acfc40cc63bd8c24ba20844f5b366b9239a))

### ⚙️ Continuous Integration

* add maven artifact publication github workflow ([dbb08f3](https://github.com/xzima/gradle-semantic-release-example/commit/dbb08f33cb6de25afe0376fe065f42608260e5dd))
* clear debug logic ([5dd33d2](https://github.com/xzima/gradle-semantic-release-example/commit/5dd33d21a9a8f65aae36acfa07e8e02b5eac7709))
* remove redundant gradle wrapper validation and enable debug mode ([4cb1a52](https://github.com/xzima/gradle-semantic-release-example/commit/4cb1a520007b65c23ae20d7c7b004b08777ea066))

## [1.2.1](https://github.com/xzima/gradle-semantic-release-example/compare/1.2.0...1.2.1) (2024-08-19)

### 🐞 Bug Fixes

* warning on gradle build ([#61](https://github.com/xzima/gradle-semantic-release-example/issues/61)) ([514b228](https://github.com/xzima/gradle-semantic-release-example/commit/514b228451eb74fee375146a34c1a778e532266b)), closes [#58](https://github.com/xzima/gradle-semantic-release-example/issues/58)

## [1.2.0](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.5...1.2.0) (2024-08-19)

### ⭐ New Features

* centralize jvm configuration ([#57](https://github.com/xzima/gradle-semantic-release-example/issues/57)) ([1a7bd57](https://github.com/xzima/gradle-semantic-release-example/commit/1a7bd572a14de7a2d4c1a0f753277cb210c0023e))

### 🐞 Bug Fixes

* specify version for artifact in publish-docker-image.yml ([d8d2d27](https://github.com/xzima/gradle-semantic-release-example/commit/d8d2d279ccb40b1871db93609e43b47bc70e7f85))

## [1.1.5](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.4...1.1.5) (2024-08-17)

### 🔨 Build System

* **deps-dev:** bump braces ([15cf3ce](https://github.com/xzima/gradle-semantic-release-example/commit/15cf3ce7c3ac7a681a24bacb0e8591013ddf0b90))
* **deps:** update actions/checkout action to v4 ([928c454](https://github.com/xzima/gradle-semantic-release-example/commit/928c454de793d8719c6656ab68f8f10eed84b6ee))
* **deps:** update actions/setup-java action to v4 ([595d361](https://github.com/xzima/gradle-semantic-release-example/commit/595d361806a635ef05b2a6152ed5dfed1f7f3952))
* **deps:** update actions/setup-node action to v4 ([4b6410c](https://github.com/xzima/gradle-semantic-release-example/commit/4b6410c22af7f66f291076bf1b5769a13864464e))
* **deps:** update codecov/codecov-action action to v3.1.6 ([c369c00](https://github.com/xzima/gradle-semantic-release-example/commit/c369c00aed485734e5be998bef4ff03124057976))
* **deps:** update codecov/codecov-action action to v4 ([c826e7f](https://github.com/xzima/gradle-semantic-release-example/commit/c826e7fedc3163ad95dce11b800f52df68b6dfb5))
* **deps:** update commitlint monorepo to v17.8.1 ([4b90499](https://github.com/xzima/gradle-semantic-release-example/commit/4b904994f4ef7a284c8682aee0bfed0ecece6f7a))
* **deps:** update commitlint monorepo to v19 ([7df32dd](https://github.com/xzima/gradle-semantic-release-example/commit/7df32ddc356a7719eb329efe759bf8f32bb4a251))
* **deps:** update dependency conventional-changelog-conventionalcommits to v8 ([0a38d0a](https://github.com/xzima/gradle-semantic-release-example/commit/0a38d0a45ae421936392b47ba44b090eb9510a94))
* **deps:** update dependency gradle to v8.10 ([204bc30](https://github.com/xzima/gradle-semantic-release-example/commit/204bc3092d93efb5bc8e9814b703eb91a9d087d5))
* **deps:** update dependency husky to v9 ([19c28ce](https://github.com/xzima/gradle-semantic-release-example/commit/19c28ce347c3c26434b162e14489a89fa0aac9d7))
* **deps:** update dependency io.spring.dependency-management to v1.1.6 ([84dd9c5](https://github.com/xzima/gradle-semantic-release-example/commit/84dd9c5cff7c1d9efc6f517685f34094e5d040f3))
* **deps:** update dependency org.springframework.boot to v3.3.2 ([2e8ca1a](https://github.com/xzima/gradle-semantic-release-example/commit/2e8ca1a31e50a7b72a351f300a85d4f9cc05f57a))
* **deps:** update dependency semantic-release to v21.1.2 ([812ee36](https://github.com/xzima/gradle-semantic-release-example/commit/812ee36725bbcc9e9f6446c214b11a0756938dfa))
* **deps:** update dependency semantic-release to v24 ([f6733af](https://github.com/xzima/gradle-semantic-release-example/commit/f6733af2d7b921c521749936b4ec5e5381f1e665))
* **deps:** update gradle/actions action to v4 ([a35a61a](https://github.com/xzima/gradle-semantic-release-example/commit/a35a61a233460779a1e60cfea05f210e516d33f0))
* **deps:** update hmarr/debug-action action to v3 ([ec1496a](https://github.com/xzima/gradle-semantic-release-example/commit/ec1496a09e6986b85d35456685d5b6fc5567e8dc))
* **deps:** update kotlin monorepo to v1.9.25 ([6a3d97f](https://github.com/xzima/gradle-semantic-release-example/commit/6a3d97fd4856938e0a1be5a844fc164a1c4e3076))
* **deps:** update kotlin monorepo to v2 ([842e690](https://github.com/xzima/gradle-semantic-release-example/commit/842e690645eb6901f2685663f9ea6740f416f895))
* **deps:** update wagoid/commitlint-github-action action to v5.5.1 ([4bd6569](https://github.com/xzima/gradle-semantic-release-example/commit/4bd6569e3374b4928893861c2e1916ae24d22a9e))
* **deps:** update wagoid/commitlint-github-action action to v6 ([a03f8ae](https://github.com/xzima/gradle-semantic-release-example/commit/a03f8aeeff6dbe30762d5c3750287803a062f07e))

### ⚙️ Continuous Integration

* add more permissions for publish-docker-image.yml ([342e9f6](https://github.com/xzima/gradle-semantic-release-example/commit/342e9f62578fe56c2d3ddadbebf190796003b39b))

## [1.1.4](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.3...1.1.4) (2024-06-29)


### ⚙️ Continuous Integration

* add more permissions for publish-docker-image.yml ([f99e7a5](https://github.com/xzima/gradle-semantic-release-example/commit/f99e7a56b57c50dc21d85cfaf7e34aeba39950c1))

## [1.1.3](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.2...1.1.3) (2024-06-29)


### ⚙️ Continuous Integration

* add more permissions for publish-docker-image.yml ([8c9ea6a](https://github.com/xzima/gradle-semantic-release-example/commit/8c9ea6af0027dcf58627e2b44f542f6ffc87df87))

## [1.1.2](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.1...1.1.2) (2024-06-29)


### ⚙️ Continuous Integration

* add more permissions for publish-docker-image.yml ([2b12413](https://github.com/xzima/gradle-semantic-release-example/commit/2b12413d7175f1b88c46f549469e45f03d446497))

## [1.1.1](https://github.com/xzima/gradle-semantic-release-example/compare/1.1.0...1.1.1) (2024-06-29)


### ⚙️ Continuous Integration

* add more permissions for publish-docker-image.yml ([2108721](https://github.com/xzima/gradle-semantic-release-example/commit/21087213157a6eacdd6f7cedd0676fd3e7efedd4))

## [1.1.0](https://github.com/xzima/gradle-semantic-release-example/compare/1.0.4...1.1.0) (2024-06-29)


### ⭐ New Features

* upgrade concept ([#25](https://github.com/xzima/gradle-semantic-release-example/issues/25)) ([ddc88a9](https://github.com/xzima/gradle-semantic-release-example/commit/ddc88a944f0a82fc5bdaa44073ed5def138c0c8a))

## [1.0.4](https://github.com/xzima/gradle-semantic-release-example/compare/1.0.3...1.0.4) (2023-04-18)


### 🔨 Build System

* **deps:** update commitlint monorepo to v17.6.1 ([8c445f8](https://github.com/xzima/gradle-semantic-release-example/commit/8c445f8c044bd15709fcdba29c50ada94ace7b62))


### 📔 Documentation

* **release:** add CI test, Semantic version publication and Docker hub publication sections ([16bc3fe](https://github.com/xzima/gradle-semantic-release-example/commit/16bc3fe07a5f4671be9f5ef7d1fc442d617e9679))

## [1.0.3](https://github.com/xzima/gradle-semantic-release-example/compare/1.0.2...1.0.3) (2023-04-17)


### 🔨 Build System

* **deps:** update commitlint monorepo to v17.6.1 ([c3daf3a](https://github.com/xzima/gradle-semantic-release-example/commit/c3daf3aa76712c29fdf5f6f26a55a983b56bb034))
* **deps:** update dependency gradle to v8.1 ([9372dcb](https://github.com/xzima/gradle-semantic-release-example/commit/9372dcb5b4b066eb8f17e232382fc61e7c6192c9))

## [1.0.2](https://github.com/xzima/gradle-semantic-release-example/compare/1.0.1...1.0.2) (2023-04-15)


### 🔨 Build System

* **deps:** update wagoid/commitlint-github-action action to v5.4.1 ([67626a3](https://github.com/xzima/gradle-semantic-release-example/commit/67626a31edc2b22106ca5bf7cc2bc53a1a2d3eec))

## [1.0.1](https://github.com/xzima/gradle-semantic-release-example/compare/1.0.0...1.0.1) (2023-04-15)


### 🐞 Bug Fixes

* change separator for version calculation logic ([95b60bc](https://github.com/xzima/gradle-semantic-release-example/commit/95b60bcd6ccf0c0b5cfb85081e163e262a32577b))

## 1.0.0 (2023-04-15)


### 📔 Documentation

* **readme:** add Automated dependency updates section ([3bcbf13](https://github.com/xzima/gradle-semantic-release-example/commit/3bcbf136c3d30e7a1007043bb794ce4f6bc05237))
* **readme:** close issue automation and write summery about it ([9731c61](https://github.com/xzima/gradle-semantic-release-example/commit/9731c61b945fd56fdd70ba531c1fccc5397e33d9))
* **readme:** complete Issue automation section ([ee2e593](https://github.com/xzima/gradle-semantic-release-example/commit/ee2e59372a7ed28cc5a3bf29403c7a1e3e67f139))
* **readme:** write checklist to README.md ([302440a](https://github.com/xzima/gradle-semantic-release-example/commit/302440a517934916ed1312db11f0edff373897be))
* write good README.md ([#3](https://github.com/xzima/gradle-semantic-release-example/issues/3)) ([3f78402](https://github.com/xzima/gradle-semantic-release-example/commit/3f7840222dc0d865d67674a8bf60089f095743e8))


### 🔨 Build System

* **deps:** move and extend renovate config ([724562a](https://github.com/xzima/gradle-semantic-release-example/commit/724562a52669f9bb4e88d1206449eae12133227d))
* **deps:** update renovate.json ([6d4443a](https://github.com/xzima/gradle-semantic-release-example/commit/6d4443ac8f4d2c096881468e1b6aa401a63caf87))
* **deps:** update renovate.json ([db6132d](https://github.com/xzima/gradle-semantic-release-example/commit/db6132da03364a0178d566e8eba70a246b408bfc))
* **deps:** update wagoid/commitlint-github-action action to v5.4.0 ([f75b6bd](https://github.com/xzima/gradle-semantic-release-example/commit/f75b6bd406809888b72fc5d1e815d0f49ec2091f))
* **git:** add .gitignore with JetBrains, gradle, kotlin and node templates ([a1ac37f](https://github.com/xzima/gradle-semantic-release-example/commit/a1ac37f4d21ce1bcbf5c1dda68c652123adfbe60))
* **gradle:** init project with idea "kotlin multiplatform" template ([69d49a9](https://github.com/xzima/gradle-semantic-release-example/commit/69d49a95b1bb0fd32c20d3b6df57250844cffcdb))
* **gradle:** reconfigure gradle with version catalog toml ([10d99e3](https://github.com/xzima/gradle-semantic-release-example/commit/10d99e3b2db6b1b50b346aad5ed29a7c751c0d01))


### ⭐ New Features

* **release:** configure release build and ci logic ([fa0e99f](https://github.com/xzima/gradle-semantic-release-example/commit/fa0e99f036fb5fe4c01aec3f8c3b9e00d3df423d))


### ⚙️ Continuous Integration

* fix caching and release.config.js ([bc192f3](https://github.com/xzima/gradle-semantic-release-example/commit/bc192f302b56c7a0ec3722f1b866584526dfda52))
* fix DEPLOY.yml declaration ([ab8f411](https://github.com/xzima/gradle-semantic-release-example/commit/ab8f41186af98b782532629c598b5f54d83b7b81))
* fix logic in release.config.js ([6698710](https://github.com/xzima/gradle-semantic-release-example/commit/669871016dc64b7c029ccd91f138dae5e180b070))
* fix version calculation logic and hide debug from DEPLOY.yml ([620c327](https://github.com/xzima/gradle-semantic-release-example/commit/620c32796df883eaedea0190ef700970fb4aa268))
* **github:** add Conventional Commits lints ([c49a24d](https://github.com/xzima/gradle-semantic-release-example/commit/c49a24d3d6046e67c76fb21c0376658bf1bb7088))
* **github:** add gitflow-action ([a2232c3](https://github.com/xzima/gradle-semantic-release-example/commit/a2232c3fadeadd312f3308d8085a3f9c0dd9b505))
* **github:** add label sync and label pr check ([ce45e34](https://github.com/xzima/gradle-semantic-release-example/commit/ce45e347482edda408b6b7894e3734be577ca622))
* **github:** add label sync and label pr check ([a974b80](https://github.com/xzima/gradle-semantic-release-example/commit/a974b80d8e792a4da13239c60f18d25aab97b45d))
* **github:** add permissions for label sync ([c4ca119](https://github.com/xzima/gradle-semantic-release-example/commit/c4ca1191baa8b2d28a87fa8b8cc18142826b2a32))
* **github:** fix commitlint action on CI ([afcbf72](https://github.com/xzima/gradle-semantic-release-example/commit/afcbf72591e91e20b0386be7c5fa13aaf1ff9d1d))
* **github:** fix label sync ([7a6642f](https://github.com/xzima/gradle-semantic-release-example/commit/7a6642f5483ac340cc04569361384ed204f7240e))
