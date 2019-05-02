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
	mvn test

# do not use -Dmaven.test.skip=true
deploy: clean
	mvn deploy -DskipTests=true

version:
	mvn versions:set -DnewVersion=${VERSION}
	mvn package -DskipTests=true

ver: version

docker-kill:
	docker-compose -f $${CI_JOB_ID:-.indirect}/docker-compose.yml kill
	docker network rm testrail-network-$${CI_JOB_ID:-local} || true

docker-up: docker-kill
	docker network create testrail-network-$${CI_JOB_ID:-local}
	docker-compose -f $${CI_JOB_ID:-.indirect}/docker-compose.yml pull
	docker-compose -f $${CI_JOB_ID:-.indirect}/docker-compose.yml up --force-recreate --renew-anon-volumes -d
	docker ps
	@echo http://localhost/index.php Login: testrail@testrail.testrail Pass: testrail

docker-logs:
	mkdir ./logs || true
	docker logs testrail-web-$${CI_JOB_ID:-local}       >& logs/testrail-web.log
	docker logs testrail-fpm-$${CI_JOB_ID:-local}       >& logs/testrail-fpm.log
	docker logs testrail-migration-$${CI_JOB_ID:-local} >& logs/testrail-migration.log
	docker logs testrail-mysql-$${CI_JOB_ID:-local} 	>& logs/testrail-mysql.log

docker-clean:
	@echo Останавливаем все testrail-контейнеры
	docker kill $$(docker ps --filter=name=testrail -q) || true
	@echo Очистка докер контейнеров
	docker rm -f $$(docker ps -a -f --filter=name=testrail status=exited -q) || true
	@echo Очистка dangling образов
	docker rmi -f $$(docker images -f "dangling=true" -q) || true
	@echo Очистка testrail образов
	docker rmi -f $$(docker images --filter=reference='registry.gitlab.com/touchbit/image/testrail/*' -q) || true
	@echo Очистка всех неиспользуемых volume
	docker volume rm -f $$(docker volume ls -q) || true
	@echo Очистка всех testrail сетей
	docker network rm $$(docker network ls --filter=name=testrail -q) || true

itest:
	java -jar testrail4j-integration-tests/target/Corvus.jar --http-port $${TR_HTTP_PORT:-80} --https-port $${TR_HTTPS_PORT:-443}

iitest: build itest

clean-doc:
	rm -rf ./site

build-doc: clean-doc
	sphinx-build -W -b html ./docs ./site

build-doc-image:
	docker build --no-cache -t testrail4j/doc:${VERSION} -f ./.indirect/docs/Dockerfile .

run-doc-image:
	docker run -p 8080:80 testrail4j/doc:${VERSION}