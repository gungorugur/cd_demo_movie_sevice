pipeline {
    agent none

    stages {
        stage("Build") {
            agent {
                docker { image 'gradle:jdk8-slim' }
            }
            steps {
                sh 'gradle clean compileJava'
                sh 'gradle clean test'
                junit '**/test-results/test/*.xml'
                sh 'gradle clean customFatJar'
                sh 'ls -la'
                sh 'cd build/libs/ &&ls -la'
                stash includes: 'build/libs/*.jar', name: 'app'
                stash includes: '.build', name: 'build'


            }
        }

        stage("Package") {
            agent any
            steps {
                unstash 'app'
                unstash 'build'
                sh 'ls -la'
                sh 'cd .build && ls  -la'
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
            failFast true
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