-- Creacion de la base de datos (ya manejada por Docker environment)

-- Tabla de Categorias
CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Suscripciones
CREATE TABLE IF NOT EXISTS suscripciones (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    costo DECIMAL(10, 2) NOT NULL,
    fecha_renovacion DATE,
    estado VARCHAR(50) DEFAULT 'ACTIVO',
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Ingresos
CREATE TABLE IF NOT EXISTS ingresos (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha DATE DEFAULT CURRENT_DATE,
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Gastos
CREATE TABLE IF NOT EXISTS gastos (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(100),
    fecha DATE DEFAULT CURRENT_DATE,
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Inserts iniciales opcionales
INSERT INTO categorias (nombre) VALUES ('Software'), ('Marketing'), ('Servicios'), ('Hardware'), ('Otros') ON CONFLICT DO NOTHING;
