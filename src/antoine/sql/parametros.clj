(ns antoine.sql.parametros
  "queries que se encarga de aumentar los contadores en los parametros"
   (:require [antoine.servicios.conexiones :refer [consulta-desal consulta-parametros]]
             [honey.sql :as sql]
             [antoine.utils.utils :as utils]))

(defn obtiene-nro-protocolo-internado 
  "Suma 1 al contador de protocolo internado lo devuelve"
  []
  (let [query (sql/format {:update :param
                           :set {:prm_54 (sql/call :+ :prm_54 1)}
                           :where [:= :key_param 1]
                           :returning :prm_54})]
    (consulta-parametros query)))

(defn obtiene-nro-protocolo-ambulatorio
  "Suma 1 al contador de protocolo ambulatorio y lo devuelve"
  []
  (let [query (sql/format {:update :tbl_parametros
                           :set {:contador_entero (sql/call :+ :contador_entero 1)}
                           :where [:= :paramid 17]
                           :returning :contador_entero})]
    (consulta-desal query)))

(comment
  (obtiene-nro-protocolo-ambulatorio)

  (obtiene-nro-protocolo-internado)
  

  :ref)