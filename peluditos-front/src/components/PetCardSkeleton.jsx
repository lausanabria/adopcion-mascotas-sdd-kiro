import React from 'react';

export default function PetCardSkeleton() {
  return (
    <div className="rounded-3xl p-2 bg-[#F5F3FF] animate-pulse">
      {/* Emoji placeholder */}
      <div className="rounded-2xl bg-white bg-opacity-50 aspect-square flex items-center justify-center mb-2">
        <div className="w-16 h-16 bg-[#ECE8F7] rounded-full"></div>
      </div>

      {/* Información placeholder */}
      <div className="px-2 pb-2">
        <div className="h-4 bg-[#ECE8F7] rounded-full mb-2 w-3/4"></div>
        <div className="h-3 bg-[#ECE8F7] rounded-full mb-1 w-full"></div>
        <div className="h-3 bg-[#ECE8F7] rounded-full mb-3 w-1/2"></div>
        <div className="h-6 bg-[#ECE8F7] rounded-full mb-3 w-20"></div>
        <div className="h-8 bg-[#ECE8F7] rounded-full w-full"></div>
      </div>
    </div>
  );
}
