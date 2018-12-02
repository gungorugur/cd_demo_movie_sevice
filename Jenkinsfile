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
                dir('build/libs'){
                    stash name: 'jar', includes: 'cd_demo_movie_sevice-all-1.0.jar'
                }
                sh 'ls -la'
                stash name: 'build', includes: '.build'

        stage("Package") {
            agent any
            steps {
                unstash 'jar'
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