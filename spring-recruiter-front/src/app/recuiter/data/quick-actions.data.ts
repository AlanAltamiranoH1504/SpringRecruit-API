import {QuickAction} from '../types/QuickActions';

export const QuickActionsData: QuickAction[] = [
  {
    id: 1,
    title: "Publicar Nuevo Trabajo",
    description: "Crea y publica una nueva oferta de trabajo",
    icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-plus\">\n" +
      "  <path d=\"M5 12h14\"/>\n" +
      "  <path d=\"M12 5v14\"/>\n" +
      "</svg>\n",
    color: "text-blue-600",
    bgColor: "bg-blue-50",
  },
  // {
  //   title: "Importar Candidatos",
  //   description: "Sube un archivo CSV con candidatos",
  //   icon: "",
  //   color: "text-green-600",
  //   bgColor: "bg-green-50",
  // },
  // {
  //   title: "Programar Entrevista",
  //   description: "Agenda una entrevista con un candidato",
  //   icon: Calendar,
  //   color: "text-purple-600",
  //   bgColor: "bg-purple-50",
  // },
  {
    id: 2,
    title: "Enviar Mensaje",
    description: "Contacta con tus candidatos",
    icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-message-square\">\n" +
      "  <path d=\"M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z\"/>\n" +
      "</svg>\n",
    color: "text-orange-600",
    bgColor: "bg-orange-50",
  },
];
