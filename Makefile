

target:
	mkdir target  2>/dev/null

install-starter:
	mvn -f microhe-stacks/pom.xml clean install

install-package: target
	mvn -f microhe-stacks/pom.xml clean package
	cp microhe-stacks/microhe-starter/target/microhe-starter-1.0-SNAPSHOT.jar target