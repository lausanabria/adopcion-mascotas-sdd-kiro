const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

/**
 * Obtener todas las mascotas o filtrar por tipo
 * @param {string|null} petType - PERRO, GATO, OTRO o null para todas
 * @returns {Promise<Array>}
 */
export async function getPets(petType = null) {
  const url = petType 
    ? `${API_URL}/api/pets?petType=${petType}`
    : `${API_URL}/api/pets`;
  
  const response = await fetch(url, {
    method: 'GET',
    headers: {
      'Accept': 'application/json'
    }
  });
  
  if (!response.ok) {
    throw new Error(`Error al obtener mascotas: ${response.status}`);
  }
  
  return response.json();
}

/**
 * Crear una solicitud de adopción
 * @param {Object} applicationData - Datos de la solicitud
 * @returns {Promise<Object>}
 */
export async function createApplication(applicationData) {
  const response = await fetch(`${API_URL}/api/applications`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    body: JSON.stringify(applicationData)
  });
  
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}));
    throw new Error(errorData.message || `Error al crear solicitud: ${response.status}`);
  }
  
  return response.json();
}
