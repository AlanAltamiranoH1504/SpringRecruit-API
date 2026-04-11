import {Step} from '../../home/types/Step';

export const StepsCandidate: Step[] = [
  {
    icon: `<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
             <path d="M4 14.899A7 7 0 1 1 15.71 8h1.79a4.5 4.5 0 0 1 2.5 8.242M12 12v9m-4-4l4 4 4-4"/>
           </svg>`,
    title: "Sube tu CV",
    description: "Regístrate y sube tu currículum para que los reclutadores puedan encontrarte.",
    step: "01"
  },
  {
    icon: `<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
             <circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/>
           </svg>`,
    title: "Explora Vacantes",
    description: "Busca entre miles de ofertas que se ajusten a tus habilidades y expectativas.",
    step: "02"
  },
  {
    icon: `<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
             <path d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"/>
           </svg>`,
    title: "Entrevista",
    description: "Conecta con las empresas y demuestra tu potencial en las entrevistas.",
    step: "03"
  },
  {
    icon: `<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
             <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/><polyline points="22 4 12 14.01 9 11.01"/>
           </svg>`,
    title: "¡Contratado!",
    description: "Recibe ofertas y comienza tu nuevo reto profesional de inmediato.",
    step: "04"
  }
]
