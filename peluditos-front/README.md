# Peluditos - Frontend 🐾

Interfaz web para el sistema de adopción de mascotas Peluditos.

## Características

- 🎨 Diseño moderno y responsivo con Tailwind CSS
- 🐶 Filtrado de mascotas por tipo (Todos, Perros, Gatos)
- 📝 Formulario de adopción con validación
- 🎭 Emojis únicos por mascota (consistentes entre recargas)
- 📱 Mobile-first, optimizado para celular, tablet y computador

## Tecnologías

- React 19
- Vite 8
- Tailwind CSS 4
- Lucide React (íconos)
- Google Fonts (Fredoka y Nunito)

## Instalación

```bash
# Instalar dependencias
npm install

# Copiar archivo de configuración
copy .env.example .env

# Iniciar servidor de desarrollo
npm run dev
```

## Configuración

Edita el archivo `.env` para configurar la URL de la API:

```
VITE_API_URL=http://localhost:8080
```

## Estructura del Proyecto

```
src/
├── components/
│   ├── Header.jsx           # Encabezado con logo y filtros
│   ├── PetCard.jsx          # Tarjeta de mascota
│   ├── PetCardSkeleton.jsx  # Estado de carga
│   └── AdoptionModal.jsx    # Modal de solicitud de adopción
├── constants/
│   └── theme.js             # Colores y configuración de diseño
├── services/
│   └── api.js               # Llamadas a la API
├── App.jsx                  # Componente principal
└── main.jsx                 # Punto de entrada
```

## Scripts

```bash
# Desarrollo
npm run dev

# Producción
npm run build
npm run preview

# Linting
npm run lint
```

## Notas de Diseño

- **Colores**: Centralizados en `src/constants/theme.js`
- **Tipografías**: Fredoka para títulos, Nunito para texto general
- **Emojis**: Se asignan basándose en el ID de la mascota para consistencia
- **Responsividad**: 2 columnas (móvil) → 4 (tablet) → 8 (desktop)

## Requisitos

- Node.js 18+
- API backend ejecutándose en `http://localhost:8080`
