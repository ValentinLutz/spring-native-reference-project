help::
	@egrep -h '\s##\s' $(MAKEFILE_LIST) \
		| awk -F':.*?## | \\| ' '{printf "\033[36m%-18s \033[37m %-35s \033[35m%s \n", $$1, $$2, $$3}'



PROJECT_NAME ?= spring-native-reference-project
MAVEN_PROFILE ?= none-dev
MAVEN_ARGS ?=
MAVEN_THREAD_ARGS ?= -T 1C
FLYWAY_USER ?= test
FLYWAY_PASSWORD ?= test
VERSION ?= 1.0.0-SNAPSHOT
DOCKER_REPOSITORY ?= ValentinLutz



clean:: ## Clean all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw clean \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

compile:: ## Compile all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw compile \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

test:: ## Test all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw test \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

package:: ## Build all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw package \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

version:: ## Set version of all maven modules | VERSION, MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw versions:set \
		-DnewVersion=${VERSION} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}



app-build-native:: ## Build native image | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw spring-boot:build-image \
		-pl app-java \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

app-tag-native:: ## Publish native image | DOCKER_REPOSITORY, PROJECT_NAME, VERSION
	docker tag \
		app-java:${VERSION} \
		${DOCKER_REPOSITORY}/${PROJECT_NAME}:${VERSION}

app-push-native:: ## Publish native image | DOCKER_REPOSITORY, PROJECT_NAME, VERSION
	docker push \
		${DOCKER_REPOSITORY}/${PROJECT_NAME}:${VERSION}



docker-up:: ## Start docker containers | PROJECT_NAME
	docker-compose -p ${PROJECT_NAME} \
		-f deployment-docker/docker-compose.yaml \
		up -d

docker-down:: ## Shutdown docker containers | PROJECT_NAME
	docker-compose -p ${PROJECT_NAME} \
		-f deployment-docker/docker-compose.yaml \
		 down

docker-app-native:: ## Run native image | PROJECT_NAME
	docker run \
		--rm \
		--name ${PROJECT_NAME} \
		-p 8080:8080 app-java:1.0.0-SNAPSHOT \
			-Dspring.profiles.active="NONE-DEV"



migrate-db:: ## Migrate database | MAVEN_PROFILE, FLYWAY_USER, FLYWAY_PASSWORD, MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw flyway:clean \
		flyway:migrate \
		-pl migration-database \
		-P ${MAVEN_PROFILE} \
		-Dflyway.user=${FLYWAY_USER} \
		-Dflyway.password=${FLYWAY_PASSWORD} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}



app-run:: ## Run app | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:run \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-start:: ## Start app in background | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:start \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-stop:: ## Stop app in background | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:stop \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}



test-smoke:: ## Run smoke tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-smoke \
		-am \
		-P smoke-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

test-integration:: ## Run integration tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-integration \
		-am \
		-P integration-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

test-load:: ## Run load tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-load \
		-am \
		-P load-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}
