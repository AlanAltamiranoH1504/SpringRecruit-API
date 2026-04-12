import {Navigate} from '../types/Navigate';

export const navigateData: Navigate[] = [
  {
    name: "Dashboard", icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-layout-dashboard\">\n" +
      "  <rect width=\"7\" height=\"9\" x=\"3\" y=\"3\" rx=\"1\"/>\n" +
      "  <rect width=\"7\" height=\"5\" x=\"14\" y=\"3\" rx=\"1\"/>\n" +
      "  <rect width=\"7\" height=\"9\" x=\"14\" y=\"12\" rx=\"1\"/>\n" +
      "  <rect width=\"7\" height=\"5\" x=\"3\" y=\"16\" rx=\"1\"/>\n" +
      "</svg>\n",
    route: "/dashboard/recruiter"
  },
  {
    name: "Trabajos", icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-briefcase\">\n" +
      "  <rect width=\"20\" height=\"14\" x=\"2\" y=\"7\" rx=\"2\" ry=\"2\"/>\n" +
      "  <path d=\"M16 21V5a2 2 0 0 0-2-2h-4a2 2 0 0 0-2 2v16\"/>\n" +
      "</svg>\n",
    route: "/dashboard/recruiter/work"
  },
  {
    name: "Candidatos", icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-users\">\n" +
      "  <path d=\"M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2\"/>\n" +
      "  <circle cx=\"9\" cy=\"7\" r=\"4\"/>\n" +
      "  <path d=\"M22 21v-2a4 4 0 0 0-3-3.87\"/>\n" +
      "  <path d=\"M16 3.13a4 4 0 0 1 0 7.75\"/>\n" +
      "</svg>\n",
    route: "/dashboard/recruiter/candidates"
  },
  {
    name: "Aplicaciones", icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-file-text\">\n" +
      "  <path d=\"M15 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V7Z\"/>\n" +
      "  <path d=\"M14 2v4a2 2 0 0 0 2 2h4\"/>\n" +
      "  <path d=\"M10 9H8\"/>\n" +
      "  <path d=\"M16 13H8\"/>\n" +
      "  <path d=\"M16 17H8\"/>\n" +
      "</svg>\n",
    route: "/dashboard/recruiter/applications"
  },
  {
    name: "Reportes", icon: "<svg \n" +
      "  xmlns=\"http://www.w3.org/2000/svg\" \n" +
      "  width=\"24\" \n" +
      "  height=\"24\" \n" +
      "  viewBox=\"0 0 24 24\" \n" +
      "  fill=\"none\" \n" +
      "  stroke=\"currentColor\" \n" +
      "  stroke-width=\"2\" \n" +
      "  stroke-linecap=\"round\" \n" +
      "  stroke-linejoin=\"round\" \n" +
      "  class=\"lucide lucide-bar-chart-3\">\n" +
      "  <path d=\"M3 3v18h18\"/>\n" +
      "  <path d=\"M18 17V9\"/>\n" +
      "  <path d=\"M13 17V5\"/>\n" +
      "  <path d=\"M8 17v-3\"/>\n" +
      "</svg>\n", route: "/dashboard/recruiter/reports"
  },
  {
    name: "Ayuda",
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
      "  class=\"lucide lucide-settings\">\n" +
      "  <path d=\"M12.22 2h-.44a2 2 0 0 0-2 2v.18a2 2 0 0 1-1 1.73l-.43.25a2 2 0 0 1-2 0l-.15-.08a2 2 0 0 0-2.73.73l-.22.38a2 2 0 0 0 .73 2.73l.15.1a2 2 0 0 1 1 1.72v.51a2 2 0 0 1-1 1.74l-.15.09a2 2 0 0 0-.73 2.73l.22.38a2 2 0 0 0 2.73.73l.15-.08a2 2 0 0 1 2 0l.43.25a2 2 0 0 1 1 1.73V20a2 2 0 0 0 2 2h.44a2 2 0 0 0 2-2v-.18a2 2 0 0 1 1-1.73l.43-.25a2 2 0 0 1 2 0l.15.08a2 2 0 0 0 2.73-.73l.22-.39a2 2 0 0 0-.73-2.73l-.15-.08a2 2 0 0 1-1-1.74v-.5a2 2 0 0 1 1-1.74l.15-.09a2 2 0 0 0 .73-2.73l-.22-.38a2 2 0 0 0-2.73-.73l-.15.08a2 2 0 0 1-2 0l-.43-.25a2 2 0 0 1-1-1.73V4a2 2 0 0 0-2-2z\"/>\n" +
      "  <circle cx=\"12\" cy=\"12\" r=\"3\"/>\n" +
      "</svg>\n",
    route: "/dashboard/recruiter/help"
  }
];
