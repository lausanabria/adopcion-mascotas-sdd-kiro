import React, { useState, useEffect } from 'react';
import Header from './components/Header';
import PetCard from './components/PetCard';
import PetCardSkeleton from './components/PetCardSkeleton';
import AdoptionModal from './components/AdoptionModal';
import { getPets, createApplication } from './services/api';

function App() {
  const [pets, setPets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedFilter, setSelectedFilter] = useState('TODOS');
  const [selectedPet, setSelectedPet] = useState(null);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);

  // Cargar mascotas al montar o cambiar filtro
  useEffect(() => {
    loadPets();
  }, [selectedFilter]);

  const loadPets = async () => {
    setLoading(true);
    setError(null);
    try {
      const petType = selectedFilter === 'TODOS' ? null : selectedFilter;
      const data = await getPets(petType);
      setPets(data);
    } catch (err) {
      setError('No se pudieron cargar las mascotas. Verifica que el servidor esté ejecutándose.');
      console.error('Error al cargar mascotas:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleFilterChange = (filter) => {
    setSelectedFilter(filter);
  };

  const handleAdoptClick = (pet) => {
    setSelectedPet(pet);
  };

  const handleCloseModal = () => {
    setSelectedPet(null);
  };

  const handleSubmitApplication = async (applicationData) => {
    try {
      await createApplication(applicationData);
      setSelectedPet(null);
      setShowSuccessMessage(true);
      
      // Ocultar mensaje de éxito después de 5 segundos
      setTimeout(() => {
        setShowSuccessMessage(false);
      }, 5000);
    } catch (err) {
      throw err; // Re-lanzar para que el modal lo maneje
    }
  };

  return (
    <div className="min-h-screen bg-[#FBFAFF] px-4 py-6 sm:py-8">
      <div className="max-w-7xl mx-auto">
        <Header 
          selectedFilter={selectedFilter}
          onFilterChange={handleFilterChange}
        />

        {/* Mensaje de éxito */}
        {showSuccessMessage && (
          <div className="mb-6 p-4 rounded-2xl bg-[#DDF6E6] border-2 border-[#1E8A4F]">
            <p className="text-[#1E8A4F] text-center font-medium">
              ¡Solicitud enviada con éxito! Pronto nos pondremos en contacto contigo.
            </p>
          </div>
        )}

        {/* Error de carga */}
        {error && (
          <div className="mb-6 p-4 rounded-2xl bg-red-50 border-2 border-[#D92D20]">
            <p className="text-[#D92D20] text-center">{error}</p>
            <button
              onClick={loadPets}
              className="mt-3 mx-auto block px-4 py-2 rounded-full bg-[#7048E8] text-white
                         hover:bg-[#5a3ab8] transition-colors"
            >
              Reintentar
            </button>
          </div>
        )}

        {/* Cuadrícula de mascotas */}
        {loading ? (
          <div className="grid grid-cols-2 lg:grid-cols-4 gap-3">
            {[...Array(8)].map((_, index) => (
              <PetCardSkeleton key={index} />
            ))}
          </div>
        ) : pets.length === 0 ? (
          <div className="text-center py-12">
            <p className="text-[#7A7291] text-lg">
              No hay mascotas disponibles en esta categoría
            </p>
          </div>
        ) : (
          <div className="grid grid-cols-2 sm:grid-cols-4 lg:grid-cols-4 gap-3">
            {pets.map((pet) => (
              <PetCard
                key={pet.id}
                pet={pet}
                onAdoptClick={handleAdoptClick}
              />
            ))}
          </div>
        )}

        {/* Modal de adopción */}
        {selectedPet && (
          <AdoptionModal
            pet={selectedPet}
            onClose={handleCloseModal}
            onSubmit={handleSubmitApplication}
          />
        )}
      </div>
    </div>
  );
}

export default App;
