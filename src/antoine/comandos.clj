(ns antoine.comandos
  (:require [antoine.eventos :as e]))

(defn crear_protocolo_internado 
  "Crea un protocolo de internación con el estado indicado por las banderas recibidas como argumento.
   aler -> Alerta asistencial
   ca -> Completo con anestesia
   csa -> Completo sin anestesia
   sqc -> Seguridad Quirurgica Completa
   sqi -> Seguridad Quirurgica antes incision
   eap -> Evaluacion anestesica preoperatoria
   fa -> Ficha anestesica
   fasc -> Ficha anestesica sin cerrar
   fasm -> Ficha anestesica sin medicamentos
   m -> Medicamentos cirugia
   hci -> Historia Clínica de Ingreso
   anatpa -> Anatomía Patológica
   cepa -> Con Evaluacion Post Anestesica
   sepa -> Sin Evaluacion Post Anestesica
   rim -> Retiro de Implante
   imp -> Insercion de implante
   prto -> Parto
   hem -> Hemodinamia"
  [{:keys [aler ca csa sqc sqi eap fa fasc fasm m hci anatpa cepa sepa rim imp prto hem]}])

(defn crear_protocolo_ambulatorio
  "Crea un protocolo de internación con el estado indicado por las banderas recibidas como argumento.
     aler -> Alerta asistencial
     ca -> Completo con anestesia
     csa -> Completo sin anestesia
     sqc -> Seguridad Quirurgica Completa
     sqi -> Seguridad Quirurgica antes incision
     eap -> Evaluacion anestesica preoperatoria
     fa -> Ficha anestesica
     fasc -> Ficha anestesica sin cerrar
     fasm -> Ficha anestesica sin medicamentos
     m -> Medicamentos cirugia
     hci -> Historia Clínica de Ingreso
     cepa -> Con Evaluacion Post Anestesica
     sepa -> Sin Evaluacion Post Anestesica
     rim -> Retiro de Implante
     imp -> Insercion de implante
     hem -> Hemodinamia"
  [{:keys [aler ca csa sqc sqi eap fa fasc fasm m hci cepa sepa rim imp hem]}])