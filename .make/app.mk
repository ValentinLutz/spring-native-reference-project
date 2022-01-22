app.build:: ## Build image | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw spring-boot:build-image \
		-pl app-kotlin \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

app.tag:: ## Tag native image | DOCKER_REPOSITORY, PROJECT_NAME, VERSION
	docker tag \
		app-kotlin:${VERSION} \
		${DOCKER_REGISTRY}/${DOCKER_REPOSITORY}/${PROJECT_NAME}:${VERSION}
	docker tag \
		app-kotlin:${VERSION} \
		${DOCKER_REGISTRY}/${DOCKER_REPOSITORY}/${PROJECT_NAME}:latest

app.push:: ## Publish native image | DOCKER_REPOSITORY, PROJECT_NAME, VERSION
	docker push \
		${DOCKER_REGISTRY}/${DOCKER_REPOSITORY}/${PROJECT_NAME}:${VERSION}
	docker push \
		${DOCKER_REGISTRY}/${DOCKER_REPOSITORY}/${PROJECT_NAME}:latest

app.run:: ## Run app | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:run \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app.start:: ## Start app in background | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:start \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

app.stop:: ## Stop app in background | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw spring-boot:stop \
		-pl app-kotlin \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}
