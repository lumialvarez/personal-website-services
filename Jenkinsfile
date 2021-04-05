pipeline {
	agent any
	tools {
        maven 'Maven 3.6.3'
        jdk 'jdk11'
    }
	stages {
		stage('Test') {
			steps {
                sh 'mvn clean compile'
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deploy'
		    }
		}
	}
}
