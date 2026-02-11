# DevOps PPE - Sistema de Gestión de Estudiantes

Aplicación REST API para la gestión de estudiantes desarrollada con Spring Boot, implementando una arquitectura en capas inspirada en el patrón MVC y prácticas DevOps con GitHub Actions.

## Descripción del Proyecto

Sistema que permite crear y listar estudiantes mediante endpoints REST, con persistencia en memoria y pipeline de CI/CD automatizado.

## Tecnologías Utilizadas

- **Java 25**
- **Spring Boot 4.0.2**
- **Gradle 9.3.0**
- **GitHub Actions** para CI/CD

## Requisitos Previos

- JDK 25 instalado
- Gradle 9.3.0 o superior (opcional, incluye Gradle Wrapper)
- Git

## Instrucciones de Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/andres200314/devOpsPPE.git
cd devOpsPPE
```

### 2. Compilar el proyecto

**En Linux/Mac:**
```bash
./gradlew build
```

**En Windows:**
```bash
.\gradlew.bat build
```

### 3. Ejecutar la aplicación

**En Linux/Mac:**
```bash
./gradlew bootRun
```

**En Windows:**
```bash
.\gradlew.bat bootRun
```

La aplicación se ejecutará en `http://localhost:8080`

### 4. Probar los endpoints

## Endpoints de la API

### POST /estudiantes
Crea un nuevo estudiante.

**Request:**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "carrera": "Ingeniería de Sistemas"
}
```

**Response exitoso (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "carrera": "Ingeniería de Sistemas"
}
```

**Response error (400 Bad Request):**
```json
"Ya existe un estudiante con el ID: 1"
```

### GET /estudiantes
Retorna la lista de todos los estudiantes registrados.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "carrera": "Ingeniería de Sistemas"
  },
  {
    "id": 2,
    "nombre": "María García",
    "carrera": "Ingeniería Civil"
  }
]
```


## Arquitectura del Proyecto

El proyecto implementa el patrón **MVC (Modelo-Vista-Controlador)** organizado por capas:

```
src/main/java/edu/upb/devops/estudiantes/
├── model/              # Entidades de dominio
│   └── Estudiante.java
├── repository/         # Capa de persistencia
│   ├── interfaces/
│   │   └── IEstudianteRepository.java
│   └── implementations/
│       └── EstudianteRepository.java
├── service/           # Lógica de negocio
│   ├── interfaces/
│   │   └── IEstudianteService.java
│   └── implementations/
│       └── EstudianteService.java
└── controller/        # Endpoints REST
    └── EstudianteController.java
```

**Características de la arquitectura:**
- Separación clara de responsabilidades por capas
- Inyección de dependencias por constructor
- Interfaces para desacoplamiento
- Persistencia en memoria usando ConcurrentHashMap

## Estrategia de Gestión de Versiones

### Estrategia Elegida: Trunk-Based Development

Se utiliza una estrategia basada en ramas de corta duración que se integran a `main` mediante Pull Requests.

**Justificación de la elección:**

1. **Simplicidad:** Para un proyecto pequeño, Trunk-Based es más ágil que GitFlow
2. **Integración continua:** Las ramas de features se integran frecuentemente a main, reduciendo conflictos
3. **Feedback rápido:** Los Pull Requests permiten revisión de código antes de merge
4. **Despliegue continuo:** Main siempre está en estado deployable
5. **Mejor para equipos pequeños:** GitFlow añade complejidad innecesaria con ramas develop/hotfix/release para proyectos de esta escala


## Pipeline de CI/CD (GitHub Actions)

### Archivo de configuración
`.github/workflows/ci.yml`

### Jobs del Pipeline

#### 1. **Build**
- Se ejecuta en: Push a cualquier rama y Pull Requests a main
- Acciones:
  - Checkout del código
  - Configuración de JDK 25 (Temurin)
  - Setup de Gradle
  - Compilación del proyecto con `./gradlew build`
  - Fix automático de permisos de ejecución para gradlew

#### 2. **Release**
- Se ejecuta en: Push a main (después de merge exitoso)
- Condición: `if: github.ref == 'refs/heads/main'`
- Depende de: Job `build` exitoso
- Acciones:
  - Generación de tag versionado automáticamente: `v1.0.<run_number>`
  - Creación de GitHub Release con el tag generado
  - Publicación del release con título y descripción automática

#### 3. **Dependency Submission**
- Se ejecuta en: Push y Pull Requests
- Acciones:
  - Generación del grafo de dependencias del proyecto
  - Envío a GitHub para análisis de seguridad (Dependabot)
  - Permite detectar vulnerabilidades en dependencias

### Herramientas del Pipeline

1. **actions/checkout@v4**
   - Clona el repositorio en el runner
   - Permite acceder al código fuente

2. **actions/setup-java@v4**
   - Configura JDK 25 con distribución Temurin
   - Prepara el entorno Java para compilación

3. **gradle/actions/setup-gradle@v4**
   - Configura Gradle con caché inteligente
   - Acelera builds subsecuentes

4. **gradle/actions/dependency-submission@v4**
   - Genera snapshot de dependencias
   - Integración con GitHub Security (Dependabot)

5. **softprops/action-gh-release@v2**
   - Crea releases automáticos en GitHub
   - Gestiona tags y versionado

### Beneficios del Pipeline

- **Automatización completa:** Build, test y release sin intervención manual
- **Versionado automático:** Incremento de versión en cada release
- **Seguridad:** Análisis de dependencias con cada cambio
- **Calidad:** Compilación exitosa requerida antes de merge
- **Trazabilidad:** Cada release vinculado a commit específico


## Estructura de Commits

Se utiliza **Conventional Commits** para mantener un historial limpio:

```
tipo(scope): descripción breve

- Detalle 1
- Detalle 2
```

**Tipos utilizados:**
- `feat`: Nueva funcionalidad
- `fix`: Corrección de errores
- `chore`: Cambios en configuración
- `docs`: Documentación

**Ejemplos:**
```
feat(model): definir modelo Estudiante con atributos id, nombre y carrera
feat(repository): implementar repositorio de estudiantes en memoria
feat(service): implementar servicio de estudiantes con validaciones
feat(controller): implementar endpoints REST POST y GET para estudiantes
```

## Autor

**Andrés Arroyave Cardona**
- GitHub: [@andres200314](https://github.com/andres200314)
- Repositorio: [devOpsPPE](https://github.com/andres200314/devOpsPPE)

## Licencia

Este proyecto fue desarrollado como parte de la evaluación de la materia DevOps en la Universidad Pontificia Bolivariana.
