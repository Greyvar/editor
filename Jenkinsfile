node("fedora") {
	stage("Build") {
		deleteDir()

		checkout scm

		sh "mvn package"
	}
}
