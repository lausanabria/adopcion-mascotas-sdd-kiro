
<div align="center">

# 🐾 Peluditos App

**Esta es una aplicación web para adopción de mascotas, permite listar las mascotas, filtrar por tipo y permitir que una persona pueda realizar una solicitud de adopción. <br>Esta app fue desarrollada con Kiro y SDD**

![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

[🎥 Ver tutorial](https://www.youtube.com/watch?v=VgBb0a8ylTI) 


</div>

---

## ✨ ¿Qué encuentras aquí?

Este repo contiene la aplicación de backend y frontend de Peluditos App, éstos fueron generados por un agente que se basó en las especificaciones definidas por mí para crear la app, en el caso del backend fueron especificaciones de funcionalidad y en el caso del frontend fueron especificaciones de diseño. En el backend las encontrarás en la carpeta .spect y en el frontend en la carpeta .kiro/steering

## ✨ Funcionalidades

- 🔍 **Interfaz principal:** Puedes ver el listado de mascotas disponibles para adoptar y un botón de adoptar si quieres hacer la solicitud de adopción
- 📱 **Responsive:** Es una app web que se ve puede usar desde un celular o computador

## ✨ Córrelo en tu máquina

**Necesitas instalar:** Java 21 y Node 20 o superior.
Si quieres crear tu propia aplicación lo puedes hacer con el lenguaje y framework de tu preferencia para lo que necesitarás instalar las respectivas versiones.

### 1. Clona el repo
```bash
git clone https://github.com/lausanabria/adopcion-mascotas-sdd-kiro.git
cd adopcion-mascotas-sdd-kiro
```

### 2. Ejecuta el back
```bash
cd peluditos-back
./mvnw spring-boot:run
```
o ábrelo desde Intellij y ejecuta la clase main SddApplication.Java

### 3. Ejecuta el front
```bash
cd peluditos-front
npm install
npm run dev
```

Y Listo! Abre en el navegador la url [http://localhost:5173](http://localhost:5173) 

## Prompts usados para la ejecución de los agentes

### Prompt para la creación del backend
```bash
Actúa como un desarrollador backend senior en Java y SpringBoot. Analiza los archivos requeriments.md y database.md  e implementa el código del backend completo siguiendo estrictamente lo especificado en ambos documentos.
Crea todos los archivos necesarios(enums, modelos, DTOs, repositorios, servicios, controladores y propiedades de configuración asegurándote de que el proyecto compile correctamente.
```

### Prompt para la creación del frontend
```bash
Crea la interfaz de peluditos en este proyecto React. Usa la API de .kiro/steering/api-peluditos.md y respeta el diseño de .kiro/steering/diseno-peluditos.md.
Una sola pantalla, encabezado con filtros por tipo de mascota, cuadrícula de mascotas disponibles y un botón en cada tarjeta que abre un modal para solicitar la adopción, con los campos que pide la API. Todo en español.
```

## 🤝 ¿Quieres dejarme una sugerencia?

Puedes escribirme a mi [Instagram](https://www.instagram.com/lausanabriac).

## 💜 Hecho por Lau Sanabria

[GitHub](https://github.com/lausanabria)

Si este proyecto te gustó, déjale una ⭐ estaré muy agradecida!
