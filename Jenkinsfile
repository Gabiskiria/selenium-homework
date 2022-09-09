pipeline {
  agent any
  stages {
    stage('project') {
      parallel {
        stage('project') {
          steps {
            powershell 'mvn compile'
          }
        }

        stage('version') {
          steps {
            powershell 'mvn --version'
          }
        }

      }
    }

  }
}