pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'jdk8.221'
    }
    stages {
        stage('Build') {
            steps {
                bat "mvn clean compile"
            }
        }
        stage('Ejecutar Pruebas') {
            steps {
                script {
                    try {
                        // Ejecutar pruebas y generar informe JSON
                        bat "mvn clean test -Dcucumber.filter.tags=${ESCENARIO}"
                        echo 'Ejecución de pruebas y generación de informe JSON exitosa'
                    } catch (ex) {
                        echo 'Caso de prueba fallido'
                    }
                }
            }
        }
        stage('Generar reporte') {
            steps {
                cucumber buildStatus: 'SUCCESS',
                reportTitle: 'Pruebas Taller Automatización',
                fileIncludePattern: '**/*cucumber.json',
                trendsLimit: 10,
                classifications: [
                    [
                        'key': 'Browser',
                        'value': 'Chrome'
                    ]
                ]
            }
        }
    }
    post {
    always {
                publishTestResults serverAddress: 'https://grupoaxo.atlassian.net',
                projectKey: 'GDC',
                format: 'Cucumber',
                filePath: 'target/cucumber-reports/cucumber.json',
                autoCreateTestCases: true,
                customTestCycle: [
                    name: 'Regresion',
                    description: 'Resultado de pruebas de regresion',
                    jiraProjectVersion: '10001',
                    folderId: '9840848',
                ]
            }
    }
}

