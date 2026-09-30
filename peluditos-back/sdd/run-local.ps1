# Script PowerShell para ejecutar la aplicación en modo local
# Uso: .\run-local.ps1

Write-Host "================================================" -ForegroundColor Cyan
Write-Host "   Pet Adoption API - Iniciando Aplicación" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Verificar si Maven está instalado
Write-Host "Verificando Maven..." -ForegroundColor Yellow
$mavenVersion = & mvn -version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ Maven encontrado" -ForegroundColor Green
    Write-Host $mavenVersion[0] -ForegroundColor Gray
} else {
    Write-Host "✗ Maven no encontrado. Intentando usar Maven Wrapper..." -ForegroundColor Red
}

Write-Host ""
Write-Host "Compilando el proyecto..." -ForegroundColor Yellow
mvn clean compile

if ($LASTEXITCODE -ne 0) {
    Write-Host "✗ Error en la compilación" -ForegroundColor Red
    exit 1
}

Write-Host "✓ Compilación exitosa" -ForegroundColor Green
Write-Host ""

Write-Host "Ejecutando tests..." -ForegroundColor Yellow
mvn test

if ($LASTEXITCODE -ne 0) {
    Write-Host "⚠ Algunos tests fallaron, pero continuaremos..." -ForegroundColor Yellow
} else {
    Write-Host "✓ Tests exitosos" -ForegroundColor Green
}

Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "   Iniciando servidor Spring Boot" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Perfil activo: local" -ForegroundColor Magenta
Write-Host "Puerto: 8080" -ForegroundColor Magenta
Write-Host "URL: http://localhost:8080" -ForegroundColor Magenta
Write-Host ""
Write-Host "Presiona Ctrl+C para detener el servidor" -ForegroundColor Yellow
Write-Host ""

# Ejecutar la aplicación con el perfil local
mvn spring-boot:run -D"spring-boot.run.profiles=local"
