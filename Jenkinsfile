pipeline {
	agent any
	tools {
        maven 'Maven'
        jdk 'JDK'
    }
	stages {
		stage('Test') {
			steps {
                sh 'mvn clean test'
			}
			post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
		}
		stage('Build') {
			steps {
				sh 'mvn clean package -DskipTests'
		    }
		}
	}
}