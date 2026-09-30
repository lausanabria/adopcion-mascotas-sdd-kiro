import React, { useState } from 'react';
import { X } from 'lucide-react';

export default function AdoptionModal({ pet, onClose, onSubmit }) {
  const [formData, setFormData] = useState({
    documentType: 'CC',
    documentNumber: '',
    name: '',
    email: '',
    phoneNumber: '',
    housingType: 'HOUSE',
    hasOtherPets: false,
    occupation: ''
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
    // Limpiar error del campo al cambiar
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: null }));
    }
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.documentNumber.trim()) {
      newErrors.documentNumber = 'El número de documento es obligatorio';
    }

    if (!formData.name.trim()) {
      newErrors.name = 'El nombre es obligatorio';
    }

    if (!formData.email.trim()) {
      newErrors.email = 'El correo electrónico es obligatorio';
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = 'El correo electrónico no es válido';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setIsSubmitting(true);

    try {
      const applicationData = {
        applicant: {
          documentType: formData.documentType,
          documentNumber: formData.documentNumber,
          name: formData.name,
          email: formData.email,
          ...(formData.phoneNumber && { phoneNumber: formData.phoneNumber })
        },
        petId: pet.id,
        housingType: formData.housingType,
        hasOtherPets: formData.hasOtherPets,
        ...(formData.occupation && { occupation: formData.occupation })
      };

      await onSubmit(applicationData);
    } catch (error) {
      setErrors({ submit: error.message || 'Error al enviar la solicitud' });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div
      className="fixed inset-0 bg-[rgba(42,33,64,0.45)] flex items-center justify-center z-50 p-4"
      onClick={onClose}
    >
      <div
        className="bg-white rounded-3xl max-w-md w-full max-h-[90vh] overflow-y-auto"
        onClick={(e) => e.stopPropagation()}
      >
        {/* Header del modal */}
        <div className="sticky top-0 bg-white rounded-t-3xl p-6 border-b border-[#ECE8F7]">
          <div className="flex items-center justify-between">
            <h2 className="font-fredoka text-2xl font-bold text-[#2A2140]">
              Solicitud de adopción
            </h2>
            <button
              onClick={onClose}
              className="w-10 h-10 rounded-full bg-[#F1ECFF] flex items-center justify-center
                         hover:bg-[#E5DBFF] transition-colors"
              aria-label="Cerrar"
            >
              <X className="w-5 h-5 text-[#7048E8]" />
            </button>
          </div>
          <p className="text-[#7A7291] mt-2">
            Completa el formulario para adoptar a <span className="font-fredoka font-semibold text-[#2A2140]">{pet.name}</span>
          </p>
        </div>

        {/* Formulario */}
        <form onSubmit={handleSubmit} className="p-6 space-y-4">
          {/* Tipo de documento */}
          <div>
            <label htmlFor="documentType" className="block text-sm font-medium text-[#2A2140] mb-2">
              Tipo de documento
            </label>
            <select
              id="documentType"
              name="documentType"
              value={formData.documentType}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-2xl border-2 border-[#ECE8F7] bg-[#FBFAFF]
                         focus:outline-none focus:border-[#7048E8] text-base"
            >
              <option value="CC">Cédula de Ciudadanía</option>
              <option value="PASSPORT">Pasaporte</option>
            </select>
          </div>

          {/* Número de documento */}
          <div>
            <label htmlFor="documentNumber" className="block text-sm font-medium text-[#2A2140] mb-2">
              Número de documento *
            </label>
            <input
              type="text"
              id="documentNumber"
              name="documentNumber"
              value={formData.documentNumber}
              onChange={handleChange}
              className={`w-full px-4 py-3 rounded-2xl border-2 bg-[#FBFAFF]
                         focus:outline-none text-base
                         ${errors.documentNumber ? 'border-[#D92D20]' : 'border-[#ECE8F7] focus:border-[#7048E8]'}`}
              placeholder="Ingresa tu número de documento"
            />
            {errors.documentNumber && (
              <p className="text-[#D92D20] text-sm mt-1">{errors.documentNumber}</p>
            )}
          </div>

          {/* Nombre completo */}
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-[#2A2140] mb-2">
              Nombre completo *
            </label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className={`w-full px-4 py-3 rounded-2xl border-2 bg-[#FBFAFF]
                         focus:outline-none text-base
                         ${errors.name ? 'border-[#D92D20]' : 'border-[#ECE8F7] focus:border-[#7048E8]'}`}
              placeholder="Ingresa tu nombre completo"
            />
            {errors.name && (
              <p className="text-[#D92D20] text-sm mt-1">{errors.name}</p>
            )}
          </div>

          {/* Correo electrónico */}
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-[#2A2140] mb-2">
              Correo electrónico *
            </label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className={`w-full px-4 py-3 rounded-2xl border-2 bg-[#FBFAFF]
                         focus:outline-none text-base
                         ${errors.email ? 'border-[#D92D20]' : 'border-[#ECE8F7] focus:border-[#7048E8]'}`}
              placeholder="tu@correo.com"
            />
            {errors.email && (
              <p className="text-[#D92D20] text-sm mt-1">{errors.email}</p>
            )}
          </div>

          {/* Teléfono */}
          <div>
            <label htmlFor="phoneNumber" className="block text-sm font-medium text-[#2A2140] mb-2">
              Teléfono
            </label>
            <input
              type="tel"
              id="phoneNumber"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-2xl border-2 border-[#ECE8F7] bg-[#FBFAFF]
                         focus:outline-none focus:border-[#7048E8] text-base"
              placeholder="3001234567"
            />
          </div>

          {/* Tipo de vivienda */}
          <div>
            <label htmlFor="housingType" className="block text-sm font-medium text-[#2A2140] mb-2">
              Tipo de vivienda
            </label>
            <select
              id="housingType"
              name="housingType"
              value={formData.housingType}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-2xl border-2 border-[#ECE8F7] bg-[#FBFAFF]
                         focus:outline-none focus:border-[#7048E8] text-base"
            >
              <option value="HOUSE">Casa</option>
              <option value="APARTMENT">Apartamento</option>
            </select>
          </div>

          {/* Tiene otras mascotas */}
          <div>
            <label className="flex items-center gap-3 cursor-pointer">
              <input
                type="checkbox"
                name="hasOtherPets"
                checked={formData.hasOtherPets}
                onChange={handleChange}
                className="w-5 h-5 rounded border-2 border-[#ECE8F7] text-[#7048E8]
                           focus:ring-2 focus:ring-[#7048E8] focus:ring-offset-0"
              />
              <span className="text-sm text-[#2A2140]">
                Tengo otras mascotas en casa
              </span>
            </label>
          </div>

          {/* Ocupación */}
          <div>
            <label htmlFor="occupation" className="block text-sm font-medium text-[#2A2140] mb-2">
              Ocupación
            </label>
            <input
              type="text"
              id="occupation"
              name="occupation"
              value={formData.occupation}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-2xl border-2 border-[#ECE8F7] bg-[#FBFAFF]
                         focus:outline-none focus:border-[#7048E8] text-base"
              placeholder="¿A qué te dedicas?"
            />
          </div>

          {/* Error de envío */}
          {errors.submit && (
            <div className="p-4 rounded-2xl bg-red-50 border-2 border-[#D92D20]">
              <p className="text-[#D92D20] text-sm">{errors.submit}</p>
            </div>
          )}

          {/* Botones */}
          <div className="flex gap-3 pt-2">
            <button
              type="button"
              onClick={onClose}
              className="flex-1 px-6 py-3 rounded-full border-2 border-[#ECE8F7] text-[#2A2140]
                         font-medium hover:bg-[#F1ECFF] transition-colors"
            >
              Cancelar
            </button>
            <button
              type="submit"
              disabled={isSubmitting}
              className="flex-1 px-6 py-3 rounded-full bg-[#7048E8] text-white font-medium
                         hover:bg-[#5a3ab8] transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {isSubmitting ? 'Enviando...' : 'Enviar solicitud'}
            </button>
          </div>

          <p className="text-xs text-[#7A7291] text-center">
            * Campos obligatorios
          </p>
        </form>
      </div>
    </div>
  );
}
