-- Creacion de la base de datos (ya manejada por Docker environment)

-- Tabla de Categorias
CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla de Usuarios
-- Sincronizado con la entidad JPA Usuario.java
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100) NOT NULL,
    nombre VARCHAR(100),
    telefono VARCHAR(30),
    fecha_nacimiento DATE,
    cuenta_bancaria VARCHAR(100),
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) DEFAULT 'USER',
    moneda_base VARCHAR(10) DEFAULT 'USD',
    zona_horaria VARCHAR(100) DEFAULT 'America/Lima',
    porcentaje_impuesto DECIMAL(5, 2),
    meta_ingreso_mensual DECIMAL(10, 2),
    limite_gastos DECIMAL(10, 2),
    alertas_renovaciones BOOLEAN DEFAULT true,
    alertas_inyectar_suscripciones BOOLEAN DEFAULT true,
    razon_social VARCHAR(255),
    ruc_nif VARCHAR(50),
    direccion_fiscal TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Suscripciones
CREATE TABLE IF NOT EXISTS suscripciones (
    id SERIAL PRIMARY KEY,
    servicio VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    monto DECIMAL(10, 2) NOT NULL,
    moneda VARCHAR(3) NOT NULL DEFAULT 'USD',
    ciclo VARCHAR(20) DEFAULT 'MENSUAL',
    proxima_renovacion DATE,
    status VARCHAR(50) DEFAULT 'ACTIVA',
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Ingresos
CREATE TABLE IF NOT EXISTS ingresos (
    id SERIAL PRIMARY KEY,
    proyecto_nombre VARCHAR(255) NOT NULL,
    monto_bruto DECIMAL(10, 2) NOT NULL,
    retencion DECIMAL(10, 2) DEFAULT 0.00,
    monto_neto DECIMAL(10, 2) NOT NULL,
    moneda VARCHAR(3) NOT NULL DEFAULT 'USD',
    status VARCHAR(50) DEFAULT 'PENDIENTE',
    fecha DATE DEFAULT CURRENT_DATE,
    fecha_emision DATE,
    fecha_vencimiento DATE,
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Gastos
CREATE TABLE IF NOT EXISTS gastos (
    id SERIAL PRIMARY KEY,
    concepto VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    moneda VARCHAR(3) NOT NULL DEFAULT 'USD',
    categoria VARCHAR(100),
    es_deducible BOOLEAN DEFAULT false,
    es_recurrente BOOLEAN DEFAULT false,
    cantidad INTEGER DEFAULT 1,
    fecha DATE DEFAULT CURRENT_DATE,
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Inserts iniciales
INSERT INTO categorias (nombre) VALUES ('TECNOLOGIA_SAAS'), ('SERVICIOS_PUBLICOS_CONECTIVIDAD'), ('COWORKING'), ('EDUCACION_CAPACITACION'), ('IMPUESTOS_LEGAL'), ('PERSONAL') ON CONFLICT DO NOTHING;
INSERT INTO usuarios (nombres, apellido_paterno, apellido_materno, email, password, rol)
VALUES ('Juan', 'Perez', 'Lopez', 'admin@test.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER')
ON CONFLICT DO NOTHING;

INSERT INTO ingresos (proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento, usuario_id) VALUES ('Desarrollo Web App', 1500.00, 150.00, 1350.00, 'USD', 'PAGADO', '2026-07-01', '2026-06-01', '2026-07-01', 1) ON CONFLICT DO NOTHING;
INSERT INTO gastos (concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, usuario_id) VALUES ('Hosting AWS', 50.00, 'USD', 'TECNOLOGIA_SAAS', true, true, 1, '2026-07-05', 1) ON CONFLICT DO NOTHING;
INSERT INTO suscripciones (servicio, categoria, monto, moneda, ciclo, proxima_renovacion, status, usuario_id) VALUES ('GitHub Copilot', 'TECNOLOGIA_SAAS', 10.00, 'USD', 'MENSUAL', '2026-08-01', 'ACTIVA', 1) ON CONFLICT DO NOTHING;

