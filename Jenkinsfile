pipeline {
    agent none

    stages {
        stage("Build") {
            agent {
                docker {
                    image 'gradle:jdk8-slim'
                    args '-v $HOME/.gradle/caches:/home/gradle/.gradle/caches'
                }
            }
            steps {
                sh 'gradle clean compileJava'
                sh 'gradle clean test'
                sh 'gradle clean customFatJar'
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