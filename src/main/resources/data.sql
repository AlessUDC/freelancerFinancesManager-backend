-- =============================================================================
--  SEED DATA  ·  Ejecutado automáticamente por Spring Boot en cada arranque
--  Todas las sentencias son idempotentes (ON CONFLICT / WHERE NOT EXISTS)
--  Contraseña de los 3 usuarios de prueba: "test1234"
-- =============================================================================

-- ── Categorías ────────────────────────────────────────────────────────────────
INSERT INTO categorias (nombre) VALUES
    ('TECNOLOGIA_SAAS'),
    ('SERVICIOS_PUBLICOS_CONECTIVIDAD'),
    ('COWORKING'),
    ('EDUCACION_CAPACITACION'),
    ('IMPUESTOS_LEGAL'),
    ('PERSONAL'),
    ('MARKETING_PUBLICIDAD'),
    ('EQUIPAMIENTO_HARDWARE'),
    ('SALUD_BIENESTAR'),
    ('TRANSPORTE')
ON CONFLICT (nombre) DO NOTHING;

-- ── Usuarios ──────────────────────────────────────────────────────────────────

INSERT INTO usuarios (
    nombres, apellido_paterno, apellido_materno,
    telefono, fecha_nacimiento, cuenta_bancaria,
    email, password, rol,
    moneda_base, zona_horaria,
    razon_social, ruc_nif, direccion_fiscal,
    porcentaje_impuesto, meta_ingreso_mensual, limite_gastos, porcentaje_retencion,
    alertas_renovaciones, alertas_inyectar_suscripciones,
    created_at
) VALUES
    -- Usuario 1: Ana García (diseñadora UX/UI freelance)
    ('Ana Sofía', 'García', 'Rodríguez',
     '+51 987 654 321', '1993-04-15', 'BCP-0011-0234-0100456789',
     'ana@freelancer.test', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER',
     'USD', 'America/Lima',
     'Ana García Design E.I.R.L.', '20601234567', 'Av. Larco 1234, Miraflores, Lima',
     8.00, 3000.00, 1500.00, 5.00,
     true, true,
     NOW() - INTERVAL '6 months'),

    -- Usuario 2: Carlos Mendoza (desarrollador backend freelance)
    ('Carlos Eduardo', 'Mendoza', 'Vega',
     '+51 912 345 678', '1990-11-22', 'BBVA-0011-0564-0200987654',
     'carlos@freelancer.test', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER',
     'USD', 'America/Lima',
     'CMV Tech Solutions S.A.C.', '20709876543', 'Jr. Schell 375, Miraflores, Lima',
     8.00, 5000.00, 2000.00, 8.00,
     true, true,
     NOW() - INTERVAL '1 year'),

    -- Usuario 3: Lucía Torres (copywriter & marketing digital freelance)
    ('Lucía Fernanda', 'Torres', 'Jiménez',
     '+51 956 789 012', '1995-07-08', 'INTERBANK-0044-3123-0300345678',
     'lucia@freelancer.test', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'USER',
     'PEN', 'America/Lima',
     'Torres Jiménez Comunicaciones', '20812345678', 'Calle Las Flores 456, San Isidro, Lima',
     8.00, 8000.00, 3000.00, 5.00,
     true, false,
     NOW() - INTERVAL '3 months')
ON CONFLICT (email) DO NOTHING;

-- =============================================================================
--  INGRESOS  (5 por usuario · sólo inserta si el usuario aún no tiene ninguno)
-- =============================================================================

-- ── Ingresos de Ana García ────────────────────────────────────────────────────
INSERT INTO ingresos (proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento, usuario_id)
SELECT v.proyecto_nombre, v.monto_bruto, v.retencion, v.monto_neto, v.moneda, v.status, v.fecha::date, v.fecha_emision::date, v.fecha_vencimiento::date, u.id
FROM (VALUES
    ('Rediseño App Móvil – FinTech',      2500.00::numeric,  125.00::numeric, 2375.00::numeric, 'USD', 'PAGADO',    '2026-01-15', '2026-01-01', '2026-01-30'),
    ('Identidad Visual – Startup Eco',    1800.00::numeric,   90.00::numeric, 1710.00::numeric, 'USD', 'PAGADO',    '2026-02-20', '2026-02-05', '2026-02-28'),
    ('UX Audit – E-commerce Retail',      1200.00::numeric,   60.00::numeric, 1140.00::numeric, 'USD', 'PAGADO',    '2026-03-10', '2026-03-01', '2026-03-15'),
    ('Prototipo Dashboard Analytics',     3200.00::numeric,  160.00::numeric, 3040.00::numeric, 'USD', 'PENDIENTE', '2026-04-01', '2026-03-20', '2026-04-15'),
    ('Consultoría UX – App Educativa',     900.00::numeric,   45.00::numeric,  855.00::numeric, 'USD', 'PENDIENTE', '2026-05-01', '2026-04-25', '2026-05-10')
) AS v(proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento)
JOIN usuarios u ON u.email = 'ana@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM ingresos WHERE usuario_id = u.id);

-- ── Ingresos de Carlos Mendoza ────────────────────────────────────────────────
INSERT INTO ingresos (proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento, usuario_id)
SELECT v.proyecto_nombre, v.monto_bruto, v.retencion, v.monto_neto, v.moneda, v.status, v.fecha::date, v.fecha_emision::date, v.fecha_vencimiento::date, u.id
FROM (VALUES
    ('API REST – Plataforma Logística',   4500.00::numeric,  360.00::numeric, 4140.00::numeric, 'USD', 'PAGADO',    '2026-01-10', '2025-12-20', '2026-01-20'),
    ('Microservicios – HealthTech SaaS',  6000.00::numeric,  480.00::numeric, 5520.00::numeric, 'USD', 'PAGADO',    '2026-02-15', '2026-01-30', '2026-02-28'),
    ('Integración Pasarela de Pagos',     2800.00::numeric,  224.00::numeric, 2576.00::numeric, 'USD', 'PAGADO',    '2026-03-05', '2026-02-20', '2026-03-10'),
    ('Backend CRM – Inmobiliaria',        3500.00::numeric,  280.00::numeric, 3220.00::numeric, 'USD', 'PENDIENTE', '2026-04-12', '2026-04-01', '2026-04-30'),
    ('Auditoría de Seguridad – API',      1500.00::numeric,  120.00::numeric, 1380.00::numeric, 'USD', 'PENDIENTE', '2026-05-08', '2026-04-28', '2026-05-15')
) AS v(proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento)
JOIN usuarios u ON u.email = 'carlos@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM ingresos WHERE usuario_id = u.id);

-- ── Ingresos de Lucía Torres ──────────────────────────────────────────────────
INSERT INTO ingresos (proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento, usuario_id)
SELECT v.proyecto_nombre, v.monto_bruto, v.retencion, v.monto_neto, v.moneda, v.status, v.fecha::date, v.fecha_emision::date, v.fecha_vencimiento::date, u.id
FROM (VALUES
    ('Estrategia de Contenidos – ONG',    3500.00::numeric,  175.00::numeric, 3325.00::numeric, 'PEN', 'PAGADO',    '2026-01-20', '2026-01-05', '2026-01-25'),
    ('Copywriting Web – Clínica Dental',  1200.00::numeric,   60.00::numeric, 1140.00::numeric, 'PEN', 'PAGADO',    '2026-02-14', '2026-02-01', '2026-02-20'),
    ('Campaña Email Marketing – Retail',  2100.00::numeric,  105.00::numeric, 1995.00::numeric, 'PEN', 'PAGADO',    '2026-03-18', '2026-03-05', '2026-03-25'),
    ('Guión Corporativo – Video 3 min',    800.00::numeric,   40.00::numeric,  760.00::numeric, 'PEN', 'PENDIENTE', '2026-04-30', '2026-04-20', '2026-05-05'),
    ('Plan Editorial – Marca Deportiva',  4500.00::numeric,  225.00::numeric, 4275.00::numeric, 'PEN', 'PENDIENTE', '2026-05-15', '2026-05-01', '2026-05-30')
) AS v(proyecto_nombre, monto_bruto, retencion, monto_neto, moneda, status, fecha, fecha_emision, fecha_vencimiento)
JOIN usuarios u ON u.email = 'lucia@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM ingresos WHERE usuario_id = u.id);

-- =============================================================================
--  GASTOS  (5 por usuario · sólo inserta si el usuario aún no tiene ninguno)
-- =============================================================================

-- ── Gastos de Ana García ──────────────────────────────────────────────────────
INSERT INTO gastos (concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv, usuario_id)
SELECT v.concepto, v.monto, v.moneda, v.categoria, v.es_deducible, v.es_recurrente, v.cantidad, v.fecha::date, v.igv, u.id
FROM (VALUES
    ('Figma Professional (anual)',          180.00::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-01', 32.40::numeric),
    ('Adobe Creative Cloud',                 60.00::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-05',  0.00::numeric),
    ('Curso UX Research – IDF',              99.00::numeric, 'USD', 'EDUCACION_CAPACITACION',      true,  false, 1, '2026-02-10', 17.82::numeric),
    ('Coworking Regus – Enero',             250.00::numeric, 'USD', 'COWORKING',                   true,  true,  1, '2026-01-31', 45.00::numeric),
    ('Impresión portafolio físico',          45.00::numeric, 'USD', 'MARKETING_PUBLICIDAD',        false, false, 1, '2026-03-05',  8.10::numeric)
) AS v(concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv)
JOIN usuarios u ON u.email = 'ana@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM gastos WHERE usuario_id = u.id);

-- ── Gastos de Carlos Mendoza ──────────────────────────────────────────────────
INSERT INTO gastos (concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv, usuario_id)
SELECT v.concepto, v.monto, v.moneda, v.categoria, v.es_deducible, v.es_recurrente, v.cantidad, v.fecha::date, v.igv, u.id
FROM (VALUES
    ('AWS EC2 + RDS – Enero',              120.00::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-31', 21.60::numeric),
    ('GitHub Copilot Business',             19.00::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-05',  3.42::numeric),
    ('JetBrains All Products Pack',        249.00::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-10', 44.82::numeric),
    ('Udemy – Curso Kubernetes Avanzado',   15.99::numeric, 'USD', 'EDUCACION_CAPACITACION',      true,  false, 1, '2026-02-18',  2.88::numeric),
    ('Monitor LG 27" 4K',                 380.00::numeric, 'USD', 'EQUIPAMIENTO_HARDWARE',        true,  false, 1, '2026-03-22', 68.40::numeric)
) AS v(concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv)
JOIN usuarios u ON u.email = 'carlos@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM gastos WHERE usuario_id = u.id);

-- ── Gastos de Lucía Torres ────────────────────────────────────────────────────
INSERT INTO gastos (concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv, usuario_id)
SELECT v.concepto, v.monto, v.moneda, v.categoria, v.es_deducible, v.es_recurrente, v.cantidad, v.fecha::date, v.igv, u.id
FROM (VALUES
    ('Canva Pro (anual)',                  119.00::numeric, 'PEN', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-03', 21.42::numeric),
    ('Grammarly Premium',                   29.95::numeric, 'USD', 'TECNOLOGIA_SAAS',             true,  true,  1, '2026-01-08',  0.00::numeric),
    ('Taller Copywriting – Hotmart',        75.00::numeric, 'USD', 'EDUCACION_CAPACITACION',      true,  false, 1, '2026-02-25', 13.50::numeric),
    ('Internet Fibra Óptica – Enero',      110.00::numeric, 'PEN', 'SERVICIOS_PUBLICOS_CONECTIVIDAD', true, true, 1, '2026-01-31', 19.80::numeric),
    ('Smartphone Samsung para trabajo',    950.00::numeric, 'PEN', 'EQUIPAMIENTO_HARDWARE',        true,  false, 1, '2026-03-15',171.00::numeric)
) AS v(concepto, monto, moneda, categoria, es_deducible, es_recurrente, cantidad, fecha, igv)
JOIN usuarios u ON u.email = 'lucia@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM gastos WHERE usuario_id = u.id);

-- =============================================================================
--  SUSCRIPCIONES  (5 por usuario · sólo inserta si el usuario aún no tiene ninguna)
-- =============================================================================

-- ── Suscripciones de Ana García ───────────────────────────────────────────────
INSERT INTO suscripciones (servicio, monto, moneda, ciclo, proxima_renovacion, status, igv, usuario_id)
SELECT v.servicio, v.monto, v.moneda, v.ciclo, v.proxima_renovacion::date, v.status, v.igv, u.id
FROM (VALUES
    ('Figma Professional',       15.00::numeric, 'USD', 'MENSUAL', '2026-08-01', 'ACTIVA',   2.70::numeric),
    ('Adobe CC (All Apps)',      60.00::numeric, 'USD', 'MENSUAL', '2026-08-05', 'ACTIVA',  10.80::numeric),
    ('Notion Team',              16.00::numeric, 'USD', 'MENSUAL', '2026-08-12', 'ACTIVA',   2.88::numeric),
    ('Behance Pro',               9.99::numeric, 'USD', 'MENSUAL', '2026-08-15', 'ACTIVA',   1.80::numeric),
    ('Google Workspace Business',12.00::numeric, 'USD', 'MENSUAL', '2026-08-01', 'ACTIVA',   2.16::numeric)
) AS v(servicio, monto, moneda, ciclo, proxima_renovacion, status, igv)
JOIN usuarios u ON u.email = 'ana@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM suscripciones WHERE usuario_id = u.id);

-- ── Suscripciones de Carlos Mendoza ──────────────────────────────────────────
INSERT INTO suscripciones (servicio, monto, moneda, ciclo, proxima_renovacion, status, igv, usuario_id)
SELECT v.servicio, v.monto, v.moneda, v.ciclo, v.proxima_renovacion::date, v.status, v.igv, u.id
FROM (VALUES
    ('GitHub Copilot Business',  19.00::numeric, 'USD', 'MENSUAL', '2026-08-05', 'ACTIVA',   3.42::numeric),
    ('AWS (estimado mensual)',   120.00::numeric, 'USD', 'MENSUAL', '2026-08-01', 'ACTIVA',  21.60::numeric),
    ('JetBrains All Products',   24.90::numeric, 'USD', 'MENSUAL', '2026-08-10', 'ACTIVA',   4.48::numeric),
    ('Linear (proyectos)',         8.00::numeric, 'USD', 'MENSUAL', '2026-08-20', 'ACTIVA',   1.44::numeric),
    ('DataDog Monitoring',        25.00::numeric, 'USD', 'MENSUAL', '2026-08-01', 'PAUSADA',  4.50::numeric)
) AS v(servicio, monto, moneda, ciclo, proxima_renovacion, status, igv)
JOIN usuarios u ON u.email = 'carlos@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM suscripciones WHERE usuario_id = u.id);

-- ── Suscripciones de Lucía Torres ─────────────────────────────────────────────
INSERT INTO suscripciones (servicio, monto, moneda, ciclo, proxima_renovacion, status, igv, usuario_id)
SELECT v.servicio, v.monto, v.moneda, v.ciclo, v.proxima_renovacion::date, v.status, v.igv, u.id
FROM (VALUES
    ('Canva Pro',                 9.99::numeric, 'USD', 'MENSUAL', '2026-08-03', 'ACTIVA',   1.80::numeric),
    ('Grammarly Premium',        29.95::numeric, 'USD', 'MENSUAL', '2026-08-08', 'ACTIVA',   5.39::numeric),
    ('SEMrush Pro',             119.95::numeric, 'USD', 'MENSUAL', '2026-08-01', 'ACTIVA',  21.59::numeric),
    ('Mailchimp Essentials',     13.00::numeric, 'USD', 'MENSUAL', '2026-08-15', 'ACTIVA',   2.34::numeric),
    ('HubSpot CRM Starter',      45.00::numeric, 'USD', 'MENSUAL', '2026-09-01', 'PAUSADA',  8.10::numeric)
) AS v(servicio, monto, moneda, ciclo, proxima_renovacion, status, igv)
JOIN usuarios u ON u.email = 'lucia@freelancer.test'
WHERE NOT EXISTS (SELECT 1 FROM suscripciones WHERE usuario_id = u.id);
