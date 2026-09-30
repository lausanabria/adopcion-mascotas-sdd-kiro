import React from 'react';
import { COLORS, PET_EMOJIS } from '../constants/theme';

export default function PetCard({ pet, onAdoptClick }) {
  // Seleccionar color pastel y emoji basado en el ID
  const pastelColor = COLORS.pastelBackgrounds[pet.id % COLORS.pastelBackgrounds.length];
  const emojis = PET_EMOJIS[pet.petType] || ['🐾'];
  const emoji = emojis[pet.id % emojis.length];

  return (
    <div
      className="rounded-3xl p-2 transition-transform hover:scale-105"
      style={{ backgroundColor: pastelColor }}
    >
      {/* Emoji de la mascota */}
      <div className="rounded-2xl bg-white bg-opacity-50 aspect-square flex items-center justify-center mb-2">
        <span className="text-5xl sm:text-6xl">{emoji}</span>
      </div>

      {/* Información de la mascota */}
      <div className="px-2 pb-2">
        <h3 className="font-fredoka text-base font-semibold text-[#2A2140] truncate mb-0.5">
          {pet.name}
        </h3>
        <p className="text-xs text-[#7A7291] truncate mb-1">
          {pet.breed}
        </p>
        <p className="text-xs text-[#7A7291] mb-3">
          {pet.age} {pet.age === 1 ? 'año' : 'años'}
        </p>

        {/* Badge disponible */}
        <div className="inline-flex items-center px-2 py-1 rounded-full bg-[#DDF6E6] mb-3">
          <span className="text-xs font-medium text-[#1E8A4F]">
            Disponible
          </span>
        </div>

        {/* Botón de adopción */}
        <button
          onClick={() => onAdoptClick(pet)}
          className="w-full bg-[#7048E8] text-white rounded-full py-2 text-sm font-medium
                     hover:bg-[#5a3ab8] transition-all duration-200 hover:scale-105"
        >
          Adoptar
        </button>
      </div>
    </div>
  );
}
