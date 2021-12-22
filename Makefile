help::
	@egrep -h '\s##\s' $(MAKEFILE_LIST) \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'


project-name = spring-native-reference-project


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



dev-docker-up:: ## start docker containers
	docker-compose -p ${project-name} \
		-f deployment-local/docker-compose.yaml \
		up -d

dev-docker-down:: ## shutdown docker containers
	docker-compose -p ${project-name} \
		-f deployment-local/docker-compose.yaml \
		 down

dev-docker-java:: ## run spring java native image
	docker run \
		--name ${project-name} \
		-p 8080:8080 app-java:1.0.0-SNAPSHOT \
			-Dspring.profiles.active="dev"

dev-migrate:: ## execute flyway database migration
	./mvnw flyway:migrate \
		-f migration-database \
		-P dev \
		-Dflyway.user=dev_user \
		-Dflyway.password=dev_password

dev-java:: ## run spring java app
	./mvnw spring-boot:run \
		-pl app-java \
		-P dev
