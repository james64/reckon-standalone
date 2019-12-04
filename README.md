# reckon-standalone

This is just a simple wrapper around reckon-core module of [ajoberstar/reckon](https://github.com/ajoberstar/reckon).
It allows one to use git tag based version inference outside of any particular (build) tool.

```
Reckon-standalone
Usage: reckon-standalone [options]
  --usage  <bool>
        Print usage and exit
  --help | -h  <bool>
        Print help message and exit
  --scope  <string?>
        Optional scope to pass to reckoner. One of "major", "minor", "patch". Defaults to "minor".
  --stage  <string?>
        Optional stage to pass to reckoner. Defaults to "rc".
  --git-dir  <string>
        Location of .git directory. Defaults to ".git"
```

# Example usage

```bash
# download coursier
curl -Lo coursier https://git.io/coursier-cli && chmod +x coursier

# infer version
version=$(coursier launch \
  -r 'https://jcenter.bintray.com/' \
  com.github.james64:reckon-standalone:0.0.1 \
  -- --scope=minor --stage=final)

# create tag
git tag $version

# run release
...

# push/remove tag
if [[ "$?" -eq 0 ]]; then
    git push --tags
else
    git tag -d tag
fi
```
