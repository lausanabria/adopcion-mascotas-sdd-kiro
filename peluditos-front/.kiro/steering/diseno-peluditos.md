---
inclusion: always
---

# Diseño visual de Peluditos

Toda interfaz de este proyecto debe seguir este diseño. No cambies colores, tipografías ni estilos sin que se pida explícitamente.

## Stack de estilos

- Tailwind CSS.
- Íconos con lucide-react.
- Tipografías de Google Fonts: Fredoka para títulos y nombres de mascotas, Nunito para el resto del texto.
- Los colores se centralizan en `src/constants/theme.js` o en `tailwind.config`; nunca van sueltos en los componentes.

## Colores

- Fondo de la página: #FBFAFF
- Superficies (modal, filtros inactivos): #FFFFFF
- Texto principal: #2A2140
- Texto secundario: #7A7291
- Bordes: #ECE8F7
- Acento principal (botones, filtro activo, logo): #7048E8
- Acento suave (fondo del encabezado, botones secundarios, botón cerrar): #F1ECFF
- Badge "Disponible": texto #1E8A4F sobre #DDF6E6
- Errores: #D92D20
- Fondos pastel de las tarjetas, rotando por mascota: #FFE8DC, #DDF3EC, #E8E4FF, #FFF1C9, #DCEEFF, #FDE2EF

## Mascotas sin foto

Por ahora las mascotas no tienen fotos. En su lugar se muestra un emoji grande centrado en un recuadro con fondo blanco al 50 % de opacidad sobre el pastel de la tarjeta.

- Perros: 🐶 🐕 🦮 🐩 🐕‍🦺
- Gatos: 😺 🐱 🐈 🐈‍⬛ 😸

El emoji y el color pastel se eligen a partir del id de la mascota (por ejemplo, `id % cantidad`), nunca con `Math.random()`, para que cada mascota conserve siempre los mismos al recargar o filtrar.

## Estilo

- Encabezado: bloque `rounded-3xl` con fondo #F1ECFF, separado de los bordes de la pantalla, con dos huellas 🐾 decorativas grandes a la derecha, rotadas y con opacidad al 10 % (la segunda solo en pantallas medianas en adelante).
- Logo: círculo violeta con 🐾 y el nombre "Peluditos" en Fredoka.
- Título principal en Fredoka, `text-3xl` en celular y `text-5xl` en computador.
- Filtros tipo píldora con emoji; el activo va con fondo violeta y texto blanco, los inactivos con fondo blanco y texto oscuro.
- Tarjetas `rounded-3xl` con `p-2` y fondo pastel; el recuadro del emoji va dentro con `rounded-2xl`. Nombre `text-base` en Fredoka; raza y edad `text-xs` en color secundario.
- Cuadrícula: 2 columnas en celular, 4 en tablet y 8 en computador (`grid-cols-2 sm:grid-cols-4 lg:grid-cols-8`), `gap-3`, contenedor `max-w-7xl`.
- Todos los botones en forma de píldora (`rounded-full`). Los botones de acción en tarjetas crecen un poco al pasar el mouse (`hover:scale-105`).
- Modales `rounded-3xl`, ancho máximo `max-w-md`, overlay rgba(42,33,64,0.45), botón cerrar circular con fondo violeta suave.
- Inputs con borde de 2 px (#ECE8F7), `rounded-2xl` y fondo #FBFAFF; el borde se pone rojo si hay error y el mensaje va debajo del campo.
- Carga: tarjetas esqueleto del mismo tamaño que las reales.
- Todos los textos de la interfaz en español.

## Responsividad

La interfaz debe verse y usarse bien en celular, tablet y computador. Diseña primero para celular (mobile first) y agrega los ajustes para pantallas más grandes con los prefijos de Tailwind `sm:`, `md:` y `lg:`.

- Nunca debe aparecer scroll horizontal en ningún tamaño de pantalla.
- Encabezado: en celular, el título va en `text-3xl` y los filtros bajan a su propia línea si no caben (`flex-wrap`). En computador, el título va en `text-5xl`.
- Cuadrícula de mascotas: 2 columnas en celular, 4 en tablet (`sm:`) y 8 en computador (`lg:`).
- Tarjetas: los textos largos (nombre o raza) se cortan con puntos suspensivos (`truncate`) en lugar de romper el diseño de la tarjeta.
- Botones y filtros: en celular deben ser fáciles de tocar con el dedo, con una altura mínima de 44 px.
- Modal: en celular ocupa casi todo el ancho de la pantalla, con un margen pequeño a los lados (`p-4` en el contenedor). Si el contenido no cabe en la altura, el modal hace scroll por dentro en lugar de salirse de la pantalla.
- Inputs del modal: en celular el texto debe ser de al menos 16 px (`text-base`), para que el navegador no haga zoom automático al escribir.
- Espaciados: márgenes laterales de `px-4` en celular y un poco más amplios en pantallas grandes.
- Antes de dar una pantalla por terminada, revísala a 360 px (celular), 768 px (tablet) y 1280 px (computador).