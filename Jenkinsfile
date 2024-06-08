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
                        echo 'Ejecucion de pruebas y generacion de informe JSON exitosa'
                    } catch (ex) {
                        echo 'Caso de prueba fallido'
                    }
                }
            }
        }
        stage('Generar reporte') {
            steps {
                script {
                    cucumber buildStatus: 'SUCCESS',
                            reportTitle: 'Pruebas Taller Automatizacion',
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
    }
    post {
        always {
            echo 'This will always be executed'
        }
    }
}


