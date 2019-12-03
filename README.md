# reckon-standalone

This is just a simple wrapper around reckon-core module of [ajoberstar/rekon](https://github.com/ajoberstar/reckon).
It allows one to use git tag based version inference outside of any particular (build) tool.

```
Usage: [options]
  --usage  <bool>
        Print usage and exit
  --help | -h  <bool>
        Print help message and exit
  --scope  <string?>
  --stage  <string?>
  --git-dir  <string>

```

```bash
curl -Lo coursier https://git.io/coursier-cli && chmod +x coursier
version=$(coursier launch com.github.james64:reckon-standalone:0.0.1 -- --scope=minor --stage=final)
git tag $version
build_cmd
if [[ "$?" -eq 0 ]]; then
    git push --tags
else
    git tag -d tag
fi
```
