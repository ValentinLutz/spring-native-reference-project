help::
	@egrep -h '\s##\s' $(MAKEFILE_LIST) \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'


PROJECT_NAME ?= spring-native-reference-project
MAVEN_PROFILE ?= dev


clean:: ## clean all maven modules
	./mvnw clean

compile:: ## compile all maven modules
	./mvnw compile

test:: ## test all maven modules
	./mvnw test

package:: ## build all maven modules
	./mvnw package



build-java-native:: ## build native image
	./mvnw spring-boot:build-image \
		-pl app-java



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
		--name ${PROJECT_NAME} \
		-p 8080:8080 app-java:1.0.0-SNAPSHOT \
			-Dspring.profiles.active="dev"



migrate-db:: ## execute flyway database migration
	./mvnw flyway:migrate \
		-f migration-database \
		-P dev \
		-Dflyway.user=dev_user \
		-Dflyway.password=dev_password



app-java:: ## run spring java app
	./mvnw spring-boot:run \
		-pl app-java \
		-P ${MAVEN_PROFILE}


test-st:: ## run smoke tests
	./mvnw package \
		-pl test-smoke \
		-P it \
		-P ${MAVEN_PROFILE}

test-it:: ## run integration tests with spring rest docs
	./mvnw package \
		-pl test-integration \
		-P it \
		-P ${MAVEN_PROFILE}
