pipeline {
  agent any
  stages {
    stage('maven project') {
      parallel {
        stage('maven project') {
          steps {
            powershell(script: 'mvn compile', returnStatus: true)
          }
        }

        stage('maven version') {
          steps {
            powershell 'mvn --version'
          }
        }

      }
    }

    stage('End') {
      steps {
        echo 'End'
      }
    }

  }
}