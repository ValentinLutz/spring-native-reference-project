database.migrate:: ## Migrate database | MAVEN_PROFILE, FLYWAY_USER, FLYWAY_PASSWORD, MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw compile \
		flyway:clean \
		flyway:migrate \
		-pl migration-database \
		-P ${MAVEN_PROFILE} \
		-Dflyway.user=${FLYWAY_USER} \
		-Dflyway.password=${FLYWAY_PASSWORD} \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}