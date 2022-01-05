test:: ## Test all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw test \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

test.smoke:: ## Run smoke tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-smoke \
		-am \
		-P smoke-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

test.integration:: ## Run integration tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-integration \
		-am \
		-P integration-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}

test.load:: ## Run load tests | MAVEN_PROFILE, MAVEN_ARGS
	./mvnw test \
		-pl test-load \
		-am \
		-P load-test \
		-P ${MAVEN_PROFILE} \
		${MAVEN_ARGS}
