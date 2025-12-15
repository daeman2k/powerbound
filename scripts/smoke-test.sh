#!/usr/bin/env bash
set -euo pipefail

# Simple smoke test runner for local Paper server
# Usage: ./scripts/smoke-test.sh path/to/paper.jar

PAPER_JAR=${1:-purpur-1.21.11.jar}
BUILD_DIR=$(pwd)
TMPDIR=$(mktemp -d)
echo "Using tmp dir: $TMPDIR"
pushd "$TMPDIR" >/dev/null

echo "Downloading Purpur if needed..."
if [ ! -f "$PAPER_JAR" ]; then
  # Try to download a reasonable Purpur build for 1.21.11 (best-effort)
  curl -Lo "$PAPER_JAR" "https://api.papermc.io/v2/projects/purpur/versions/1.21.11/builds/1/downloads/purpur-1.21.11-1.jar" || true
fi

mkdir -p plugins
cp "$BUILD_DIR/target/powerbound-0.1.0-SNAPSHOT.jar" plugins/ 2>/dev/null || echo "Plugin jar not found, run mvn package first"

echo "Writing eula"
echo "eula=true" > eula.txt

echo "Starting server (nogui) - will run until Ctrl-C"
java -Xmx1G -jar "$PAPER_JAR" --nogui

popd >/dev/null
