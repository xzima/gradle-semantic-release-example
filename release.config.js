// original https://github.com/semantic-release/semantic-release/issues/1231#issuecomment-1063671157
const DOCKER_IMAGE_NAME = process.env.DOCKER_IMAGE_NAME
const GITHUB_OUTPUT = process.env.GITHUB_OUTPUT
const DOCKER_HUB_USERNAME = process.env.DOCKER_HUB_USERNAME
const DOCKER_HUB_PASSWORD = process.env.DOCKER_HUB_PASSWORD
// see https://github.com/semantic-release/env-ci/blob/master/services/github.js
const BRANCH_NAME = process.env.GITHUB_REF_NAME
//----------------------------------------------------------------------------------------------------------------------
const prepareCmd = `
    ./gradlew genDocs
`

const publishCmd = `
./gradlew bootBuildImage -PdockerHubUsername=${DOCKER_HUB_USERNAME} \
                         -PdockerHubPassword=${DOCKER_HUB_PASSWORD} \
                         -PimageName=${DOCKER_IMAGE_NAME}:\${nextRelease.version} \
                         -PwithLatest
`
const successCmd = `
echo "new_release_published=true" >> "${GITHUB_OUTPUT}"
echo "new_release_version=\${nextRelease.version}" >> "${GITHUB_OUTPUT}"
echo "branch_type=\${branch.type}" >> "${GITHUB_OUTPUT}"
echo "branch_name=\${branch.name}" >> "${GITHUB_OUTPUT}"
`

//----------------------------------------------------------------------------------------------------------------------

const config = {
    debug: true,
    tagFormat: '${version}',
    branches: [
        'master',
        {
            name: 'rc',
            prerelease: true,
        },
    ],
    plugins: [
        ['@semantic-release/commit-analyzer', {
            preset: "conventionalcommits",
            releaseRules: [
                {breaking: true, release: 'major'},
                {revert: true, release: 'patch'},
                // Conventional Commits
                {type: 'feat', release: 'minor'},
                {type: 'fix', release: 'patch'},
                {type: 'perf', release: 'patch'},
                {type: 'chore', release: 'patch'},
                // {type: 'docs', release: 'patch'},
                {type: 'build', release: 'patch'},
                {type: 'ci', release: 'patch'},
            ]
        }],
        ['@semantic-release/release-notes-generator', {
            preset: "conventionalcommits",
            presetConfig: {
                "types": [
                    {type: 'feat', section: '⭐ New Features'},
                    {type: 'fix', section: '🐞 Bug Fixes'},
                    {type: 'perf', section: '📈 Performance Improvements'},
                    {type: 'revert', section: '🔙 Reverts'},
                    {type: 'docs', section: '📔 Documentation'},
                    {type: 'style', section: 'Styles', hidden: true},
                    {type: 'chore', section: 'Miscellaneous Chores', hidden: true},
                    {type: 'refactor', section: 'Code Refactoring', hidden: true},
                    {type: 'test', section: 'Tests', hidden: true},
                    {type: 'build', section: '🔨 Build System'},
                    {type: 'ci', section: '⚙️ Continuous Integration'}
                ]
            }
        }],
        '@semantic-release/github',
        ['@semantic-release/exec', {prepareCmd, publishCmd, successCmd}],
        // ["semantic-release-slack-bot", {"notifyOnSuccess": true, "notifyOnFail": true, "markdownReleaseNotes": true}],
    ]
}

// see https://github.com/semantic-release/changelog/issues/51#issuecomment-682609394
if (config.branches.some(it => it === BRANCH_NAME || (it.name === BRANCH_NAME && !it.prerelease))) {
    config.plugins.push(
        '@semantic-release/changelog',
        ['@semantic-release/git', {assets: ['CHANGELOG.md']}]
    );
}

module.exports = config;
