DIR=$(dirname $0)

$DIR/gradlew idea -s
$DIR/gradlew -p buildSrc idea
