(ns antoine.sql.parametros
  "queries que se encarga de aumentar los contadores en los parametros"
   (:require [antoine.servicios.conexiones :refer [consulta-desal]]
             [honey.sql :as sql]
             [antoine.utils.utils :as utils]))

(defn suma-protocolo-ambulatorio
  "suma 1 al contador de protocolo ambulatorio"
  []
  (let [query (sql/format {:update :tbl_parametros
                           :set {:contador_entero (sql/call :+ :contador_entero 1)}
                           :where [:= :paramid 17]})]
    (consulta-desal query)))


(comment
  (suma-protocolo-ambulatorio)
  (resta-protocolo-ambulatorio)

  :ref)