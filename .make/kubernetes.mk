kubernetes.version:: ## Update kustomize image version | VERSION
	kubectl kustomize \
		edit \
		set image \
		ghcr.io/valentinlutz/spring-native-reference-project:${VERSION}

kubernetes.apply:: ## Deploy kustomize to kubernetes | MAVEN_PROFILE
	kubectl apply \
		-k deployment-kustomize/overlays/${MAVEN_PROFILE}