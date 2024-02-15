(ns antoine.scriptQuirurgico
  (:require [antoine.system :refer [configuracion]] 
            [clojure.java.io :as io]
            [antoine.servicios.conexiones :as conn]
            [honey.sql :as sql]
            [antoine.sql.paciente_ambulatorio :as pac]
            [antoine.sql.seguridad_quirurgica :as sqa]
            [antoine.sql.alertas_asistenciales :as ala]
            [antoine.sql.his_lectora :as hl]
            [antoine.sql.anestesia_ambulatoria :as anes-ambu]
            [java-time.api :as jt]) 
  (:gen-class))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(defn reinicio-paciente
  "llama a todas las funciones necesarias para borrar todos los registros de un paciente y poder usarlo desde 0
   puedes llamar la funcion sola y te va a usar un paciente random o puedes darle la historia clinica"
  [histcli] (let [paciente (pac/limpiar-paciente (pac/paciente-ambulatorio-aleatorio histcli) {:Guar_Estado 1,
                                                                                               :Guar_Estado1 1,
                                                                                               :Guar_Estado3 1
                                                                                               :Guar_Medico 0,
                                                                                               :Guar_TipoMed 0,
                                                                                               :Guar_FechaAlta 0,
                                                                                               :Guar_HoraAlta 0,
                                                                                               :Guar_EspMed 0,
                                                                                               :Guar_HoraAtenc 0})]
              (sqa/borrar paciente)
              (ala/borrar paciente)
              (hl/borrar paciente 9999)
              (anes-ambu/borrar paciente)
              paciente))

(comment
  (sqa/borrar (pac/paciente-ambulatorio-aleatorio 758036))
  (jt/format "Hmm" (jt/local-date-time 2024 02 02 04 06))
  (hl/borrar (pac/paciente-ambulatorio-aleatorio 834619) 9999)
  (reinicio-paciente 834619)
  :ref
  )
