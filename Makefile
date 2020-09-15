ifneq ($(MAKECMDGOALS),$(findstring $(MAKECMDGOALS),build-doc-image run-doc-image version))
    VERSION := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
else
	ifneq (version,$(firstword $(MAKECMDGOALS)))
		VERSION := latest
	endif
endif
$(eval $(VERSION):;@:)

SHELL=/bin/bash -o pipefail

clean:
	mvn clean

# do not use -Dmaven.test.skip=true
build: clean
	mvn package -DskipTests

test: clean
	mvn package

# do not use -Dmaven.test.skip=true
deploy: clean
	mvn deploy -DskipTests=true

version:
	mvn versions:set -DnewVersion=${VERSION}
	mvn package -DskipTests=true

ver: version

clean-doc:
	rm -rf ./site

build-doc: clean-doc
	sphinx-build -W -b html ./docs ./site

build-doc-image:
	docker build --no-cache -t testrail4j/doc:${VERSION} -f ./.indirect/docs/Dockerfile .

run-doc-image:
	docker run -p 8080:80 testrail4j/doc:${VERSION}