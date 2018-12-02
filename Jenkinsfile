pipeline {
    agent none

    stages {
        stage("Build") {
            agent {
                docker { image 'openjdk:8-jdk-alpine' }
            } steps {
                sh './gradlew clean compileJava'
                sh './gradlew test'
                sh './gradlew clean customfatJar'
                sh 'cd build/libs/ && ls -la'
            }
        }

        stage("Package") {
            agent any
            steps {
                echo 'build docker image with fatjar'
            }
        }

        stage("Deplo2QA") {
            agent any
            steps {
                echo 'deploy to qa'
            }
        }

        stage("ComponentTests") {
            parallel {
                stage('Api Tests') {
                    agent any
                    steps {
                        echo 'postman tests'
                    }
                }

                stage('CDC Tests') {
                    agent any
                    steps {
                        echo 'consumer driven contract tests'
                    }
                }
            }
        }

        stage("Deploy2Prod") {
            agent any
            steps {
                echo 'deploy to prod'
            }
        }
    }
}