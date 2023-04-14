// original https://github.com/semantic-release/semantic-release/issues/1231#issuecomment-1063671157
const DOCKER_IMAGE_NAME = process.env.DOCKER_IMAGE_NAME

//----------------------------------------------------------------------------------------------------------------------
const publishCmd = `
./mvnw -B clean spring-boot:build-image -Dspring-boot.build-image.imageName=${DOCKER_IMAGE_NAME}
docker push ${DOCKER_IMAGE_NAME} --all-tags
`
const successCmd = `
echo "::set-output name=new_release_published::true"
echo "::set-output name=new_release_version::\${nextRelease.version}"
echo "::set-output name=branch_type::\${branch.type}"
echo "::set-output name=branch_name::\${branch.name}"
`

//----------------------------------------------------------------------------------------------------------------------

module.exports = {
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
        '@semantic-release/changelog',
        ['@semantic-release/exec', {publishCmd, successCmd}],
        ['@semantic-release/git', {assets: '.'}],
        '@semantic-release/github',
        ["semantic-release-slack-bot", {"notifyOnSuccess": true, "notifyOnFail": true, "markdownReleaseNotes": true}]
    ]
}
