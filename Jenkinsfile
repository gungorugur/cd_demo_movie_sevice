pipeline {
    agent none

    stages {
        stage("Build") {
            agent any
            steps {
                echo 'compile'
                echo 'unit test'
                echo 'fatjar'
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