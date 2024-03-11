(ns antoine.system
  "crea variable configuracion con el archivo config.edn que son los parametros para conectar a la base de datos"
(:require [aero.core :refer [read-config]]
          [clojure.java.io :as io]))

(def configuracion (read-config (io/resource "config.edn")))

(defn obtener-datasource
  [tipo k]
  (get-in configuracion [:db-type tipo k]))

(comment 
  (obtener-datasource :relativity :asistencial)
  (obtener-datasource :relativity :maestros)
  (obtener-datasource :postgres :desal)
  )