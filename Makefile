help::
	@egrep -h '\s##\s' $(MAKEFILE_LIST) \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-25s\033[0m %s\n", $$1, $$2}'



PROJECT_NAME ?= spring-native-reference-project
MAVEN_PROFILE ?= dev
MAVEN_ARGS ?=
MAVEN_THREAD_ARGS ?= -T 1C
FLYWAY_USER ?= dev_user
FLYWAY_PASSWORD ?= dev_password



clean:: ## clean all maven modules
	./mvnw clean \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

compile:: ## compile all maven modules
	./mvnw compile \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

test:: ## test all maven modules
	./mvnw test \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

package:: ## build all maven modules
	./mvnw package \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}



build-java-native:: ## build native image
	./mvnw spring-boot:build-image \
		-pl app-java \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}



deploy-docker-up:: ## start docker containers
	docker-compose -p ${PROJECT_NAME} \
		-f deployment-docker/docker-compose.yaml \
		up -d

deploy-docker-down:: ## shutdown docker containers
	docker-compose -p ${PROJECT_NAME} \
		-f deployment-docker/docker-compose.yaml \
		 down

deploy-docker-java-native:: ## run spring java native image
	docker run \
		--rm \
		--name ${PROJECT_NAME} \
		-p 8080:8080 app-java:1.0.0-SNAPSHOT \
			-Dspring.profiles.active="dev"



migrate-db:: ## execute flyway database migration
	./mvnw flyway:migrate \
		-f migration-database \
		-P dev \
		-am \
		-Dflyway.user=${FLYWAY_USER} \
		-Dflyway.password=${FLYWAY_PASSWORD} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}



app-java-run:: compile ## run spring java app
	./mvnw spring-boot:run \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-java-start:: compile ## start spring java app in background
	./mvnw spring-boot:start \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-java-stop:: ## stop spring java app in background
	./mvnw spring-boot:stop \
		-pl app-java \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}



app-kotlin-run:: compile ## run spring java app
	./mvnw spring-boot:run \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-kotlin-start:: compile ## start spring java app in background
	./mvnw spring-boot:start \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app-kotlin-stop:: ## stop spring java app in background
	./mvnw spring-boot:stop \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}



test-st:: ## run smoke tests
	./mvnw test \
		-pl test-smoke \
		-am \
		-P st \
		-P ${MAVEN_PROFILE} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

test-it:: ## run integration tests with spring rest docs
	./mvnw test \
		-pl test-integration \
		-am \
		-P it \
		-P ${MAVEN_PROFILE} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

test-lt:: ## run integration tests with spring rest docs
	./mvnw test \
		-pl test-load \
		-am \
		-P lt \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

test-st-full:: deploy-docker-up migrate-db app-java-start test-st app-java-stop ## run smoke tests with starting and stopping containers and app

test-it-full:: deploy-docker-up migrate-db app-java-start test-it app-java-stop ## run integration tests with spring rest docs with starting and stopping containers and app

test-lt-full:: deploy-docker-up migrate-db app-java-start test-lt app-java-stop ## run load tests with starting and stopping containers and app
