import React from 'react';

export default function Header({ selectedFilter, onFilterChange }) {
  const filters = [
    { id: 'TODOS', label: 'Todos', emoji: '🐾' },
    { id: 'PERRO', label: 'Perros', emoji: '🐶' },
    { id: 'GATO', label: 'Gatos', emoji: '🐱' }
  ];

  return (
    <header className="bg-[#F1ECFF] rounded-3xl p-6 sm:p-8 relative overflow-hidden mb-6 sm:mb-8">
      {/* Huellas decorativas */}
      <div className="absolute right-8 top-1/2 -translate-y-1/2 text-6xl sm:text-8xl opacity-10 rotate-[-15deg] select-none">
        🐾
      </div>
      <div className="hidden sm:block absolute right-24 top-1/2 -translate-y-1/2 text-6xl sm:text-8xl opacity-10 rotate-[25deg] select-none">
        🐾
      </div>

      <div className="relative z-10">
        {/* Logo y título */}
        <div className="flex items-center gap-3 sm:gap-4 mb-4 sm:mb-6">
          <div className="w-12 h-12 sm:w-16 sm:h-16 bg-[#7048E8] rounded-full flex items-center justify-center text-2xl sm:text-3xl">
            🐾
          </div>
          <div>
            <h1 className="font-fredoka text-3xl sm:text-5xl font-bold text-[#2A2140]">
              Peluditos
            </h1>
            <p className="text-[#7A7291] text-sm sm:text-base">
              Encuentra a tu compañero ideal
            </p>
          </div>
        </div>

        <h2 className="font-fredoka text-3xl sm:text-5xl font-bold leading-tight max-w-2xl text-[#2A2140]">
          ¡Hola! Alguien te está esperando 🐶🐱
        </h2>
        <p className="mt-2 mb-5 text-base max-w-xl text-[#7A7291]">
          Perros y gatos rescatados que buscan un hogar lleno de cariño.
        </p>

        {/* Filtros */}
        <div className="flex flex-wrap gap-2 sm:gap-3">
          {filters.map((filter) => (
            <button
              key={filter.id}
              onClick={() => onFilterChange(filter.id)}
              className={`
                px-4 sm:px-6 py-2 sm:py-2.5 rounded-full font-medium text-sm sm:text-base
                transition-all duration-200 flex items-center gap-2 min-h-[44px]
                ${selectedFilter === filter.id
                  ? 'bg-[#7048E8] text-white shadow-lg'
                  : 'bg-white text-[#2A2140] hover:bg-[#F1ECFF]'
                }
              `}
            >
              <span>{filter.emoji}</span>
              <span>{filter.label}</span>
            </button>
          ))}
        </div>
      </div>
    </header>
  );
}
