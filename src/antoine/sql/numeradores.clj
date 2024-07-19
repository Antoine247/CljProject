(ns antoine.sql.numeradores
  (:require [honey.sql :as sql]
            [next.jdbc :as jdbc]
            [antoine.system :refer [obtener-datasource]]))

(defn obtener-nro-protocolo
  []
  (jdbc/with-transaction [tx (obtener-datasource :postgres :parametros)]
    (let [param (jdbc/execute! tx (sql/format {:select :prm_54
                                               :from [:param]
                                               :where [:= :key_param 1]}))
          actualizacion (jdbc/execute! tx (sql/format {:update :param
                                                       :set {:prm_54 (inc (:param/prm_54 (first param)))}
                                                       :where [:= :key_param 1]
                                                       :returning [:prm_54]}))]
      (:param/prm_54 (first actualizacion)))))

   
(comment
   
  (tap> (obtener-nro-protocolo))
  
  )