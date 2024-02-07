(ns antoine.scriptQuirurgico
  (:require [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [aero.core :refer [read-config]]
            [clojure.java.io :as io])
  (:import (com.zaxxer.hikari HikariDataSource))
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
  (def rc (read-config (io/resource "config.edn")))
  (def db {:dbtype "postgres" 
           :dbname "desal" 
           :user "postgres" 
           :password "postgres" 
           :host "10.200.0.90"
           :port "5432"})

  (def ds (jdbc/get-datasource db)) 

  (def dsr (jdbc/get-datasource (-> rc :db :asistencial)))
  
  (jdbc/execute! ds ["select * from fichaaneste_cab fc where fc.histcli_unico = 839094"])
  (jdbc/execute! dsr ["select * from tbc_val_ciru where tbc_val_ciru.VcirHistClinica = 847020"])
  )