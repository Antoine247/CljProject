(ns antoine.scriptQuirurgico
  (:require [antoine.system :refer [configuracion]] 
            [clojure.java.io :as io]
            [antoine.servicios.conexiones :as conn]
            [honey.sql :as sql] 
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


(comment
  (sqa/borrar (pac/paciente-ambulatorio-aleatorio 758036))
  (jt/format "Hmm" (jt/local-date-time 2024 02 02 04 06))
  (hl/borrar (pac/paciente-ambulatorio-aleatorio 834619) 9999)
  (reinicio-paciente 834619)
  :ref
  )
