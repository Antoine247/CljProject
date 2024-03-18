(ns antoine.sql.paciente-internado
  (:require [honey.sql :as sql]
            [antoine.servicios.conexiones :refer [consulta-asistencial-todo]]
            [com.brunobonacci.mulog :as u])
  (:import java.sql.SQLException))

(def pacientes (atom nil))

(defn paciente-internado-aleatorio 
  []
  (if-not @pacientes
    (let [query (sql/format {:select [:tbc_admision_scroll/Adm_HistClin
                                      :tbc_admision_scroll/Adm_FecIng
                                      :tbc_admision_scroll/Adm_HorIng]
                             :from [:tbc_admision_scroll]
                             :where [:= :tbc_admision_scroll/Adm_FecAltaEfec 0]})
          resultados (try 
                       (consulta-asistencial-todo query)
                       (catch SQLException e (u/log ::error-en-consulta-tbc-admision-scroll :mensaje (.getMessage e))))]
      (reset! pacientes resultados)
      (first resultados))
    (rand-nth @pacientes)))
   
(comment 
  (paciente-internado-aleatorio)
  @pacientes 
   
  )