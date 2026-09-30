# 🔧 Guía de Solución de Problemas

Esta guía te ayudará a resolver los problemas más comunes al ejecutar el backend de Pet Adoption API.

---

## 🔴 Problema: Error de compilación Maven

### Síntomas
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin
```

### Soluciones

#### 1. Verificar versión de Java
```powershell
java -version
```
Debe mostrar Java 21 o superior.

#### 2. Limpiar y recompilar
```powershell
mvn clean install -U
```

#### 3. Verificar JAVA_HOME
```powershell
echo $env:JAVA_HOME
```
Debe apuntar a la instalación de Java 21.

---

## 🔴 Problema: No se puede conectar a la base de datos

### Síntomas
```
org.postgresql.util.PSQLException: Connection refused
java.sql.SQLException: Connection to localhost:5432 refused
```

### Soluciones

#### 1. Verificar credenciales en application-local.properties
```properties
DB_URL=jdbc:postgresql://db.wnntlrvcajiarahpnjla.supabase.co:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=SC08oTVql2h5o5IJ
```

#### 2. Verificar perfil activo
Asegúrate de ejecutar con el perfil `local`:
```powershell
mvn spring-boot:run -D"spring-boot.run.profiles=local"
```

#### 3. Verificar conexión a Internet
```powershell
Test-Connection -ComputerName db.wnntlrvcajiarahpnjla.supabase.co -Count 2
```

#### 4. Probar puerto alternativo (Transaction Pooler)
Edita `application-local.properties`:
```properties
DB_URL=jdbc:postgresql://db.wnntlrvcajiarahpnjla.supabase.co:6543/postgres
```

---

## 🔴 Problema: Puerto 8080 ya en uso

### Síntomas
```
Port 8080 was already in use
Web server failed to start. Port 8080 was already in use.
```

### Soluciones

#### Opción 1: Cambiar el puerto
Edita `application.properties`:
```properties
server.port=8081
```

#### Opción 2: Cerrar proceso que usa el puerto
```powershell
# Ver qué proceso usa el puerto 8080
netstat -ano | findstr :8080

# Matar el proceso (reemplaza PID con el número encontrado)
taskkill /PID <PID> /F
```

---

## 🔴 Problema: Lombok no funciona

### Síntomas
```
cannot find symbol: method getName()
cannot find symbol: method setName(java.lang.String)
```

### Soluciones

#### 1. Limpiar y recompilar
```powershell
mvn clean compile
```

#### 2. Verificar que Lombok está en pom.xml
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

#### 3. Configurar IDE (IntelliJ IDEA)
1. Ir a Settings → Plugins
2. Buscar e instalar "Lombok Plugin"
3. Settings → Build → Compiler → Annotation Processors
4. Activar "Enable annotation processing"

#### 4. Configurar IDE (VS Code)
Instalar extensión: "Language Support for Java(TM) by Red Hat"

---

## 🔴 Problema: Tests fallan

### Síntomas
```
java.lang.NullPointerException
java.lang.AssertionError: expected: <...> but was: <null>
```

### Soluciones

#### 1. Ejecutar tests individualmente
```powershell
mvn test -Dtest=PetServiceTest
mvn test -Dtest=ApplicantServiceTest
mvn test -Dtest=AdoptionApplicationServiceTest
```

#### 2. Ver logs detallados
```powershell
mvn test -X
```

#### 3. Verificar mocks en los tests
Asegúrate de que todos los mocks estén configurados correctamente con `@Mock` y `@InjectMocks`.

---

## 🔴 Problema: Error de validación en DTOs

### Síntomas
```json
{
  "status": 400,
  "error": "Validation Error",
  "message": "Error en la validación de los datos"
}
```

### Soluciones

#### 1. Verificar que todos los campos requeridos estén presentes

Campos obligatorios en `AdoptionApplicationRequestDTO`:
```json
{
  "applicant": {
    "documentType": "CC",          // REQUERIDO
    "documentNumber": "1234567890", // REQUERIDO
    "name": "Test User",            // REQUERIDO
    "email": "test@example.com"     // REQUERIDO (formato email)
  },
  "petId": 1                        // REQUERIDO
}
```

#### 2. Verificar formato de email
```json
"email": "usuario@dominio.com"  // ✅ Correcto
"email": "usuario@dominio"       // ❌ Incorrecto
"email": "usuario"               // ❌ Incorrecto
```

#### 3. Verificar valores de enums
```json
"documentType": "CC"        // ✅ Correcto
"documentType": "cc"        // ❌ Incorrecto (case sensitive)
"documentType": "CEDULA"    // ❌ Incorrecto (valor no existe)
```

---

## 🔴 Problema: Mascota no encontrada (404)

### Síntomas
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Mascota no encontrada con ID: 999"
}
```

### Soluciones

#### 1. Verificar que la base de datos tiene datos
Ejecuta `verify-database.sql` en tu cliente PostgreSQL:
```sql
SELECT * FROM pets;
```

#### 2. Usar IDs válidos
Los IDs de prueba en la base de datos son: 1, 2, 3, 4, 5, 6

#### 3. Verificar conexión a base de datos
```
GET http://localhost:8080/api/pets
```
Debe retornar la lista de mascotas.

---

## 🔴 Problema: Error al mapear arrays de PostgreSQL

### Síntomas
```
org.postgresql.util.PSQLException: ERROR: column "vaccines" is of type vaccine_type[] but expression is of type character varying[]
```

### Soluciones

#### 1. Verificar que la entidad Pet tiene la anotación correcta
```java
@JdbcTypeCode(SqlTypes.ARRAY)
@Column(name = "vaccines", columnDefinition = "vaccine_type[]")
private List<String> vaccines;
```

#### 2. Usar los métodos helper
```java
// Para obtener vacunas
List<VaccineType> vaccines = pet.getVaccinesAsEnum();

// Para establecer vacunas
pet.setVaccinesFromEnum(Arrays.asList(VaccineType.RABIA));
```

---

## 🔴 Problema: Error con UUID

### Síntomas
```
org.hibernate.PropertyValueException: not-null property references a null or transient value
```

### Soluciones

#### 1. Verificar estrategia de generación en Applicant
```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
```

#### 2. No establecer el ID manualmente
El UUID se genera automáticamente, no lo establezcas en el código.

---

## 🔴 Problema: Dependencias no se descargan

### Síntomas
```
Could not resolve dependencies for project com.project:sdd
```

### Soluciones

#### 1. Forzar actualización de dependencias
```powershell
mvn clean install -U
```

#### 2. Limpiar repositorio local de Maven
```powershell
Remove-Item -Recurse -Force "$env:USERPROFILE\.m2\repository"
mvn clean install
```

#### 3. Verificar proxy/firewall
Si estás detrás de un proxy corporativo, configura Maven:
```xml
<!-- En settings.xml -->
<proxies>
  <proxy>
    <host>proxy.example.com</host>
    <port>8080</port>
  </proxy>
</proxies>
```

---

## 🔴 Problema: Aplicación no inicia

### Síntomas
```
Application run failed
org.springframework.beans.factory.BeanCreationException
```

### Soluciones

#### 1. Verificar que todas las dependencias están en pom.xml
```powershell
mvn dependency:tree
```

#### 2. Verificar logs completos
```powershell
mvn spring-boot:run -D"spring-boot.run.profiles=local" -X
```

#### 3. Verificar que no hay beans conflictivos
Asegúrate de que cada componente tiene la anotación correcta:
- `@Service` para servicios
- `@Repository` para repositorios
- `@RestController` para controladores
- `@Component` para mappers

---

## 🔴 Problema: JSON no se serializa correctamente

### Síntomas
```json
{
  "id": 1,
  "name": "Luna",
  "birthdate": [2024, 3, 15]  // ❌ Array en lugar de string
}
```

### Soluciones

#### Verificar configuración de Jackson en application.properties
```properties
spring.jackson.serialization.write-dates-as-timestamps=false
spring.jackson.time-zone=America/Bogota
```

---

## 🔴 Problema: CORS error en frontend

### Síntomas
```
Access to XMLHttpRequest blocked by CORS policy
```

### Soluciones

#### Agregar configuración CORS
Crea `WebConfig.java`:
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
```

---

## 🟢 Verificación de Salud del Sistema

### Script de diagnóstico completo

```powershell
Write-Host "=== Diagnóstico del Sistema ===" -ForegroundColor Cyan

# 1. Java
Write-Host "`n1. Versión de Java:" -ForegroundColor Yellow
java -version

# 2. Maven
Write-Host "`n2. Versión de Maven:" -ForegroundColor Yellow
mvn -version

# 3. Variables de entorno
Write-Host "`n3. JAVA_HOME:" -ForegroundColor Yellow
echo $env:JAVA_HOME

# 4. Puerto 8080
Write-Host "`n4. Puerto 8080:" -ForegroundColor Yellow
netstat -ano | findstr :8080

# 5. Conexión a BD
Write-Host "`n5. Conexión a Supabase:" -ForegroundColor Yellow
Test-Connection -ComputerName db.wnntlrvcajiarahpnjla.supabase.co -Count 2

Write-Host "`n=== Fin del Diagnóstico ===" -ForegroundColor Cyan
```

---

## 📞 Ayuda Adicional

Si ninguna de estas soluciones funciona:

1. **Revisa los logs completos**: Ejecuta con `-X` para ver logs detallados
2. **Verifica la documentación**: Lee `README.md` y `API-EXAMPLES.md`
3. **Revisa la implementación**: Consulta `IMPLEMENTATION-SUMMARY.md`

---

## ✅ Checklist de Verificación

Antes de pedir ayuda, verifica:

- [ ] Java 21 instalado y configurado
- [ ] Maven funciona correctamente
- [ ] Puerto 8080 disponible
- [ ] Archivo `application-local.properties` existe
- [ ] Conexión a Internet activa
- [ ] Base de datos Supabase accesible
- [ ] Dependencias descargadas (`mvn dependency:tree`)
- [ ] Proyecto compila sin errores (`mvn clean compile`)
- [ ] Tests pasan (`mvn test`)

---

¡Buena suerte! 🍀
