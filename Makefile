ifneq ($(MAKECMDGOALS),$(findstring $(MAKECMDGOALS),build-doc-image run-doc-image version))
    VERSION := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
else
	ifneq (version,$(firstword $(MAKECMDGOALS)))
		VERSION := latest
	endif
endif
$(eval $(VERSION):;@:)

clean:
	mvn clean

# do not use -Dmaven.test.skip=true
install: clean
	mvn install -DskipTests

test: clean
	mvn test

# do not use -Dmaven.test.skip=true
deploy:
	mvn deploy -DskipTests

version:
	mvn versions:set -DnewVersion=${VERSION}
ver: version

purge:
	mvn dependency:purge-local-repository@local-buggy

build-doc:
	python _docs/setup.py
	mkdocs build

tr-start: tr-stop
	docker-compose -f .indirect/docker-compose.yml up -d
	docker ps
	echo http://localhost/index.php Login: testrail@testrail.testrail Pass: testrail

tr-stop:
	docker-compose -f .indirect/docker-compose.yml kill

itest: install
	java -jar integration/target/Corvus.jar