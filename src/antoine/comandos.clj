(ns antoine.comandos
  (:require [antoine.eventos :as e]))

(defn crear_protocolo_internado 
  "Crea un protocolo de internación con el estado indicado por las banderas recibidas como argumento.
   ca -> Completo con anestesia
   csa -> Completo sin anestesia
   sqc -> Seguridad Quirurgica Completa
   sqi -> Seguridad Quirurgica antes incision"
  [{:keys [ca csa sqc sqi eap fa fasc fasm m cepa sepa rim imp]}])

(defn crear_protocolo_ambulatorio
  [{:keys []}])