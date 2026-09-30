-- Script de verificación de la base de datos
-- Ejecutar este script para verificar que las tablas y datos existen correctamente

-- Verificar que las tablas existen
SELECT table_name 
FROM information_schema.tables 
WHERE table_schema = 'public' 
  AND table_name IN ('pets', 'applicants', 'adoption_applications')
ORDER BY table_name;

-- Verificar que los tipos ENUM existen
SELECT typname 
FROM pg_type 
WHERE typname IN ('pet_type', 'vaccine_type', 'document_type', 'housing_type', 'application_status')
ORDER BY typname;

-- Contar registros en cada tabla
SELECT 'pets' as tabla, COUNT(*) as cantidad FROM pets
UNION ALL
SELECT 'applicants' as tabla, COUNT(*) as cantidad FROM applicants
UNION ALL
SELECT 'adoption_applications' as tabla, COUNT(*) as cantidad FROM adoption_applications;

-- Ver todas las mascotas
SELECT id, name, age, pet_type, breed, birthdate, vaccines 
FROM pets 
ORDER BY id;

-- Ver todos los solicitantes
SELECT id, document_type, document_number, name, email, phone_number 
FROM applicants 
ORDER BY name;

-- Ver todas las solicitudes de adopción con información relacionada
SELECT 
    aa.id,
    a.name as applicant_name,
    a.document_number,
    p.name as pet_name,
    p.pet_type,
    aa.housing_type,
    aa.has_other_pets,
    aa.occupation,
    aa.status,
    aa.application_date
FROM adoption_applications aa
JOIN applicants a ON aa.applicant_id = a.id
JOIN pets p ON aa.pet_id = p.id
ORDER BY aa.application_date DESC;

-- Verificar índices
SELECT 
    schemaname,
    tablename,
    indexname,
    indexdef
FROM pg_indexes
WHERE schemaname = 'public'
  AND tablename IN ('pets', 'applicants', 'adoption_applications')
ORDER BY tablename, indexname;

-- Verificar las restricciones de clave foránea
SELECT
    tc.constraint_name,
    tc.table_name,
    kcu.column_name,
    ccu.table_name AS foreign_table_name,
    ccu.column_name AS foreign_column_name
FROM information_schema.table_constraints AS tc
JOIN information_schema.key_column_usage AS kcu
    ON tc.constraint_name = kcu.constraint_name
    AND tc.table_schema = kcu.table_schema
JOIN information_schema.constraint_column_usage AS ccu
    ON ccu.constraint_name = tc.constraint_name
    AND ccu.table_schema = tc.table_schema
WHERE tc.constraint_type = 'FOREIGN KEY'
  AND tc.table_schema = 'public'
  AND tc.table_name IN ('adoption_applications')
ORDER BY tc.table_name, tc.constraint_name;
