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

install: clean
	mvn install -DskipTests

test: clean
	mvn test

deploy:
	mvn deploy -DskipTests

version:
	mvn versions:set -DnewVersion=${VERSION}
ver: version

purge:
	mvn dependency:purge-local-repository@local-buggy
