def APP_VERSION
pipeline {
	agent any
	tools {
		maven 'Maven'
		jdk 'JDK'
	}
	environment {
		SSH_MAIN_SERVER = credentials("SSH_MAIN_SERVER")
	
		DATASOURCE_URL = credentials("DATASOURCE_URL")
		DATASOURCE_USERNAME = credentials("DATASOURCE_USERNAME")
		DATASOURCE_PASSWORD = credentials("DATASOURCE_PASSWORD")
		JWT_SECRET = credentials("JWT_SECRET")
		
		DATASOURCE_URL_PRUEBAS = credentials("DATASOURCE_URL_PRUEBAS")
		DATASOURCE_USERNAME_PRUEBAS = credentials("DATASOURCE_USERNAME_PRUEBAS")
		DATASOURCE_PASSWORD_PRUEBAS = credentials("DATASOURCE_PASSWORD_PRUEBAS")
		JWT_SECRET_PRUEBAS = credentials("JWT_SECRET_PRUEBAS")

		DOCKERHUB_CREDENTIALS = credentials('dockerhub-lmalvarez')
	}
	stages {
		stage('Get Version') {
			steps {
				script {
					APP_VERSION = sh (
						script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
						returnStdout: true
					).trim()
				}
				script {
                   CHANGELOG_APP_VERSION = sh (
                      script: "grep -m 1 -Po '[0-9]+[.][0-9]+[.][0-9]+' CHANGELOG.md",
                      returnStdout: true
                   ).trim()
                }
				script {
					currentBuild.displayName = "#" + currentBuild.number + " - v" + APP_VERSION
				}
				script{
				    if(APP_VERSION != CHANGELOG_APP_VERSION){
				        currentBuild.result = 'ABORTED'
                        error("Aborted: App version and changelog version mismatch")
				    }
                    if(currentBuild.previousSuccessfulBuild) {
                        lastBuild = currentBuild.previousSuccessfulBuild.displayName.replaceFirst(/^#[0-9]+ - v/, "")
                        echo "Last success version: ${lastBuild} \nNew version to deploy: ${APP_VERSION}"
                        if(lastBuild == APP_VERSION)  {
                             currentBuild.result = 'ABORTED'
                             error("Aborted: A version that already exists cannot be deployed a second time")
                        }
                    }
                }
			}
		}
		stage('Test') {
			steps {
				sh 'rm src/main/resources/application.properties || true'
                sh 'cp src/main/resources/template.properties src/main/resources/application.properties'
                sh "python replace-variables.py ${WORKSPACE}/src/main/resources/application.properties DATASOURCE_URL=${DATASOURCE_URL_PRUEBAS} DATASOURCE_USERNAME=${DATASOURCE_USERNAME_PRUEBAS} DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD_PRUEBAS} JWT_SECRET=${JWT_SECRET_PRUEBAS}"
				sh 'cat src/main/resources/application.properties'
			
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
				sh 'rm src/main/resources/application.properties || true'
                sh 'cp src/main/resources/template.properties src/main/resources/application.properties'
                sh "python replace-variables.py ${WORKSPACE}/src/main/resources/application.properties DATASOURCE_URL=${DATASOURCE_URL} DATASOURCE_USERNAME=${DATASOURCE_USERNAME} DATASOURCE_PASSWORD=${DATASOURCE_PASSWORD} JWT_SECRET=${JWT_SECRET}"
				sh 'cat src/main/resources/application.properties'
				
				sh 'mvn clean package spring-boot:repackage -DskipTests'

				sh "docker build . -t lmalvarez/personal-website-services:${APP_VERSION}"
			}
		}
		stage('Push') {
            steps {
                //sh "setfacl --modify user:jenkins:rw /var/run/docker.sock"

                sh '''echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin '''

                sh "docker push lmalvarez/personal-website-services:${APP_VERSION}"
            }
        }
	}
}