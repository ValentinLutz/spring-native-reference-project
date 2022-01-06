include .make/help.mk
include .make/test.mk
include .make/app.mk
include .make/docker.mk
include .make/database.mk

PROJECT_NAME ?= spring-native-reference-project
MAVEN_PROFILE ?= none-dev
MAVEN_ARGS ?=
MAVEN_THREAD_ARGS ?= -T 1C
FLYWAY_USER ?= test
FLYWAY_PASSWORD ?= test
VERSION ?= 1.0.0-SNAPSHOT
DOCKER_REGISTRY ?= ghcr.io
DOCKER_REPOSITORY ?= valentinlutz

clean:: ## Clean all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw clean \
		${MAVEN_THREAD_ARGS} \
		${MAVEN_ARGS}

compile:: ## Compile all maven modules | MAVEN_THREAD_ARGS, MAVEN_ARGS
	./mvnw compile \
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