# Database Specification

Para el desarrollo del backend, ten en cuenta el siguiente script oficial de PostgreSQL (Supabase) como la única fuente de verdad para tablas, relaciones y tipos ENUM:

```sql
-- ==============================================================================
-- BASE DE DATOS: ADOPCIÓN DE MASCOTAS
-- Sistema: PostgreSQL (Supabase)
-- ==============================================================================

DROP TABLE IF EXISTS adoption_applications CASCADE;
DROP TABLE IF EXISTS applicants CASCADE;
DROP TABLE IF EXISTS pets CASCADE;

DROP TYPE IF EXISTS pet_type CASCADE;
DROP TYPE IF EXISTS vaccine_type CASCADE;
DROP TYPE IF EXISTS document_type CASCADE;
DROP TYPE IF EXISTS housing_type CASCADE;
DROP TYPE IF EXISTS application_status CASCADE;

-- Habilitar extensión para UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ==============================================================================
-- 2. TIPOS ENUM
-- ==============================================================================

-- ENUM de tipos de mascota en español
CREATE TYPE pet_type AS ENUM ('PERRO', 'GATO', 'OTRO');

-- ENUM de vacunas en español
CREATE TYPE vaccine_type AS ENUM (
    'RABIA',
    'MOQUILLO_CANINO',
    'PARVOVIRUS_CANINO',
    'TRIPLE_FELINA',
    'LEUCEMIA_FELINA'
);

CREATE TYPE document_type AS ENUM ('CC', 'PASSPORT');
CREATE TYPE housing_type AS ENUM ('APARTMENT', 'HOUSE');
CREATE TYPE application_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED');

-- ==============================================================================
-- 3. CREACIÓN DE TABLAS (Variables / Columnas en inglés)
-- ==============================================================================

-- 3.1 Entidad Pet
CREATE TABLE pets (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT,
    vaccines vaccine_type[],
    pet_type pet_type NOT NULL,
    breed VARCHAR(100),
    birthdate DATE
);

-- 3.2 Entidad Applicant
CREATE TABLE applicants (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    document_type document_type,
    document_number VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone_number VARCHAR(50)
);

-- 3.3 Entidad AdoptionApplication
CREATE TABLE adoption_applications (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    applicant_id UUID NOT NULL,
    pet_id BIGINT NOT NULL,
    housing_type housing_type,
    has_other_pets BOOLEAN,
    occupation VARCHAR(100),
    status application_status DEFAULT 'PENDING' NOT NULL,
    application_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    
    -- Relaciones Foreign Key
    CONSTRAINT fk_applicant FOREIGN KEY (applicant_id) REFERENCES applicants(id) ON DELETE CASCADE,
    CONSTRAINT fk_pet FOREIGN KEY (pet_id) REFERENCES pets(id) ON DELETE CASCADE
);

-- ==============================================================================
-- 4. ÍNDICES DE RENDIMIENTO
-- ==============================================================================

CREATE INDEX idx_pets_pet_type ON pets(pet_type);
CREATE INDEX idx_applications_applicant_id ON adoption_applications(applicant_id);
CREATE INDEX idx_applications_pet_id ON adoption_applications(pet_id);

-- ==============================================================================
-- 5. DATOS DE PRUEBA
-- ==============================================================================

-- Insertar Mascotas (Con tipos de mascota en español)
INSERT INTO pets (name, age, vaccines, pet_type, breed, birthdate) 
VALUES 
('Luna', 2, ARRAY['RABIA', 'MOQUILLO_CANINO']::vaccine_type[], 'PERRO', 'Criolla / Mestiza', '2024-03-15'),
('Mishu', 1, ARRAY['RABIA', 'TRIPLE_FELINA']::vaccine_type[], 'GATO', 'Siamés', '2025-01-10'),
('Max', 3, ARRAY['RABIA', 'MOQUILLO_CANINO', 'PARVOVIRUS_CANINO']::vaccine_type[], 'PERRO', 'Golden Retriever', '2023-06-20'),
('Pelusa', 1, ARRAY['TRIPLE_FELINA', 'LEUCEMIA_FELINA']::vaccine_type[], 'GATO', 'Persa', '2025-02-01'),
('Bruno', 4, ARRAY['RABIA', 'MOQUILLO_CANINO', 'PARVOVIRUS_CANINO']::vaccine_type[], 'PERRO', 'Beagle', '2022-11-12'),
('Coco', 2, ARRAY[]::vaccine_type[], 'OTRO', 'Conejo Holandés', '2024-08-05');

-- Insertar Solicitantes
INSERT INTO applicants (id, document_type, document_number, name, email, phone_number)
VALUES 
('11111111-1111-1111-1111-111111111111', 'CC', '1018123456', 'Laura Restrepo', 'laura.restrepo@example.com', '3001234567'),
('22222222-2222-2222-2222-222222222222', 'CC', '1020987654', 'Carlos Mendoza', 'carlos.mendoza@example.com', '3159876543'),
('33333333-3333-3333-3333-333333333333', 'PASSPORT', 'A12345678', 'Sofía Martínez', 'sofia.martinez@example.com', '3205551234');

-- Insertar Solicitudes de Adopción
INSERT INTO adoption_applications (applicant_id, pet_id, housing_type, has_other_pets, occupation, status)
VALUES 
('11111111-1111-1111-1111-111111111111', 1, 'APARTMENT', true, 'Ingeniera de Software', 'PENDING'),
('22222222-2222-2222-2222-222222222222', 2, 'HOUSE', false, 'Diseñador Gráfico', 'APPROVED');